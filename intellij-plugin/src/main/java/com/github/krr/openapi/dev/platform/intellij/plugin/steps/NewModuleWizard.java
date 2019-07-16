package com.github.krr.openapi.dev.platform.intellij.plugin.steps;

import com.github.krr.openapi.dev.platform.intellij.plugin.module.OpenApiModuleType;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.util.ui.JBUI;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

import static groovy.ui.text.FindReplaceUtility.dispose;

@Slf4j
public class NewModuleWizard extends ModuleBuilder {

    private JTextField groupId;
    private JTextField artifactId;
    private JTextField version;

    @Override
    public ModuleType getModuleType() {
        return new OpenApiModuleType("openApiModule");
    }

    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext,
                                                @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[]{
                new ModuleWizardStep() {
                    @Override
                    public JComponent getComponent() {

                        JPanel mainJPanel = new JPanel(new BorderLayout());
                        JPanel jPanel = new JPanel(new GridBagLayout());
                        GridBagConstraints gridBagConstraints = new GridBagConstraints();

                        //Group id label
                        setGridBagConstraints(0, 0, 0, 1, gridBagConstraints);
                        JLabel groupIdLabel = new JLabel("Group ID:");
                        jPanel.add(groupIdLabel, gridBagConstraints);
                        //Group id text field
                        groupId = new JTextField();
                        setGridBagConstraints(1, 0, 1, GridBagConstraints.REMAINDER, gridBagConstraints);
                        jPanel.add(groupId, gridBagConstraints);

                        //Artifact id label
                        setGridBagConstraints(0, 1, 0, 1, gridBagConstraints);
                        jPanel.add(new JLabel("Artifact ID:"), gridBagConstraints);
                        //Artifact id text field
                        artifactId = new JTextField();
                        setGridBagConstraints(1, 1, 1, GridBagConstraints.REMAINDER, gridBagConstraints);
                        jPanel.add(artifactId, gridBagConstraints);

                        //Version label
                        setGridBagConstraints(0, 2, 0, 1, gridBagConstraints);
                        jPanel.add(new JLabel("Version:"), gridBagConstraints);
                        //Version text field
                        version = new JTextField();
                        setGridBagConstraints(1, 2, 1, GridBagConstraints.REMAINDER, gridBagConstraints);
                        jPanel.add(version, gridBagConstraints);

                        //Adding inner grid panel to main border panel
                        mainJPanel.add(jPanel, BorderLayout.NORTH);
                        return mainJPanel;
                    }

                    @Override
                    public void updateDataModel() {

                    }

                    @SuppressWarnings("unused")
                    @Override
                    public void onWizardFinished() throws CommitStepException {
                        super.onWizardFinished();
                        String groupIdText = groupId.getText();
                        String artifactIdText = artifactId.getText();
                        String versionText = version.getText();
                    }

                    @Override
                    public void disposeUIResources() {
                        dispose();
                    }
                }};
    }

    public void setName(String name) {
        String myName = name;
    }

    //Set Constraints for GridBag layout panel
    private void setGridBagConstraints(int gridX, int gridY, int weightX, int gridWidth,
                                       GridBagConstraints gridBagConstraints) {
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = JBUI.insets(2);
        gridBagConstraints.gridx = gridX;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.weightx = weightX;
        gridBagConstraints.gridwidth = gridWidth;
    }
}
