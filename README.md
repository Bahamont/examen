# Huertos Urbanos — Microservicios de Parcelas

Implementación base del caso de huertos urbanos comunitarios con **validación**, **sanitización**, **excepciones personalizadas** y **mensajes de error personalizados**.

## Arquitectura

| Microservicio | Persistencia | Puerto |
|---|---|---|
| `parcel-service-mongo` | MongoDB (Opción A) | `8081` |
| `parcel-service-postgres` | PostgreSQL (Opción B) | `8082` |

Ambos exponen la misma API de parcelas.

## API

### `POST /api/v1/parcels`
Registra una nueva parcela.

```json
{
  "name": "Huerto Centro Norte",
  "location": {
    "latitude": 19.4326,
    "longitude": -99.1332
  },
  "soilType": "LIMOSO",
  "ownerOrLead": "Ana Ruiz"
}
```

`soilType` válidos: `ARCILLOSO`, `LIMOSO`, `ARENOSO`.

### `GET /api/v1/parcels`
Lista todas las parcelas.

### `GET /api/v1/parcels/{id}`
Consulta el detalle y estado actual de una parcela.

## Validación, sanitización y errores

- **Validación**: Bean Validation (`@NotBlank`, `@Size`, `@Pattern`, `@NotNull`, `@DecimalMin`/`@DecimalMax`) sobre DTOs.
- **Sanitización**: `InputSanitizer` + `SanitizingRequestBodyAdvice` limpian HTML, caracteres de control y espacios antes de validar/persistir.
- **Excepciones personalizadas**:
  - `BusinessException`
  - `ResourceNotFoundException` (404)
  - `DuplicateResourceException` (409)
- **Mensajes personalizados**: `messages.properties` y `ValidationMessages.properties` (español).
- **Manejo global**: `GlobalExceptionHandler` unifica respuestas de error.

Ejemplo de error de validación:

```json
{
  "timestamp": "2026-10-14T23:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Los datos enviados no cumplen las reglas de validación.",
  "path": "/api/v1/parcels",
  "details": [
    {
      "field": "name",
      "message": "El nombre de la parcela es obligatorio.",
      "rejectedValue": ""
    }
  ]
}
```

## Levantamiento con Docker Compose

```bash
docker compose up --build
```

Servicios:

- MongoDB: `localhost:27017`
- PostgreSQL: `localhost:5432` (user/pass/db: `huertos` / `huertos_secret` / `huertos`)
- API Mongo: `http://localhost:8081/api/v1/parcels`
- API Postgres: `http://localhost:8082/api/v1/parcels`

## Pruebas rápidas (curl)

```bash
# Crear parcela (Mongo)
curl -X POST http://localhost:8081/api/v1/parcels \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Huerto Centro Norte\",\"location\":{\"latitude\":19.4326,\"longitude\":-99.1332},\"soilType\":\"LIMOSO\",\"ownerOrLead\":\"Ana Ruiz\"}"

# Listar (Postgres)
curl http://localhost:8082/api/v1/parcels
```

## Estructura

```
examen/
├── docker-compose.yml
├── parcel-service-mongo/
└── parcel-service-postgres/
```

Stack: Java 17+, Spring Boot 3.3 (Web, Validation, Data MongoDB / Data JPA).
