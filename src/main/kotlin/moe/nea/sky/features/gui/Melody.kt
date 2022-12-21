/*
 * Copyright (C) 2022 Linnea Gräf
 *
 * This file is part of NEUHax.
 *
 * NEUHax is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * NEUHax is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with NEUHax. If not, see <https://www.gnu.org/licenses/>.
 */

package moe.nea.sky.features.gui

import cc.polyfrost.oneconfig.utils.dsl.mc
import moe.nea.sky.LOGGER
import moe.nea.sky.NEUHax
import moe.nea.sky.util.middleClickOn
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.init.Blocks
import net.minecraft.inventory.ContainerChest
import net.minecraft.item.Item
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent


object Melody {

    var lastInventoryHashCode = 0

    @SubscribeEvent
    fun onGuiTick(event: ClientTickEvent) {
        if (!NEUHax.neuHaxConfig.autoMelody) return
        if (event.phase == TickEvent.Phase.START) return
        val guiChest = mc.currentScreen as? GuiChest ?: return
        val content = guiChest.inventorySlots as? ContainerChest ?: return
        if (!content.lowerChestInventory.displayName.unformattedText.startsWith("Harp ")) return
        val inventoryHashCode = content.inventory.hashCode()
        if (inventoryHashCode == lastInventoryHashCode) return
        lastInventoryHashCode = inventoryHashCode
        for (i in 0 until content.lowerChestInventory.sizeInventory) {
            val s = content.lowerChestInventory.getStackInSlot(i) ?: continue
            if (s.item == Item.getItemFromBlock(Blocks.quartz_block)) {
                content.middleClickOn(i)
                break
            }
        }
    }


}