package moe.nea.sky

import moe.nea.sky.commands.NEUHaxCommand
import moe.nea.sky.features.gui.Enchanting
import moe.nea.sky.features.world.AutoFishing
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
        ).forEach {
            MinecraftForge.EVENT_BUS.register(it)
        }
        ClientCommandHandler.instance.registerCommand(NEUHaxCommand)
        ClientCommandHandler.instance.registerCommand(CommandActionRegistry)
    }

}