package com.github.krr.openapi.dev.platform.intellij.plugin.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.projectRoots.Sdk;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

@Slf4j
public class OpenApiModuleType extends ModuleType {

    public OpenApiModuleType(@NotNull String id) {
        super(id);
    }

    @NotNull
    @Override
    public ModuleBuilder createModuleBuilder() {
        return new ModuleBuilder() {
            @Override
            public ModuleType getModuleType() {

                return new OpenApiModuleType("openApi");
            }
        };
    }

    @NotNull
    @Override
    public String getName() {
        return "Open API Project";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Open API Description";
    }

    @Override
    public Icon getNodeIcon(boolean isOpened) {
        //Icon for open api - tbd
        return null;
    }

    public boolean isValidSdk(@NotNull Module module, @Nullable final Sdk projectSdk) {
        return true;
    }
}
