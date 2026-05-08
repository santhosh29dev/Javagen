package com.javagen.core;

import com.javagen.model.*;

public class ProjectContext {

    private String projectName;
    private String packageName;
    private ProjectType projectType;
    private BackendType backendType;
    private FrontendType frontendType;
    private DatabaseType databaseType;
    private AuthType authType;
    private boolean dockerEnabled;
    private boolean swaggerEnabled;
    private boolean redisEnabled;
    private boolean kafkaEnabled;
    private String outputPath;

    public ProjectContext(String projectName) {
        this.projectName = projectName;
        this.packageName = "com." + projectName.toLowerCase().replaceAll("[^a-z0-9]", "");
        this.outputPath = System.getProperty("user.dir") + "/" + projectName;
    }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) { this.packageName = packageName; }

    public ProjectType getProjectType() { return projectType; }
    public void setProjectType(ProjectType projectType) { this.projectType = projectType; }

    public BackendType getBackendType() { return backendType; }
    public void setBackendType(BackendType backendType) { this.backendType = backendType; }

    public FrontendType getFrontendType() { return frontendType; }
    public void setFrontendType(FrontendType frontendType) { this.frontendType = frontendType; }

    public DatabaseType getDatabaseType() { return databaseType; }
    public void setDatabaseType(DatabaseType databaseType) { this.databaseType = databaseType; }

    public AuthType getAuthType() { return authType; }
    public void setAuthType(AuthType authType) { this.authType = authType; }

    public boolean isDockerEnabled() { return dockerEnabled; }
    public void setDockerEnabled(boolean dockerEnabled) { this.dockerEnabled = dockerEnabled; }

    public boolean isSwaggerEnabled() { return swaggerEnabled; }
    public void setSwaggerEnabled(boolean swaggerEnabled) { this.swaggerEnabled = swaggerEnabled; }

    public boolean isRedisEnabled() { return redisEnabled; }
    public void setRedisEnabled(boolean redisEnabled) { this.redisEnabled = redisEnabled; }

    public boolean isKafkaEnabled() { return kafkaEnabled; }
    public void setKafkaEnabled(boolean kafkaEnabled) { this.kafkaEnabled = kafkaEnabled; }

    public String getOutputPath() { return outputPath; }
    public void setOutputPath(String outputPath) { this.outputPath = outputPath; }

    public String getBasePackagePath() {
        return packageName.replace('.', '/');
    }
}
