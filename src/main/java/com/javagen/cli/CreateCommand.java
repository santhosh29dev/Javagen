package com.javagen.cli;

import com.javagen.core.GeneratorEngine;
import com.javagen.core.ProjectContext;
import com.javagen.model.*;
import com.javagen.services.InteractivePromptService;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
    name = "create",
    description = "Scaffold a new Java project"
)
public class CreateCommand implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Project type: web, desk, app")
    private String type;

    @CommandLine.Option(names = {"-n", "--name"}, description = "Project name")
    private String projectName;

    @CommandLine.Option(names = {"-b", "--backend"}, description = "Backend: spring, quarkus, micronaut")
    private String backend;

    @CommandLine.Option(names = {"-f", "--frontend"}, description = "Frontend: react, angular, vue")
    private String frontend;

    @CommandLine.Option(names = {"-d", "--database"}, description = "Database: postgres, mysql, mongodb, sqlite")
    private String database;

    @CommandLine.Option(names = {"-a", "--auth"}, description = "Auth: jwt, oauth2, session")
    private String auth;

    @CommandLine.Option(names = {"--docker"}, description = "Include Docker setup")
    private boolean docker;

    @CommandLine.Option(names = {"--swagger"}, description = "Include Swagger/OpenAPI")
    private boolean swagger;

    @CommandLine.Option(names = {"--redis"}, description = "Include Redis")
    private boolean redis;

    @CommandLine.Option(names = {"--kafka"}, description = "Include Kafka")
    private boolean kafka;

    @CommandLine.Option(names = {"-i", "--interactive"}, description = "Interactive prompts")
    private boolean interactive;

    @Override
    public Integer call() throws Exception {
        InteractivePromptService promptService = new InteractivePromptService();

        ProjectType projectType = parseType(type);
        if (projectType == null) {
            System.err.println("Unknown project type: " + type);
            System.err.println("Valid types: web, desk, app");
            return 1;
        }

        if (projectName == null || projectName.isBlank()) {
            System.out.print("Project name: ");
            projectName = new java.io.BufferedReader(new java.io.InputStreamReader(System.in)).readLine().trim();
            if (projectName.isBlank()) {
                System.err.println("Project name is required.");
                return 1;
            }
        }

        ProjectContext ctx = new ProjectContext(projectName);
        ctx.setProjectType(projectType);
        ctx.setDockerEnabled(docker);
        ctx.setSwaggerEnabled(swagger);
        ctx.setRedisEnabled(redis);
        ctx.setKafkaEnabled(kafka);

        if (interactive) {
            runInteractive(ctx, promptService);
        } else {
            ctx.setBackendType(parseBackend(backend));
            ctx.setFrontendType(parseFrontend(frontend));
            ctx.setDatabaseType(parseDatabase(database));
            ctx.setAuthType(parseAuth(auth));
        }

        System.out.println("\nGenerating " + type + " project: " + projectName + " ...");
        new GeneratorEngine().generate(ctx);
        System.out.println("\nProject generated at: " + ctx.getOutputPath());
        System.out.println("Done!");
        return 0;
    }

    private void runInteractive(ProjectContext ctx, InteractivePromptService prompt) throws Exception {
        switch (ctx.getProjectType()) {
            case WEB -> {
                ctx.setBackendType(prompt.promptBackend());
                ctx.setFrontendType(prompt.promptFrontend());
                ctx.setDatabaseType(prompt.promptDatabase());
                ctx.setAuthType(prompt.promptAuth());
                ctx.setDockerEnabled(prompt.promptFeature("Docker"));
                ctx.setSwaggerEnabled(prompt.promptFeature("Swagger/OpenAPI"));
                ctx.setRedisEnabled(prompt.promptFeature("Redis"));
                ctx.setKafkaEnabled(prompt.promptFeature("Kafka"));
            }
            case DESKTOP -> {
                ctx.setBackendType(prompt.promptBackend());
                ctx.setDatabaseType(prompt.promptDatabase());
                ctx.setAuthType(prompt.promptAuth());
            }
            case APP -> {
                ctx.setBackendType(prompt.promptBackend());
                ctx.setFrontendType(prompt.promptFrontend());
                ctx.setDatabaseType(prompt.promptDatabase());
                ctx.setAuthType(prompt.promptAuth());
            }
        }
    }

    private ProjectType parseType(String type) {
        return switch (type.toLowerCase()) {
            case "web" -> ProjectType.WEB;
            case "desk", "desktop" -> ProjectType.DESKTOP;
            case "app", "mobile" -> ProjectType.APP;
            default -> null;
        };
    }

    private BackendType parseBackend(String backend) {
        if (backend == null) return BackendType.SPRING;
        return switch (backend.toLowerCase()) {
            case "quarkus" -> BackendType.QUARKUS;
            case "micronaut" -> BackendType.MICRONAUT;
            case "firebase" -> BackendType.FIREBASE;
            default -> BackendType.SPRING;
        };
    }

    private FrontendType parseFrontend(String frontend) {
        if (frontend == null) return FrontendType.REACT;
        return switch (frontend.toLowerCase()) {
            case "angular" -> FrontendType.ANGULAR;
            case "vue" -> FrontendType.VUE;
            case "flutter" -> FrontendType.FLUTTER;
            case "reactnative", "react-native" -> FrontendType.REACT_NATIVE;
            default -> FrontendType.REACT;
        };
    }

    private DatabaseType parseDatabase(String database) {
        if (database == null) return DatabaseType.POSTGRES;
        return switch (database.toLowerCase()) {
            case "mysql" -> DatabaseType.MYSQL;
            case "mongodb", "mongo" -> DatabaseType.MONGODB;
            case "sqlite" -> DatabaseType.SQLITE;
            case "firestore" -> DatabaseType.FIRESTORE;
            default -> DatabaseType.POSTGRES;
        };
    }

    private AuthType parseAuth(String auth) {
        if (auth == null) return AuthType.JWT;
        return switch (auth.toLowerCase()) {
            case "oauth2", "oauth" -> AuthType.OAUTH2;
            case "session" -> AuthType.SESSION;
            default -> AuthType.JWT;
        };
    }
}
