package furhatos.app.advanced.flow.main

import furhat.libraries.standard.NluLib
import furhatos.app.advanced.flow.PowerSaving
import furhatos.app.advanced.setting.fallASleep
import furhatos.app.advanced.responses.gestures.reset
import furhatos.app.advanced.setting.DeepSleepWhenNeglected
import furhatos.app.advanced.setting.NapHeadMovements
import furhatos.app.advanced.setting.downMax
import furhatos.app.advanced.setting.isAttended
import furhatos.app.advanced.setting.wakeUp
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Greeting

/**
 * Tip!
 *
 * Use multiple sleep states to set more complex behavior when no users are present.
 *
 * */

/**
 * Light sleep. Listen for wakeup commands when users are present and looking at Furhat
 */
val Nap: State = state {
    include(NapHeadMovements)
    include(DeepSleepWhenNeglected)

    onEntry { furhat.fallASleep() }

    onReentry {
        if (furhat.isAttended()) {
            furhat.listen()
        }
    }

    onResponse<Greeting> { // This will listen for a greeting response by the user.
        raise(NluLib.WakeUp()) // Raise another intent to handle pass it on to that trigger handler.
    }

    onResponse<NluLib.WakeUp> { // Listen for either a wake-up- or a greeting intent.
        furhat.wakeUp()
        when {
            users.hasAny() -> goto(Active)
            !users.hasAny() -> goto(Idle)
        }
    }

    // On each of these events onReentry is going to execute.
    onResponse { reentry() }
    onUserAttend { reentry() }
    onNoResponse { reentry() }
    onNetworkFailed { reentry() }
    onResponseFailed { reentry() }
}

/**
 * Deep sleep. Can only wake up from wizard buttons in the web interface.
 */
val DeepSleep: State = state(PowerSaving) {
    onEntry {
        furhat.fallASleep()
        furhat.attend(downMax) // Make head fall even lower, when in "deep sleep".
        furhat.gesture(reset)
        delay(200)
        furhat.setVisibility(false, 3000) // Fade out face to black
    }

    onExit {
        furhat.setVisibility(true, 3000) // Fade in face
    }
}

