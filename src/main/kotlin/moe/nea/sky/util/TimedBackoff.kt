package moe.nea.sky.util

import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.TimeMark
import kotlin.time.TimeSource

@ExperimentalTime
class TimedBackoff {
    private var timed: TimeMark? = null

    fun passedTime(): Duration {
        return timed?.elapsedNow() ?: Duration.INFINITE
    }

    fun markIfAtLeastPassed(time: Duration): Boolean {
        if (passedTime() >= time) {
            timed = TimeSource.Monotonic.markNow()
            return true
        }
        return false
    }

}