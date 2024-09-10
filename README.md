# Proyecto Inditex

Este proyecto es una prueba técnica desarrollada para Inditex utilizando Spring Boot 3.3, Java 17 y Arquitectura Hexagonal.

## Técnologias

- Java 17
- Maven 3.6+
- Spring Boot 3.3
- H2

## Estructura del Proyecto

La arquitectura hexagonal divide el proyecto en tres capas principales:

1. **Capa de Dominio**: Contiene la lógica de negocio central y es independiente de cualquier infraestructura o framework específico.
2. **Capa de Aplicación**: Contiene los servicios que interactúan con la capa de dominio y gestiona los casos de uso de la aplicación.
3. **Capa de Infraestructura**: Contiene la implementación de interfaces y adaptadores necesarios para interactuar con sistemas externos (bases de datos, APIs, etc.).

## Compilar el proyecto
```
mvn clean install
```

## Ejecutar la aplicación
```
mvn spring-boot:run
```
