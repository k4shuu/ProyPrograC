## Módulo de Persistencia y Login (SQLite)

El sistema incorpora un módulo de autenticación y registro de usuarios utilizando el patrón **DAO/DTO** y almacenamiento local mediante **SQLite** con **JDBC**.

### Requisitos de Ejecución
- **Java JDK** (versión 8 o superior recomendada).
- **Maven** (para la gestión automática de dependencias).
- **SQLite JDBC Driver** (se descarga automáticamente mediante el archivo `pom.xml`).

### Dependencia incorporada (`pom.xml`):
```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.45.1.0</version>
</dependency>