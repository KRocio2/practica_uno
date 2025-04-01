# Proyecto de Gestión de Estudiantes

Este proyecto es una API para gestionar estudiantes, con funcionalidades para:

- Crear un nuevo estudiante
- Actualizar la información de un estudiante existente
- Eliminar un estudiante
- Obtener todos los estudiantes registrados

## Requisitos

- **Java 17** o superior
- **Spring Boot**
- **Maven**
- **Postman** o **cURL** para probar la API

## Endpoints de la API

### Obtener todos los estudiantes
- **Método**: `GET`
- **URL**: `/api/estudiantes`
- **Descripción**: Obtiene una lista de todos los estudiantes.
- **Respuesta**:
    ```json
    [
        {
            "id": 1,
            "nombre": "Juan",
            "apellido": "Pérez",
            "email": "juan.perez@example.com",
            "fechaNacimiento": "2000-05-15",
            "numeroInscripcion": "S001"
        },
        {
            "id": 2,
            "nombre": "Ana",
            "apellido": "García",
            "email": "ana.garcia@example.com",
            "fechaNacimiento": "1998-08-25",
            "numeroInscripcion": "S002"
        }
    ]
    ```

### Crear un nuevo estudiante
- **Método**: `POST`
- **URL**: `/api/estudiantes`
- **Descripción**: Crea un nuevo estudiante.
- **Cuerpo de la solicitud (JSON)**:
    ```json
    {
        "nombre": "Carlos",
        "apellido": "Gomez",
        "email": "carlos.gomez@example.com",
        "fechaNacimiento": "1995-08-12",
        "numeroInscripcion": "S004"
    }
    ```
- **Respuesta**:
    ```json
    {
        "id": 4,
        "nombre": "Carlos",
        "apellido": "Gomez",
        "email": "carlos.gomez@example.com",
        "fechaNacimiento": "1995-08-12",
        "numeroInscripcion": "S004"
    }
    ```

### Actualizar un estudiante existente
- **Método**: `PUT`
- **URL**: `/api/estudiantes/{id}`
- **Descripción**: Actualiza los datos de un estudiante por su `id`.
- **Cuerpo de la solicitud (JSON)**:
    ```json
    {
        "nombre": "Carlos",
        "apellido": "Gomez",
        "email": "carlos.gomez.updated@example.com",
        "fechaNacimiento": "1995-08-12",
        "numeroInscripcion": "S004"
    }
    ```
- **Respuesta**:
    ```json
    {
        "id": 4,
        "nombre": "Carlos",
        "apellido": "Gomez",
        "email": "carlos.gomez.updated@example.com",
        "fechaNacimiento": "1995-08-12",
        "numeroInscripcion": "S004"
    }
    ```

### Eliminar un estudiante
- **Método**: `DELETE`
- **URL**: `/api/estudiantes/{id}`
- **Descripción**: Elimina un estudiante por su `id`.
- **Respuesta**:
    - **Código de estado**: `204 No Content` si se elimina correctamente.
    - **Código de estado**: `404 Not Found` si el estudiante no existe.

## Pruebas

Para probar la API, puedes usar **Postman** 


