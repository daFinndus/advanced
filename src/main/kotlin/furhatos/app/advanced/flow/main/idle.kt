package furhatos.app.advanced.flow.main

import furhat.libraries.standard.BehaviorLib.AutomaticMovements.randomHeadMovements
import furhatos.app.advanced.flow.Global
import furhatos.app.advanced.flow.log
import furhatos.app.advanced.setting.*
import furhatos.flow.kotlin.*

/**
 * Tip!
 *
 * Use simple Idle state where the interaction will start as soon as a user enters for situations where you know
 * people want to be interacting with the robot. Or define a more complex idle state like "WaitingForEngagedUser"
 * to avoid starting the interaction without the user actually being interested to engage with the user.
 */

/** State where Furhat is inactive with no users in front of it */
val Idle: State = state(parent = Global) {
    include(randomHeadMovements())
    include(napWhenTired) // Sends the robot to "sleep" after a period of inactivity

    /** This is only executed once */
    init { if (furhat.isVirtual()) furhat.say("Füge einen virtuellen Nutzer hinzu, um das Programm zu starten.") }

    onEntry { // on entry performed every time entering the state
        furhat.beIdle() // custom function to set the robot in a specific 'mode'
        log.info("Idling now.")
    }

    onUserEnter {
        furhat.attend(it) // It's good practice to make attention shifts on the trigger, instead of shifting the attention onEntry in the state.
        goto(WaitingToStart)
    }
}

/**
 * State where Furhat is waiting for users to walk up close to the robot and engage with it.
 * While waiting Furhat will seek their attention and switch attention between users.
 * */
val WaitingToStart: State = state(parent = Global) {
    include(randomHeadMovements())
    include(SeekAttention) // Attention seeking behaviour.
    include(napWhenTired) // Go to sleep after a while if no users are engaging.

    onEntry {
        furhat.beIdle()
        // We need to check if we already have a user attending when entering the state.
        if (users.usersAttendingFurhat.isNotEmpty()) {
            // We could create an actual senseUserAttend event and let the onUserAttend trigger handle it.
            // EventSystem.send(SenseUserAttend.Builder().buildEvent())
            // But it will have the unfortunate effect of clearing other user data on the user.
            // So instead we simply copy the same action here as on the onUserAttend trigger.
            log.debug("User ${users.usersAttendingFurhat.first().id} attended in ${thisState.name}.")
            furhat.attend(users.usersAttendingFurhat.first())
            goto(Active)
        } else {
            log.info("Waiting for user to attend.")
        }
    }

    onReentry {
        when {
            !users.hasAny() -> goto(Idle)
            furhat.isAttended() -> goto(Active)
            else -> log.debug("Keep idling.") // Do nothing particular - just keep idling.
        }
    }

    onUserAttend {
        log.debug("User ${it.id} attended in ${thisState.name}.")
        furhat.attend(it)
        goto(Active)
    }

    onUserLeave(instant = true) {
        when { // "it" is the user that left.
            !users.hasAny() -> goto(Idle) // No more users - back to idle.
            furhat.isAttending(it) -> furhat.attend(users.nextMostEngagedUser()) // Go to the next user.
            else -> furhat.glance(it.head.location) // Glance at the user that left.
        }
    }

}

