package com.javagen.features;

import com.javagen.core.ProjectContext;
import com.javagen.services.FileWriterService;

public class DockerModule implements FeatureModule {

    private final FileWriterService fileWriter = new FileWriterService();

    @Override
    public void apply(ProjectContext ctx) {
        fileWriter.writeFromTemplate("shared/Dockerfile.mustache", ctx, "docker/Dockerfile");
        fileWriter.writeFromTemplate("shared/docker-compose.yml.mustache", ctx, "docker-compose.yml");
        fileWriter.writeFromTemplate("shared/.env.mustache", ctx, ".env");
        fileWriter.writeFromTemplate("shared/nginx.conf.mustache", ctx, "docker/nginx.conf");
    }
}
