/*
 * Copyright (C) 2022 Linnea Gräf
 *
 * This file is part of NEUHax.
 *
 * NEUHax is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * NEUHax is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with NEUHax. If not, see <https://www.gnu.org/licenses/>.
 */

package moe.nea.sky

import io.github.moulberry.notenoughupdates.NotEnoughUpdates
import moe.nea.sky.commands.NEUHaxCommand
import moe.nea.sky.config.HaxConfigNeuConfig
import moe.nea.sky.features.gui.Enchanting
import moe.nea.sky.features.gui.Melody
import moe.nea.sky.features.world.AutoFishing
import moe.nea.sky.features.world.YawSnapping
import moe.nea.sky.util.CommandActionRegistry
import net.minecraft.launchwrapper.Launch
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent


@Mod(
    modid = MODID,
    useMetadata = true,
    clientSideOnly = true,
    modLanguageAdapter = "cc.polyfrost.oneconfig.utils.KotlinLanguageAdapter"
)
object NEUHax {

    val neuHaxConfig get() = (NotEnoughUpdates.INSTANCE.config as HaxConfigNeuConfig).neuHax

    val deobf by lazy { Launch.blackboard["fml.deobfuscatedEnvironment"] == true }

    @EventHandler
    fun onInit(event: FMLPreInitializationEvent) {
        println("Deobf: $deobf")
    }

    @EventHandler
    fun onInit(event: FMLInitializationEvent) {
        listOf(
            Enchanting,
            AutoFishing,
            YawSnapping,
            Melody,
        ).forEach {
            MinecraftForge.EVENT_BUS.register(it)
        }
        ClientCommandHandler.instance.registerCommand(NEUHaxCommand)
        ClientCommandHandler.instance.registerCommand(CommandActionRegistry)
    }

}