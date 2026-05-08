package com.javagen.generators;

import com.javagen.core.ProjectContext;
import com.javagen.services.FileWriterService;

public class AppProjectGenerator implements ProjectGenerator {

    private final FileWriterService fileWriter = new FileWriterService();

    @Override
    public void generate(ProjectContext ctx) {
        fileWriter.createBaseDirectories(ctx,
            "backend",
            "backend/src/main/java/" + ctx.getBasePackagePath(),
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/controller",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/service",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/repository",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/dto",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/entity",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/config",
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/security",
            "backend/src/main/resources",
            "mobile",
            "mobile/lib",
            "mobile/lib/screens",
            "mobile/lib/services",
            "mobile/lib/models",
            "mobile/lib/widgets"
        );

        fileWriter.writeFromTemplate("app/backend/pom.xml.mustache", ctx, "backend/pom.xml");
        fileWriter.writeFromTemplate("app/backend/Application.java.mustache", ctx,
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/Application.java");
        fileWriter.writeFromTemplate("app/backend/Controller.java.mustache", ctx,
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/controller/HomeController.java");
        fileWriter.writeFromTemplate("app/backend/Service.java.mustache", ctx,
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/service/HomeService.java");

        fileWriter.writeFromTemplate("app/mobile/pubspec.yaml.mustache", ctx, "mobile/pubspec.yaml");
        fileWriter.writeFromTemplate("app/mobile/main.dart.mustache", ctx, "mobile/lib/main.dart");
        fileWriter.writeFromTemplate("app/mobile/api_service.dart.mustache", ctx, "mobile/lib/services/api_service.dart");

        fileWriter.writeFromTemplate("shared/README.md.mustache", ctx, "README.md");
        fileWriter.writeFromTemplate("shared/.gitignore.mustache", ctx, ".gitignore");
    }
}
