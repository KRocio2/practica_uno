package com.universidad.service.impl;

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante;
import com.universidad.repository.EstudianteRepository;
import com.universidad.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @PostConstruct
    public void init() {
        estudianteRepository.init(); // Inicializa los datos de ejemplo al iniciar la aplicación
    }

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();

        for (Estudiante estudiante : estudiantes) {
            estudiantesDTO.add(convertToDTO(estudiante));
        }
        return estudiantesDTO;
    }

    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = convertToEntity(estudianteDTO);
        Estudiante estudianteGuardado = estudianteRepository.save(estudiante); // Guarda el estudiante
        return convertToDTO(estudianteGuardado); // Devuelve el EstudianteDTO del estudiante guardado
    }

    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        // Usa Optional para manejar la posible ausencia del estudiante
        Optional<Estudiante> estudianteExistenteOptional = estudianteRepository.findById(id);
        if (!estudianteExistenteOptional.isPresent()) {
            return null; // Si el estudiante no existe, retorna null
        }

        Estudiante estudianteExistente = estudianteExistenteOptional.get(); // Obtiene el estudiante del Optional

        // Actualiza los campos del estudiante
        estudianteExistente.setNombre(estudianteDTO.getNombre());
        estudianteExistente.setApellido(estudianteDTO.getApellido());
        estudianteExistente.setEmail(estudianteDTO.getEmail());
        estudianteExistente.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudianteExistente.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());

        Estudiante estudianteActualizado = estudianteRepository.save(estudianteExistente); // Guarda el estudiante
                                                                                           // actualizado
        return convertToDTO(estudianteActualizado); // Devuelve el EstudianteDTO actualizado
    }

    @Override
    public boolean eliminarEstudiante(Long id) {
        if (!estudianteRepository.existsById(id)) {
            return false; // Si no existe, retorna false
        }
        estudianteRepository.deleteById(id); // Elimina el estudiante
        return true; // Si la eliminación fue exitosa, retorna true
    }

    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        return EstudianteDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .apellido(estudiante.getApellido())
                .email(estudiante.getEmail())
                .fechaNacimiento(estudiante.getFechaNacimiento())
                .numeroInscripcion(estudiante.getNumeroInscripcion())
                .build();
    }

    private Estudiante convertToEntity(EstudianteDTO estudianteDTO) {
        return Estudiante.builder()
                .id(estudianteDTO.getId())
                .nombre(estudianteDTO.getNombre())
                .apellido(estudianteDTO.getApellido())
                .email(estudianteDTO.getEmail())
                .fechaNacimiento(estudianteDTO.getFechaNacimiento())
                .numeroInscripcion(estudianteDTO.getNumeroInscripcion())
                .build();
    }
}
