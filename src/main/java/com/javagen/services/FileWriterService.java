package com.javagen.services;

import com.javagen.core.ProjectContext;
import com.javagen.core.TemplateEngine;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileWriterService {

    private final TemplateEngine templateEngine;

    public FileWriterService() {
        this.templateEngine = new TemplateEngine();
    }

    public void writeFromTemplate(String templateName, ProjectContext ctx, String relativeOutputPath) {
        Map<String, Object> scopes = buildScopes(ctx);
        Path outputPath = Path.of(ctx.getOutputPath(), relativeOutputPath);
        templateEngine.renderToFile(templateName, scopes, outputPath);
    }

    public void writeRaw(String content, ProjectContext ctx, String relativeOutputPath) {
        try {
            Path outputPath = Path.of(ctx.getOutputPath(), relativeOutputPath);
            java.nio.file.Files.createDirectories(outputPath.getParent());
            java.nio.file.Files.writeString(outputPath, content);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write file: " + relativeOutputPath, e);
        }
    }

    public void createDirectory(ProjectContext ctx, String relativePath) {
        try {
            java.nio.file.Files.createDirectories(Path.of(ctx.getOutputPath(), relativePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create directory: " + relativePath, e);
        }
    }

    public void createBaseDirectories(ProjectContext ctx, String... dirs) {
        for (String dir : dirs) {
            createDirectory(ctx, dir);
        }
    }

    private Map<String, Object> buildScopes(ProjectContext ctx) {
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("projectName", ctx.getProjectName());
        scopes.put("packageName", ctx.getPackageName());
        scopes.put("backend", ctx.getBackendType() != null ? ctx.getBackendType().name().toLowerCase() : "");
        scopes.put("frontend", ctx.getFrontendType() != null ? ctx.getFrontendType().name().toLowerCase() : "");
        scopes.put("database", ctx.getDatabaseType() != null ? ctx.getDatabaseType().name().toLowerCase() : "");
        scopes.put("auth", ctx.getAuthType() != null ? ctx.getAuthType().name().toLowerCase() : "");
        scopes.put("basePackage", ctx.getBasePackagePath());
        return scopes;
    }
}
