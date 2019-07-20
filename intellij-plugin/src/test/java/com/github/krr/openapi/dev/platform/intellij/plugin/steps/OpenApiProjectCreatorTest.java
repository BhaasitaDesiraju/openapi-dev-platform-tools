package com.github.krr.openapi.dev.platform.intellij.plugin.steps;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.maven.archetype.DefaultArchetypeManager;
import org.apache.maven.archetype.common.DefaultArchetypeArtifactManager;
import org.apache.maven.archetype.generator.ArchetypeGenerator;
import org.apache.maven.archetype.generator.DefaultArchetypeGenerator;
import org.springframework.util.ReflectionUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.testng.Assert.*;

public class OpenApiProjectCreatorTest {

  private static final String RANDOM_TEST_DIR_NAME = RandomStringUtils.randomAlphabetic(10);
  private File randomTestDir;

  @BeforeClass
  public void setup() throws IOException {
    File tempDirectory = FileUtils.getTempDirectory();
    randomTestDir = new File(tempDirectory, RANDOM_TEST_DIR_NAME);
    FileUtils.forceMkdir(randomTestDir);
  }

  @Test
  public void createTest() throws IOException {
    String projectName = RandomStringUtils.randomAlphabetic(10);
    ProjectParameters projectParameters = new ProjectParameters();
    projectParameters.setProjectName(projectName);
    projectParameters.setProjectLocation(randomTestDir.getAbsolutePath());
    projectParameters.setArtifactId(RandomStringUtils.randomAlphabetic(3));
    projectParameters.setGroupId(RandomStringUtils.randomAlphabetic(3));
    projectParameters.setVersion(RandomStringUtils.randomAlphabetic(3));
    OpenApiProjectCreator openApiProjectCreator = new OpenApiProjectCreator();
    openApiProjectCreator.create(projectParameters);
    //Assert that a folder has been created
    //try to create a dir and check if that exists
//    Assert.assertTrue()
  }

  //illegal arguments exception
  //give null parameters and

  @AfterClass
  public void teardown() throws IOException {
    //delete temp folders
    FileUtils.forceDelete(randomTestDir);
  }

  @Test
  public void mustSetDefaultManagerFields() {
    OpenApiProjectCreator openApiProjectCreator = new OpenApiProjectCreator();
    DefaultArchetypeManager manager = new DefaultArchetypeManager();
    openApiProjectCreator.setManagerFields(manager);
    Field generator = ReflectionUtils.findField(DefaultArchetypeManager.class, "generator");
    if (generator != null) {
      ReflectionUtils.makeAccessible(generator);
      Object archetypeGenerator = ReflectionUtils.getField(generator, manager);
      Assert.assertNotNull(archetypeGenerator);
    }
  }

  @Test
  public void mustSetArchetypeGeneratorFields() {
    OpenApiProjectCreator openApiProjectCreator = new OpenApiProjectCreator();
    DefaultArchetypeManager manager = new DefaultArchetypeManager();
    openApiProjectCreator.setArchetypeGeneratorFields(manager);
    Field archetypeArtifactManagerField = ReflectionUtils.findField(DefaultArchetypeArtifactManager.class,
            "archetypeArtifactManager");
    if (archetypeArtifactManagerField != null) {
      ReflectionUtils.makeAccessible(archetypeArtifactManagerField);
      Object archetypeArtifactManager = ReflectionUtils.getField(archetypeArtifactManagerField, manager);
      Assert.assertNotNull(archetypeArtifactManager);
    }
  }
}