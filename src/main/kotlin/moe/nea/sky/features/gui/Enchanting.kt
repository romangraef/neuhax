package moe.nea.sky.features.gui

import io.github.moulberry.notenoughupdates.NotEnoughUpdates
import io.github.moulberry.notenoughupdates.miscfeatures.EnchantingSolvers
import moe.nea.sky.config.HaxConfigEnchanting
import moe.nea.sky.util.TimedBackoff
import moe.nea.sky.util.middleClickOn
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.init.Items
import net.minecraft.inventory.ContainerChest
import net.minecraft.item.ItemStack
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.input.Keyboard
import org.slf4j.LoggerFactory
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
object Enchanting {

    @JvmStatic
    lateinit var solversInstance: EnchantingSolvers

    private val LOGGER = LoggerFactory.getLogger("NEUHaxEnchanting")!!
    private val accessible get() = solversInstance as AccessibleEnchantingSolvers

    interface AccessibleEnchantingSolvers {
        var chronomatronReplyIndex: Int
        val chronomatronOrder: List<String>
        var ultrasequencerReplayIndex: Int
        val ultraSequencerOrder: Map<Int, AccessibleUltrasequencerItem>
    }

    interface AccessibleUltrasequencerItem {
        val stack: ItemStack
        val containerIndex: Int
    }

    val config get() = NotEnoughUpdates.INSTANCE.config.enchantingSolvers as HaxConfigEnchanting

    val timer = TimedBackoff()

    @SubscribeEvent
    fun onGuiKeyPress(event: GuiScreenEvent.KeyboardInputEvent) {
        val guiChest = event.gui as? GuiChest ?: return
        val content = guiChest.inventorySlots as? ContainerChest ?: return
        if (!NotEnoughUpdates.INSTANCE.config.enchantingSolvers.enableEnchantingSolvers) return
        if (config.neuHaxSolveKeybinding != Keyboard.getEventKey()) return
        if (!timer.markIfAtLeastPassed(config.neuHaxTimeout.milliseconds)) return
        LOGGER.debug("Solver: ${EnchantingSolvers.currentSolver}")
        val timerStack = content.lowerChestInventory.getStackInSlot(content.lowerChestInventory.sizeInventory - 5)
        if (timerStack.item != Items.clock) return
        when (EnchantingSolvers.currentSolver) {
            EnchantingSolvers.SolverType.CHRONOMATRON -> {
                if (accessible.chronomatronReplyIndex in accessible.chronomatronOrder.indices) {
                    val itemToClickOn = accessible.chronomatronOrder[accessible.chronomatronReplyIndex]
                    LOGGER.debug("Attempting to click on chronomatron (index ${accessible.chronomatronReplyIndex}) (name: $itemToClickOn)")
                    accessible.chronomatronReplyIndex++
                    val slotToClickOn =
                        content.inventorySlots
                            .firstOrNull { it.stack?.displayName == itemToClickOn }
                            ?.slotNumber
                            ?: return
                    LOGGER.debug("Found item to click on at slot $slotToClickOn")
                    guiChest.inventorySlots.middleClickOn(slotToClickOn)
                }
            }

            EnchantingSolvers.SolverType.ULTRASEQUENCER -> {
                val nextUltraSequencerItem =
                    accessible.ultraSequencerOrder[accessible.ultrasequencerReplayIndex] ?: return
                LOGGER.debug("Attempting to click on ultrasequencer (index ${accessible.ultraSequencerOrder}) (slotindex ${nextUltraSequencerItem.containerIndex})")
                accessible.ultrasequencerReplayIndex++
                guiChest.inventorySlots.middleClickOn(nextUltraSequencerItem.containerIndex)
            }

            else -> return
        }
    }

}