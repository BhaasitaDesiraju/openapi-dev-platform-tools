package com.github.krr.openapi.dev.platform.intellij.plugin.steps;

import lombok.Data;

@Data
public class ProjectParameters {

  private String projectName;
  private String projectLocation;
  private String groupId;
  private String artifactId;
  private String version;

}
