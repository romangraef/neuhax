package moe.nea.sky.util

import cc.polyfrost.oneconfig.utils.dsl.mc
import net.minecraft.inventory.Container


fun Container.middleClickOn(slotToClickOn: Int) {
    mc.playerController.windowClick(
        windowId, slotToClickOn, 2, 3, mc.thePlayer
    )
}
