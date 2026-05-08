package com.javagen.features;

import com.javagen.core.ProjectContext;
import com.javagen.services.FileWriterService;

public class AuthModule implements FeatureModule {

    private final FileWriterService fileWriter = new FileWriterService();

    @Override
    public void apply(ProjectContext ctx) {
        fileWriter.createDirectory(ctx, "backend/src/main/java/" + ctx.getBasePackagePath() + "/security");
        fileWriter.writeFromTemplate("shared/JwtUtil.java.mustache", ctx,
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/security/JwtUtil.java");
        fileWriter.writeFromTemplate("shared/AuthController.java.mustache", ctx,
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/controller/AuthController.java");
        fileWriter.writeFromTemplate("shared/UserService.java.mustache", ctx,
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/service/UserService.java");
    }
}
