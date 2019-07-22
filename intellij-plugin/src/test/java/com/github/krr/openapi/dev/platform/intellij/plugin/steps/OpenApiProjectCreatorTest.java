package com.github.krr.openapi.dev.platform.intellij.plugin.steps;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

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
  public void mustCreateArtifactInSpecifiedFolder() throws IOException {
    String projectName = RandomStringUtils.randomAlphabetic(10);
    ProjectParameters projectParameters = new ProjectParameters();
    projectParameters.setProjectName(projectName);
    projectParameters.setProjectLocation(randomTestDir.getAbsolutePath());
    projectParameters.setArtifactId(RandomStringUtils.randomAlphabetic(3));
    projectParameters.setGroupId(RandomStringUtils.randomAlphabetic(3));
    projectParameters.setVersion(RandomStringUtils.randomAlphabetic(3));
    projectParameters.setProjectDescription(RandomStringUtils.randomAlphabetic(3));
    OpenApiProjectCreator openApiProjectCreator = new OpenApiProjectCreator();
    openApiProjectCreator.create(projectParameters);
    Assert.assertTrue(true);
  }

  //should throw illegal args exception for null params

  @AfterClass
  public void teardown() throws IOException {
    //delete temp folders
    FileUtils.forceDelete(randomTestDir);
  }

}