/*
 * Copyright 2015 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.fluid.computer.module.storage;

import org.terasology.computer.system.common.ComputerModuleRegistry;
import org.terasology.computer.ui.documentation.DocumentationBuilder;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.fluid.computer.module.inventory.FluidManipulatorCommonSystem;
import org.terasology.logic.config.ModuleConfigManager;
import org.terasology.registry.In;

@RegisterSystem(RegisterMode.ALWAYS)
public class FluidStorageModuleCommonSystem extends BaseComponentSystem {
    public static final String COMPUTER_FLUID_STORAGE_MODULE_TYPE = "FluidStorage";

    @In
    private ComputerModuleRegistry computerModuleRegistry;
    @In
    private ModuleConfigManager moduleConfigManager;

    @Override
    public void preBegin() {
        if (computerModuleRegistry != null
                && moduleConfigManager.getBooleanVariable("Fluid", "registerModule.storage", true)) {
            String fluidManipulatorModulePageId = DocumentationBuilder.getComputerModulePageId(FluidManipulatorCommonSystem.COMPUTER_FLUID_MODULE_TYPE);

            computerModuleRegistry.registerComputerModule(
                    COMPUTER_FLUID_STORAGE_MODULE_TYPE,
                    new FluidStorageComputerModule(COMPUTER_FLUID_STORAGE_MODULE_TYPE, "Fluid internal storage", 3, 10),
                    "This module allows storing items within the computer itself. Only one module of this type can be installed in a computer " +
                            "at a time. Player does not have access to the storage itself via user interface, however " +
                            "<h navigate:" + fluidManipulatorModulePageId + ">Fluid manipulator</h> module can be used to access it and store in an external " +
                            "storage.",
                    null);
        }
    }
}