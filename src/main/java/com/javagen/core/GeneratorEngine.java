package com.javagen.core;

import com.javagen.features.*;
import com.javagen.generators.*;
import com.javagen.model.ProjectType;

import java.util.ArrayList;
import java.util.List;

public class GeneratorEngine {

    public void generate(ProjectContext context) {
        ProjectGenerator projectGenerator = resolveGenerator(context.getProjectType());
        List<FeatureModule> features = resolveFeatures(context);

        projectGenerator.generate(context);
        for (FeatureModule feature : features) {
            feature.apply(context);
        }
    }

    private ProjectGenerator resolveGenerator(ProjectType type) {
        return switch (type) {
            case WEB -> new WebProjectGenerator();
            case DESKTOP -> new DesktopProjectGenerator();
            case APP -> new AppProjectGenerator();
        };
    }

    private List<FeatureModule> resolveFeatures(ProjectContext ctx) {
        List<FeatureModule> features = new ArrayList<>();

        if (ctx.getBackendType() != null) {
            features.add(new BackendModule());
        }
        if (ctx.getFrontendType() != null && ctx.getProjectType() == ProjectType.WEB) {
            features.add(new FrontendModule());
        }
        if (ctx.getDatabaseType() != null) {
            features.add(new DatabaseModule());
        }
        if (ctx.getAuthType() != null) {
            features.add(new AuthModule());
        }
        if (ctx.isDockerEnabled()) {
            features.add(new DockerModule());
        }
        if (ctx.isSwaggerEnabled()) {
            features.add(new SwaggerModule());
        }

        return features;
    }
}
