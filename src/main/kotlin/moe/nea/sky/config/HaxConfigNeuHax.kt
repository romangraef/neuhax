/*
 * Copyright (C) 2022 Linnea Gräf
 *
 * This file is part of NEUHax.
 *
 * NEUHax is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * NEUHax is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with NEUHax. If not, see <https://www.gnu.org/licenses/>.
 */

package moe.nea.sky.config

import com.google.gson.annotations.Expose
import io.github.moulberry.notenoughupdates.core.config.annotations.*

class HaxConfigNeuHax {

    @Expose
    @ConfigOption(
        name = "Auto Melody",
        desc = "Automatically play back melody songs"
    )
    @ConfigEditorBoolean
    @JvmField
    var autoMelody: Boolean = false

    @ConfigOption(name = "Yaw Snapping", desc = "")
    @ConfigEditorAccordion(id = 6)
    @JvmField
    var yawSnappingAccordion = false

    @Expose
    @ConfigOption(name = "Enable Yaw Snapping", desc = "Align your yaw with certain angles")
    @ConfigEditorBoolean
    @JvmField

    @ConfigAccordionId(id = 6)
    var yawSnapping = false

    @Expose
    @ConfigOption(name = "Release Distance", desc = "How much you have to overshoot an angle to release yaw snapping")
    @ConfigEditorSlider(minValue = 0F, maxValue = 180F, minStep = 1F)
    @ConfigAccordionId(id = 6)
    @JvmField

    var yawTightness = 90f

    @Expose
    @ConfigOption(name = "Intervals", desc = "In which intervals do you want to enable yaw snapping (45°, 90°, etc.)")
    @ConfigEditorSlider(minValue = 1F, maxValue = 180F, minStep = 1F)
    @ConfigAccordionId(id = 6)
    @JvmField
    var yawIntervals = 45f

    @JvmField
    @Expose
    @ConfigOption(name = "Yaw Overlay", desc = "Display your current yaw over your cursor")
    @ConfigEditorBoolean
    @ConfigAccordionId(id = 6)
    var displayYawOverlay = true
}