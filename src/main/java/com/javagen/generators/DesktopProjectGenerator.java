package com.javagen.generators;

import com.javagen.core.ProjectContext;
import com.javagen.services.FileWriterService;

public class DesktopProjectGenerator implements ProjectGenerator {

    private final FileWriterService fileWriter = new FileWriterService();

    @Override
    public void generate(ProjectContext ctx) {
        fileWriter.createBaseDirectories(ctx,
            "src/main/java/" + ctx.getBasePackagePath(),
            "src/main/java/" + ctx.getBasePackagePath() + "/controller",
            "src/main/java/" + ctx.getBasePackagePath() + "/service",
            "src/main/java/" + ctx.getBasePackagePath() + "/repository",
            "src/main/java/" + ctx.getBasePackagePath() + "/model",
            "src/main/java/" + ctx.getBasePackagePath() + "/view",
            "src/main/java/" + ctx.getBasePackagePath() + "/config",
            "src/main/resources"
        );

        String fw = ctx.getBackendType() != null ? ctx.getBackendType().name().toLowerCase() : "javafx";

        fileWriter.writeFromTemplate("desktop/pom.xml.mustache", ctx, "pom.xml");
        fileWriter.writeFromTemplate("desktop/Main.java.mustache", ctx,
            "src/main/java/" + ctx.getBasePackagePath() + "/Main.java");
        fileWriter.writeFromTemplate("desktop/Controller.java.mustache", ctx,
            "src/main/java/" + ctx.getBasePackagePath() + "/controller/MainController.java");
        fileWriter.writeFromTemplate("desktop/Service.java.mustache", ctx,
            "src/main/java/" + ctx.getBasePackagePath() + "/service/MainService.java");

        fileWriter.writeFromTemplate("shared/README.md.mustache", ctx, "README.md");
        fileWriter.writeFromTemplate("shared/.gitignore.mustache", ctx, ".gitignore");
    }
}
