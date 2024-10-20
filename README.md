# Microservices Project

Este proyecto consiste en una arquitectura de microservicios compuesta por tres servicios:

- **Inventory Service** (Servicio de Inventario): utiliza PostgreSQL como base de datos.
- **Orders Service** (Servicio de Órdenes): utiliza MySQL como base de datos.
- **Products Service** (Servicio de Productos): utiliza PostgreSQL como base de datos.

Cada uno de los microservicios está diseñado para manejar un aspecto específico del sistema, y están desplegados utilizando Docker y Docker Compose.


## Requisitos

Para ejecutar este proyecto, necesitarás tener instalados los siguientes programas:

- Docker 26.x o superior
- Docker Compose v2.x o superior
- Maven 3.x o superior
- Java 21

# Notas Adicionales
- Cada microservicio tiene su propio puerto.
- El archivo docker-compose.yml orquesta los microservicios y las bases de datos.
- Las dependencias del proyecto están gestionadas mediante Maven.
