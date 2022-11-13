/*
 * Copyright (C) 2022 Linnea Gräf
 *
 * This file is part of NEUHax.
 *
 * NEUHax is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * NEUHax is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with NEUHax. If not, see <https://www.gnu.org/licenses/>.
 */

package moe.nea.sky.commands

import moe.nea.sky.util.showMessage
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraftforge.fml.common.FMLCommonHandler

object NEUHaxCommand : CommandBase() {
    override fun getCommandName(): String = "neuhax"
    override fun getCommandAliases(): List<String> = listOf("nh")
    override fun canCommandSenderUseCommand(sender: ICommandSender?): Boolean = true
    override fun getCommandUsage(sender: ICommandSender): String = "/neuhax help"

    fun sendHelp(target: ICommandSender) {
        target.showMessage {
            text("There is currently no help for you.")
            text("Check back later!")
            text("Click here for fun").clickable("Trust me") {
                FMLCommonHandler.instance().handleExit(-1651473007)
                FMLCommonHandler.instance().expectServerStopped()
            }
        }
    }

    override fun processCommand(sender: ICommandSender, args: Array<out String>) {
        val verb = args.singleOrNull()
        when (verb) {
            else -> {
                sendHelp(sender)
            }
        }
    }

}