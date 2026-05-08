package com.javagen.services;

import com.javagen.model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class InteractivePromptService {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ProjectType promptProjectType() throws Exception {
        System.out.println("\nSelect project type:");
        System.out.println("  1) Web");
        System.out.println("  2) Desktop");
        System.out.println("  3) Mobile App");
        System.out.print("Choice [1]: ");
        String input = reader.readLine().trim();
        return switch (input) {
            case "2" -> ProjectType.DESKTOP;
            case "3" -> ProjectType.APP;
            default -> ProjectType.WEB;
        };
    }

    public BackendType promptBackend() throws Exception {
        System.out.println("\nSelect backend:");
        System.out.println("  1) Spring Boot");
        System.out.println("  2) Quarkus");
        System.out.println("  3) Micronaut");
        System.out.print("Choice [1]: ");
        String input = reader.readLine().trim();
        return switch (input) {
            case "2" -> BackendType.QUARKUS;
            case "3" -> BackendType.MICRONAUT;
            default -> BackendType.SPRING;
        };
    }

    public FrontendType promptFrontend() throws Exception {
        System.out.println("\nSelect frontend:");
        System.out.println("  1) React");
        System.out.println("  2) Angular");
        System.out.println("  3) Vue");
        System.out.print("Choice [1]: ");
        String input = reader.readLine().trim();
        return switch (input) {
            case "2" -> FrontendType.ANGULAR;
            case "3" -> FrontendType.VUE;
            default -> FrontendType.REACT;
        };
    }

    public DatabaseType promptDatabase() throws Exception {
        System.out.println("\nSelect database:");
        System.out.println("  1) PostgreSQL");
        System.out.println("  2) MySQL");
        System.out.println("  3) MongoDB");
        System.out.print("Choice [1]: ");
        String input = reader.readLine().trim();
        return switch (input) {
            case "2" -> DatabaseType.MYSQL;
            case "3" -> DatabaseType.MONGODB;
            default -> DatabaseType.POSTGRES;
        };
    }

    public AuthType promptAuth() throws Exception {
        System.out.println("\nSelect authentication:");
        System.out.println("  1) JWT");
        System.out.println("  2) OAuth2");
        System.out.println("  3) Session");
        System.out.print("Choice [1]: ");
        String input = reader.readLine().trim();
        return switch (input) {
            case "2" -> AuthType.OAUTH2;
            case "3" -> AuthType.SESSION;
            default -> AuthType.JWT;
        };
    }

    public boolean promptFeature(String feature) throws Exception {
        System.out.print("\nInclude " + feature + "? [y/N]: ");
        String input = reader.readLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
}
