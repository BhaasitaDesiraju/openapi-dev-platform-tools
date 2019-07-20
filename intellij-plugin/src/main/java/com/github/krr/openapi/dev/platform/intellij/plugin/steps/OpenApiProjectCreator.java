package com.github.krr.openapi.dev.platform.intellij.plugin.steps;

import org.apache.maven.archetype.ArchetypeGenerationRequest;
import org.apache.maven.archetype.ArchetypeGenerationResult;
import org.apache.maven.archetype.DefaultArchetypeManager;
import org.apache.maven.archetype.common.DefaultArchetypeArtifactManager;
import org.apache.maven.archetype.generator.DefaultArchetypeGenerator;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.repository.ArtifactRepositoryPolicy;
import org.apache.maven.artifact.repository.MavenArtifactRepository;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.Properties;

public class OpenApiProjectCreator {


  public void create(ProjectParameters projectParameters) throws MalformedURLException {
    String projectLocation = projectParameters.getProjectLocation();
    String groupId = projectParameters.getGroupId();
    String artifactId = projectParameters.getArtifactId();
    String version = projectParameters.getVersion();

    DefaultArchetypeManager manager = new DefaultArchetypeManager();
    Properties properties = createProjectAdditionalProperties(projectParameters);
    ArchetypeGenerationRequest archetypeGenerationRequest = new ArchetypeGenerationRequest();
    ArchetypeGenerationRequest request =
            archetypeGenerationRequest.setArchetypeGroupId("com.github.krraghavan")
                    .setArchetypeArtifactId("openapi-api-definition-archetype")
                    .setArchetypeVersion("0.1.3-SNAPSHOT")
                    .setFilter("com.github.krraghavan:openapi-api-definition-archetype")
//                    .setLocalRepository(localRepository)
                    .setOutputDirectory(projectLocation)
                    .setArtifactId(artifactId)
                    .setGroupId(groupId)
                    .setVersion(version)
                    .setProperties(properties);
    setManagerFields(manager);
    setArchetypeGeneratorFields(manager);
    ArchetypeGenerationResult generationResult = manager.generateProjectFromArchetype(request);

  }

  void setManagerFields(DefaultArchetypeManager manager) {
    //set generator
    Field generatorField = ReflectionUtils.findField(DefaultArchetypeManager.class, "generator");
    if (generatorField != null) {
      ReflectionUtils.makeAccessible(generatorField);
      ReflectionUtils.setField(generatorField, manager, new DefaultArchetypeGenerator());
    }
  }

  void setArchetypeGeneratorFields(DefaultArchetypeManager manager) {
    setManagerFields(manager);
    Field generator = ReflectionUtils.findField(DefaultArchetypeManager.class, "generator");
    if (generator != null) {
      ReflectionUtils.makeAccessible(generator);
      Object archetypeGenerator = ReflectionUtils.getField(generator, manager);
      //set ArchetypeArtifactManager
      Field archetypeArtifactManagerField = ReflectionUtils.findField(DefaultArchetypeGenerator.class,
              "archetypeArtifactManager");
      if (archetypeArtifactManagerField != null) {
        ReflectionUtils.makeAccessible(archetypeArtifactManagerField);
        ReflectionUtils.setField(archetypeArtifactManagerField,
                archetypeGenerator, new DefaultArchetypeArtifactManager());
      }
    }
  }

  @NotNull
  private Properties createProjectAdditionalProperties(ProjectParameters projectParameters) {
    //what other properties????
    Properties properties = new Properties();
    properties.setProperty("Project Name", projectParameters.getProjectName());
//    properties.setProperty("Project Description", projectParameters.getProjectDescription());
    //should i add projectDesc to Project Params class???
    return properties;
  }

}

