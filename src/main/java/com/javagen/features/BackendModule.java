package com.javagen.features;

import com.javagen.core.ProjectContext;
import com.javagen.services.FileWriterService;

public class BackendModule implements FeatureModule {

    private final FileWriterService fileWriter = new FileWriterService();

    @Override
    public void apply(ProjectContext ctx) {
        fileWriter.createDirectory(ctx, "backend/src/main/java/" + ctx.getBasePackagePath() + "/config");
        fileWriter.writeFromTemplate("shared/logback.xml.mustache", ctx, "backend/src/main/resources/logback.xml");
        fileWriter.writeFromTemplate("shared/application-local.yml.mustache", ctx, "backend/src/main/resources/application-local.yml");
    }
}
