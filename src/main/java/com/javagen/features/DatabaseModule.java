package com.javagen.features;

import com.javagen.core.ProjectContext;
import com.javagen.services.FileWriterService;

public class DatabaseModule implements FeatureModule {

    private final FileWriterService fileWriter = new FileWriterService();

    @Override
    public void apply(ProjectContext ctx) {
        fileWriter.createDirectory(ctx, "database/migrations");
        fileWriter.createDirectory(ctx, "database/seeds");
        fileWriter.writeFromTemplate("shared/init.sql.mustache", ctx, "database/init.sql");
        fileWriter.writeFromTemplate("shared/application-db.yml.mustache", ctx, "backend/src/main/resources/application-db.yml");
    }
}
