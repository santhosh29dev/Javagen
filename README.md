# javagen

Java ecosystem scaffolding CLI tool. Generates boilerplate Java projects from terminal commands.

## Requirements

- Java 21+
- Maven 3.9+

## Build

```bash
mvn clean package
```

The fat JAR will be at `target/javagen-1.0.0.jar`.

## Usage

### As a JAR

```bash
java -jar target/javagen-1.0.0.jar create web --name myproject
```

### As a Windows global command

1. Add `S:\javagen` to your system PATH
2. Then use directly:

```cmd
javagen create web --name myproject
javagen create desk --name myproject
javagen create app --name myproject
```

### Commands

```bash
# Web project (Spring Boot + React/Angular/Vue)
javagen create web --name myapp --backend spring --frontend react --database postgres --auth jwt

# With optional features
javagen create web --name myapp --docker --swagger --redis --kafka

# Interactive mode
javagen create web --name myapp -i

# Desktop project (JavaFX/Swing)
javagen create desk --name mydesktop

# Mobile app (Flutter + Spring Boot)
javagen create app --name mymobile

# Help
java -jar target/javagen-1.0.0.jar --help
java -jar target/javagen-1.0.0.jar create --help
```

### Options

| Option | Description | Default |
|--------|-------------|---------|
| `-n, --name` | Project name | (required) |
| `-b, --backend` | Backend: spring, quarkus, micronaut | spring |
| `-f, --frontend` | Frontend: react, angular, vue | react |
| `-d, --database` | Database: postgres, mysql, mongodb, sqlite | postgres |
| `-a, --auth` | Auth: jwt, oauth2, session | jwt |
| `--docker` | Include Docker setup | false |
| `--swagger` | Include Swagger/OpenAPI | false |
| `--redis` | Include Redis config | false |
| `--kafka` | Include Kafka config | false |
| `-i, --interactive` | Interactive prompts | false |

## Architecture

```
src/main/java/com/javagen/
├── JavagenCLI.java              # Entry point
├── cli/                          # Picocli commands
├── core/                         # Engine, context, template rendering
├── generators/                   # Project generators (web/desktop/app)
├── features/                     # Feature modules (backend/frontend/db/auth/docker/swagger)
├── model/                        # Enums (ProjectType, BackendType, etc.)
├── services/                     # File I/O, prompts
└── utils/                        # Constants
```
