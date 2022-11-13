package moe.nea.sky.config

interface HaxConfigFishing {
    val neuHaxAutoFishEnable: Boolean
    // TODO:     val neuHaxAutoFishToggleButton: Int
    val neuHaxAutoFishDelayMinimum: Int
    val neuHaxAutoFishDelayMaximum: Int
    val neuHaxReengageFishingRod: Boolean
}