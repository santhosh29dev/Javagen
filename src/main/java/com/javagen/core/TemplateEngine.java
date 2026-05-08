package com.javagen.core;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.*;
import java.nio.file.*;
import java.util.Map;

public class TemplateEngine {

    private final MustacheFactory mustacheFactory;

    public TemplateEngine() {
        this.mustacheFactory = new DefaultMustacheFactory();
    }

    public String renderToString(String templateName, Map<String, Object> scopes) {
        try {
            Mustache mustache = mustacheFactory.compile("templates/" + templateName);
            StringWriter writer = new StringWriter();
            mustache.execute(writer, scopes);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to render template: " + templateName, e);
        }
    }

    public void renderToFile(String templateName, Map<String, Object> scopes, Path outputPath) {
        try {
            Files.createDirectories(outputPath.getParent());
            String content = renderToString(templateName, scopes);
            Files.writeString(outputPath, content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file: " + outputPath, e);
        }
    }

    public void copyTemplate(String templatePath, Path outputPath) {
        try (InputStream is = getClass().getResourceAsStream("/templates/" + templatePath)) {
            if (is == null) {
                System.err.println("Warning: template not found: " + templatePath);
                return;
            }
            Files.createDirectories(outputPath.getParent());
            Files.copy(is, outputPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to copy template: " + templatePath, e);
        }
    }
}
