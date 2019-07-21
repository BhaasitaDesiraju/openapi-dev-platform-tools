package com.github.krr.openapi.dev.platform.intellij.plugin.steps;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.cli.MavenCli;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.util.Properties;

public class OpenApiProjectCreator {


  private static final String ARCHETYPE_GROUP_ID = "com.github.krraghavan";
  private static final String ARCHETYPE_ARTIFACT_ID = "openapi-api-definition-archetype";
  private static final String ARCHETYPE_VERSION = "0.1.3-SNAPSHOT";

  public void create(ProjectParameters projectParameters) throws MalformedURLException {
    String projectLocation = projectParameters.getProjectLocation();
    String groupId = projectParameters.getGroupId();
    String artifactId = projectParameters.getArtifactId();
    String version = projectParameters.getVersion();


    MavenCli mavenCli = new MavenCli();
    String projectDescription = projectParameters.getProjectDescription();
    String projectName = projectParameters.getProjectName();
    if(StringUtils.isEmpty(projectDescription)) {
      projectDescription = "NoDescription";
    }
    if(StringUtils.isEmpty(projectName)) {
      projectName = "MyProject";
    }
    String [] args = new String[]{
        "-DarchetypeGroupId=".concat(ARCHETYPE_GROUP_ID),
        "-DarchetypeArtifactId=".concat(ARCHETYPE_ARTIFACT_ID),
        "-DarchetypeVersion=".concat(ARCHETYPE_VERSION),
        "-DartifactId=".concat(artifactId),
        "-DgroupId=".concat(groupId),
        "-Dversion=".concat(version),
        "-DprojectName=".concat(projectName),
        "-DprojectDescription=".concat(projectDescription),
        "-B",
        "archetype:generate"
    };
    System.setProperty("maven.multiModuleProjectDirectory", projectLocation);
    int retval = mavenCli.doMain(args, projectLocation, System.out, System.err);
//
//    DefaultArchetypeManager manager = new DefaultArchetypeManager();
//    Properties properties = createProjectAdditionalProperties(projectParameters);
//    ArchetypeGenerationRequest archetypeGenerationRequest = new ArchetypeGenerationRequest();
//    ArchetypeGenerationRequest request =
//            archetypeGenerationRequest.setArchetypeGroupId("com.github.krraghavan")
//                    .setArchetypeArtifactId("openapi-api-definition-archetype")
//                    .setArchetypeVersion("0.1.3-SNAPSHOT")
//                    .setFilter("com.github.krraghavan:openapi-api-definition-archetype")
////                    .setLocalRepository(localRepository)
//                    .setOutputDirectory(projectLocation)
//                    .setArtifactId(artifactId)
//                    .setGroupId(groupId)
//                    .setVersion(version)
//                    .setProperties(properties);
//    setManagerFields(manager);
//    setArchetypeGeneratorFields(manager);
//    ArchetypeGenerationResult generationResult = manager.generateProjectFromArchetype(request);

  }

//  void setManagerFields(DefaultArchetypeManager manager) {
//    //set generator
//    Field generatorField = ReflectionUtils.findField(DefaultArchetypeManager.class, "generator");
//    if (generatorField != null) {
//      ReflectionUtils.makeAccessible(generatorField);
//      ReflectionUtils.setField(generatorField, manager, new DefaultArchetypeGenerator());
//    }
//  }
//
//  void setArchetypeGeneratorFields(DefaultArchetypeManager manager) {
//    setManagerFields(manager);
//    Field generator = ReflectionUtils.findField(DefaultArchetypeManager.class, "generator");
//    if (generator != null) {
//      ReflectionUtils.makeAccessible(generator);
//      Object archetypeGenerator = ReflectionUtils.getField(generator, manager);
//      //set ArchetypeArtifactManager
//      Field archetypeArtifactManagerField = ReflectionUtils.findField(DefaultArchetypeGenerator.class,
//              "archetypeArtifactManager");
//      if (archetypeArtifactManagerField != null) {
//        ReflectionUtils.makeAccessible(archetypeArtifactManagerField);
//        ReflectionUtils.setField(archetypeArtifactManagerField,
//                archetypeGenerator, new DefaultArchetypeArtifactManager());
//      }
//    }
//  }

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

