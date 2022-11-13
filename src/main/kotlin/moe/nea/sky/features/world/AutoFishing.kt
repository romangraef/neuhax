/*
 * Copyright (C) 2022 Linnea Gräf
 *
 * This file is part of NEUHax.
 *
 * NEUHax is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * NEUHax is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with NEUHax. If not, see <https://www.gnu.org/licenses/>.
 */

package moe.nea.sky.features.world

import cc.polyfrost.oneconfig.utils.dsl.mc
import io.github.moulberry.notenoughupdates.NotEnoughUpdates
import io.github.moulberry.notenoughupdates.miscfeatures.FishingHelper
import moe.nea.sky.config.HaxConfigFishing
import net.minecraft.entity.projectile.EntityFishHook
import net.minecraft.init.Items
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import kotlin.random.Random

/**
 * Auto Fishing that integrates with NEUs [FishingHelper] to automatically reel in fish. It waits a random, configurable
 * amount before each catch and can automatically start fishing again.
 *
 * **Config**: [moe.nea.sky.mixin.config.MixinHaxConfigFishing]
 */
object AutoFishing {

    val config get() = NotEnoughUpdates.INSTANCE.config.fishing as HaxConfigFishing

    private fun randomDelay() = Random.Default.nextInt(
        config.neuHaxAutoFishDelayMinimum,
        config.neuHaxAutoFishDelayMaximum.coerceAtLeast(config.neuHaxAutoFishDelayMinimum)
    )

    private var delay = -1
    private var shouldReengage = false
    private var lastFishingHook: EntityFishHook? = null
    private fun shouldAutoFish(): Boolean {
        if (!config.neuHaxAutoFishEnable)
            return false
        if (delay == 0) {
            delay = -1
            return true
        }
        if (delay < 0)
            delay = randomDelay()
        delay -= 1
        return false
    }

    @SubscribeEvent
    fun onTick(ev: TickEvent.ClientTickEvent) {
        if (ev.phase != TickEvent.Phase.END) return
        val p = mc.thePlayer ?: return
        if (shouldReengage) {
            if (p.fishEntity == lastFishingHook) return
            if (p.fishEntity != null || p.heldItem?.item != Items.fishing_rod) {
                shouldReengage = false
                delay = -1
                return
            }
            if (shouldAutoFish()) {
                shouldReengage = false
                mc.playerController.sendUseItem(p, p.worldObj, p.heldItem)
            }
            return
        }
        if (FishingHelper.getInstance().warningState != FishingHelper.PlayerWarningState.FISH_HOOKED) return
        if (lastFishingHook != p.fishEntity && shouldAutoFish()) {
            mc.playerController.sendUseItem(p, p.worldObj, p.heldItem)
            lastFishingHook = p.fishEntity
            if (config.neuHaxReengageFishingRod) {
                shouldReengage = true
            }
        }
    }

}