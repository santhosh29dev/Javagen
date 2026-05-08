# javagen

Java ecosystem scaffolding CLI tool. Generates boilerplate Java projects from terminal commands.

## Install

### All Platforms — Easiest
Download [`javagen.zip`](https://github.com/santhosh29dev/Javagen/releases/latest/download/javagen.zip), extract it, and run the installer.

Or from CMD:
```cmd
curl.exe -fsSL -o javagen.zip https://github.com/santhosh29dev/Javagen/releases/latest/download/javagen.zip
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
