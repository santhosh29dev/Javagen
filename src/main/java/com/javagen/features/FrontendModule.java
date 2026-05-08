package com.javagen.features;

import com.javagen.core.ProjectContext;
import com.javagen.services.FileWriterService;

public class FrontendModule implements FeatureModule {

    private final FileWriterService fileWriter = new FileWriterService();

    @Override
    public void apply(ProjectContext ctx) {
        fileWriter.createDirectory(ctx, "frontend/src/utils");
        fileWriter.createDirectory(ctx, "frontend/public");
        fileWriter.writeFromTemplate("shared/.env.mustache", ctx, "frontend/.env");
        fileWriter.writeFromTemplate("shared/.env.example.mustache", ctx, "frontend/.env.example");
    }
}
