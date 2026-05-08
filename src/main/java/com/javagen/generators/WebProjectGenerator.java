package com.javagen.generators;

import com.javagen.core.ProjectContext;
import com.javagen.services.FileWriterService;

public class WebProjectGenerator implements ProjectGenerator {

    private final FileWriterService fileWriter = new FileWriterService();

    @Override
    public void generate(ProjectContext ctx) {
        // Root directories
        fileWriter.createBaseDirectories(ctx,
            "backend",
            "backend/src/main/java/" + ctx.getBasePackagePath(),
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/controller",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/service",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/repository",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/dto",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/entity",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/config",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/exception",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/security",
            "backend/src/main/resources",
            "backend/src/test/java/" + ctx.getBasePackagePath(),
            "frontend",
            "frontend/src",
            "frontend/src/pages",
            "frontend/src/components",
            "frontend/src/services",
            "frontend/src/utils",
            "docker",
            "database"
        );

        String be = ctx.getBackendType().name().toLowerCase();
        String fe = ctx.getFrontendType() != null ? ctx.getFrontendType().name().toLowerCase() : "react";

        // Spring Boot backend templates
        if (ctx.getBackendType() == com.javagen.model.BackendType.SPRING) {
            fileWriter.writeFromTemplate("web/backend/spring/application.yml.mustache", ctx, "backend/src/main/resources/application.yml");
            fileWriter.writeFromTemplate("web/backend/spring/pom.xml.mustache", ctx, "backend/pom.xml");
            fileWriter.writeFromTemplate("web/backend/spring/Application.java.mustache", ctx,
                "backend/src/main/java/" + ctx.getBasePackagePath() + "/Application.java");
            fileWriter.writeFromTemplate("web/backend/spring/Controller.java.mustache", ctx,
                "backend/src/main/java/" + ctx.getBasePackagePath() + "/controller/HomeController.java");
            fileWriter.writeFromTemplate("web/backend/spring/Service.java.mustache", ctx,
                "backend/src/main/java/" + ctx.getBasePackagePath() + "/service/HomeService.java");
            fileWriter.writeFromTemplate("web/backend/spring/Repository.java.mustache", ctx,
                "backend/src/main/java/" + ctx.getBasePackagePath() + "/repository/HomeRepository.java");
            fileWriter.writeFromTemplate("web/backend/spring/Dto.java.mustache", ctx,
                "backend/src/main/java/" + ctx.getBasePackagePath() + "/dto/HomeDto.java");
            fileWriter.writeFromTemplate("web/backend/spring/Entity.java.mustache", ctx,
                "backend/src/main/java/" + ctx.getBasePackagePath() + "/entity/HomeEntity.java");
            fileWriter.writeFromTemplate("web/backend/spring/SecurityConfig.java.mustache", ctx,
                "backend/src/main/java/" + ctx.getBasePackagePath() + "/config/SecurityConfig.java");
            fileWriter.writeFromTemplate("web/backend/spring/GlobalExceptionHandler.java.mustache", ctx,
                "backend/src/main/java/" + ctx.getBasePackagePath() + "/exception/GlobalExceptionHandler.java");
        }

        // React frontend
        if (ctx.getFrontendType() == com.javagen.model.FrontendType.REACT) {
            fileWriter.writeFromTemplate("web/frontend/react/package.json.mustache", ctx, "frontend/package.json");
            fileWriter.writeFromTemplate("web/frontend/react/vite.config.js.mustache", ctx, "frontend/vite.config.js");
            fileWriter.writeFromTemplate("web/frontend/react/index.html.mustache", ctx, "frontend/index.html");
            fileWriter.writeFromTemplate("web/frontend/react/App.jsx.mustache", ctx, "frontend/src/App.jsx");
            fileWriter.writeFromTemplate("web/frontend/react/main.jsx.mustache", ctx, "frontend/src/main.jsx");
            fileWriter.writeFromTemplate("web/frontend/react/api.js.mustache", ctx, "frontend/src/services/api.js");
        }

        // Angular frontend
        if (ctx.getFrontendType() == com.javagen.model.FrontendType.ANGULAR) {
            fileWriter.writeFromTemplate("web/frontend/angular/package.json.mustache", ctx, "frontend/package.json");
            fileWriter.writeFromTemplate("web/frontend/angular/angular.json.mustache", ctx, "frontend/angular.json");
            fileWriter.writeFromTemplate("web/frontend/angular/app.component.ts.mustache", ctx, "frontend/src/app/app.component.ts");
            fileWriter.writeFromTemplate("web/frontend/angular/app.module.ts.mustache", ctx, "frontend/src/app/app.module.ts");
            fileWriter.writeFromTemplate("web/frontend/angular/api.service.ts.mustache", ctx, "frontend/src/app/services/api.service.ts");
        }

        // Vue frontend
        if (ctx.getFrontendType() == com.javagen.model.FrontendType.VUE) {
            fileWriter.writeFromTemplate("web/frontend/vue/package.json.mustache", ctx, "frontend/package.json");
            fileWriter.writeFromTemplate("web/frontend/vue/vite.config.js.mustache", ctx, "frontend/vite.config.js");
            fileWriter.writeFromTemplate("web/frontend/vue/App.vue.mustache", ctx, "frontend/src/App.vue");
            fileWriter.writeFromTemplate("web/frontend/vue/main.js.mustache", ctx, "frontend/src/main.js");
            fileWriter.writeFromTemplate("web/frontend/vue/api.js.mustache", ctx, "frontend/src/services/api.js");
        }

        // Shared
        fileWriter.writeFromTemplate("shared/README.md.mustache", ctx, "README.md");
        fileWriter.writeFromTemplate("shared/.gitignore.mustache", ctx, ".gitignore");
    }
}
