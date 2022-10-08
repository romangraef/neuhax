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