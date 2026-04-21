# Prueba Tecnica - Backend (Spring Boot + WebFlux)

API reactiva para gestionar franquicias, sucursales y productos con persistencia en MongoDB.

## Cloud deployment

- API URL: [https://prueba-tecnica-spoe.onrender.com](https://prueba-tecnica-spoe.onrender.com)
- Swagger: [https://prueba-tecnica-spoe.onrender.com/swagger-ui.html](https://prueba-tecnica-spoe.onrender.com/swagger-ui.html)

Proveedor usado:

- Render (API)
- MongoDB Atlas M0 (persistencia)

Variables requeridas:

- `MONGODB_URI`

Notas free tier:

- Puede haber cold start despues de periodos de inactividad.

## Tecnologias

- Java 17
- Spring Boot 3 (WebFlux)
- Spring Data Reactive MongoDB
- Maven
- Docker / Docker Compose (Mongo local)
- OpenAPI / Swagger
- Terraform (IaC para MongoDB Atlas)

## Arquitectura

El proyecto sigue una separacion por capas inspirada en Clean Architecture:

- `domain`: modelos y puertos (reglas de negocio)
- `application`: casos de uso
- `infrastructure`: adapters web y persistencia
- `shared`: excepciones y manejo global de errores

## Requisitos previos

- Java 17
- Maven 3.9+
- Docker Desktop
- Terraform 1.6+ (solo para bloque IaC)

## Configuracion de variables

1. Copia el archivo de ejemplo:

```bash
cp .env.example .env
```

2. Ajusta valores en `.env` (usuario/password Mongo y `MONGODB_URI`).


## Ejecucion local

### 1) Levantar MongoDB

```bash
docker compose up -d
```

### 2) Ejecutar la API

```bash
mvn spring-boot:run
```

La API queda disponible en:

- `http://localhost:8080`
- `http://localhost:8080/` (ruta raiz con enlaces utiles)

## Documentacion API (Swagger)

- UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`

## Endpoints implementados

### Franquicias

- `POST /api/franchises` - Crear franquicia
- `GET /api/franchises` - Listar franquicias
- `PATCH /api/franchises/{franchiseId}/name` - Actualizar nombre de franquicia 
- `GET /api/franchises/{franchiseId}/top-stock-by-branch` - Producto con mayor stock por sucursal

### Sucursales

- `POST /api/franchises/{franchiseId}/branches` - Crear sucursal en franquicia
- `GET /api/branches` - Listar sucursales con franquicia asociada
- `PATCH /api/branches/{branchId}/name` - Actualizar nombre de sucursal 

### Productos

- `POST /api/branches/{branchId}/products` - Crear producto en sucursal
- `DELETE /api/branches/{branchId}/products/{productId}` - Eliminar producto de sucursal
- `GET /api/products` - Listar productos con sucursal asociada
- `PATCH /api/products/{productId}/stock` - Actualizar stock de producto
- `PATCH /api/products/{productId}/name` - Actualizar nombre de producto 

### Root

- `GET /` - Mensaje de estado y enlaces a Swagger/OpenAPI

## Persistencia e IaC (Terraform)

Se incluye carpeta `terraform/` para aprovisionar MongoDB Atlas:

- Proyecto Atlas
- Cluster
- Usuario de base de datos
- Regla de acceso por CIDR

Ver instrucciones en:

- `terraform/README.md`

## Pruebas

Ejecutar tests:

```bash
mvn test
```
