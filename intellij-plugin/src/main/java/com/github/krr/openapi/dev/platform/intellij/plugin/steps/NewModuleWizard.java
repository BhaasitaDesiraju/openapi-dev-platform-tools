package com.github.krr.openapi.dev.platform.intellij.plugin.steps;

import com.github.krr.openapi.dev.platform.intellij.plugin.module.OpenApiModuleType;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.util.ui.JBUI;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class NewModuleWizard extends ModuleBuilder {

  @Override
  public ModuleType getModuleType() {
    return new OpenApiModuleType("openApiModule");
  }

  @Override
  public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
    return new ModuleWizardStep[]{new ModuleWizardStep() {
      @Override
      public JComponent getComponent() {
        JPanel mainJPanel = new JPanel(new BorderLayout());

        JPanel jPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = JBUI.insets(1);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0;
        jPanel.add(new JLabel("Group ID:"), gridBagConstraints);
        JTextField groupId = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        jPanel.add(groupId, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0;
        jPanel.add(new JLabel("Artifact ID:"), gridBagConstraints);
        JTextField artifactId = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        jPanel.add(artifactId, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0;
        jPanel.add(new JLabel("Version:"), gridBagConstraints);
        JTextField version = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        jPanel.add(version, gridBagConstraints);

        mainJPanel.add(jPanel, BorderLayout.NORTH);
        return mainJPanel;
      }

      @Override
      public void updateDataModel() {

      }
    }};
  }
}
