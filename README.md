# javagen

Java ecosystem scaffolding CLI tool. Generates boilerplate Java projects from terminal commands.

## Install

### Windows (PowerShell)
```powershell
iwr -useb https://github.com/santhosh29dev/Javagen/releases/latest/download/install.ps1 | iex
```

### macOS / Linux
```bash
curl -fsSL https://github.com/santhosh29dev/Javagen/releases/latest/download/install.sh | bash
```

### From Source
```bash
git clone https://github.com/santhosh29dev/Javagen.git
cd Javagen
mvn clean package
java -jar target/javagen-1.0.0.jar --help
```

## Requirements

- Java 21+

## Usage

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
javagen --help
javagen create --help
```

## Options

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
