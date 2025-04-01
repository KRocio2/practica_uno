package com.universidad.controller;

import com.universidad.dto.EstudianteDTO;
import com.universidad.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EstudianteController {

    private final IEstudianteService estudianteService;

    @Autowired
    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/estudiantes")
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    // a) Actualización de un estudiante existente
    @PutMapping("/estudiantes/{id}")
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id,
            @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, estudianteDTO);
        if (estudianteActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Si el estudiante no existe
        }
        return ResponseEntity.ok(estudianteActualizado); // Retorna el estudiante actualizado
    }

    // b) Creación de un nuevo estudiante
    @PostMapping("/estudiantes")
    public ResponseEntity<EstudianteDTO> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO estudianteCreado = estudianteService.crearEstudiante(estudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteCreado); // Retorna el estudiante creado
    }

    // c) Eliminar un estudiante por ID
    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        boolean eliminado = estudianteService.eliminarEstudiante(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Si no se encuentra el estudiante
        }
        return ResponseEntity.noContent().build(); // Retorna 204 No Content si la operación fue exitosa
    }
}
