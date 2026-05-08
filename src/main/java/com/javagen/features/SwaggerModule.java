package com.javagen.features;

import com.javagen.core.ProjectContext;
import com.javagen.services.FileWriterService;

public class SwaggerModule implements FeatureModule {

    private final FileWriterService fileWriter = new FileWriterService();

    @Override
    public void apply(ProjectContext ctx) {
        fileWriter.writeFromTemplate("shared/OpenApiConfig.java.mustache", ctx,
            "backend/src/main/java/" + ctx.getBasePackagePath() + "/config/OpenApiConfig.java");
        fileWriter.writeFromTemplate("shared/application-swagger.yml.mustache", ctx,
            "backend/src/main/resources/application-swagger.yml");
    }
}
