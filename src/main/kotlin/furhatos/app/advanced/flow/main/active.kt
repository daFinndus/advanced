package furhatos.app.advanced.flow.main

import furhatos.app.advanced.flow.Parent
import furhatos.app.advanced.flow.log
import furhatos.app.advanced.nlu.HowAreYouIntent
import furhatos.app.advanced.nlu.NiceToMeetYouIntent
import furhatos.app.advanced.responses.gestures.hearSpeechGesture
import furhatos.app.advanced.setting.AutoGlanceAway
import furhatos.app.advanced.setting.AutoUserAttentionSwitching
import furhatos.app.advanced.setting.beActive
import furhatos.app.advanced.setting.isAttended
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Greeting

/**
 * State where Furhat engage actively with the user.
 * Start your interaction from here.
 */
val Active: State = state(Parent) {
    onEntry {
        furhat.beActive()
        log.debug("Now I'm listening in German, hopefully!")

        // We're leaving the initiative to the user and extending the listen timeout from default 5000 ms to 8000 ms.
        furhat.listen(8000)
    }

    onReentry {
        when {
            !users.hasAny() -> goto(Idle)
            !furhat.isAttended() -> goto(WaitingToStart)
            else -> furhat.listen()
        }
    }

    include(AutoUserAttentionSwitching) // Switch user after a while.
    include(AutoGlanceAway) // Glance away after some time of eye contact.

    /** Handle simple meet and greet conversation **/

    onPartialResponse<Greeting> { // Handle multiple intents where one part of the intent was a Greeting.
        log.info("Responding to greeting.")
        furhat.attend(it.userId) // Attend the user that spoke.
        goto(GreetUser(it))
    }

    onResponse(listOf(Greeting(), HowAreYouIntent(), NiceToMeetYouIntent())) {
        log.info("Responding to greeting, how are you or nice to meet you.")
        furhat.attend(it.userId) // Attend the user that spoke.
        goto(GreetUser(it))
    }

    onResponse {
        log.info("Unknown response.")
        // On unknown response, the robot reacts to the user speaking, but doesn't engage and take the initiative.
        // This overrides the default behavior: "Sorry I didn't understand that".
        furhat.say("Entschuldigung, das habe ich nicht verstanden.")
        furhat.say("Ich habe verstanden", it.text)
        furhat.attend(it.userId) // Attend user that spoke.
        furhat.gesture(hearSpeechGesture)
        reentry()
    }

    onUserAttend {
        log.info("User ${it.id} attended.")
        furhat.attend(it)
        reentry()
    }

    onNoResponse { reentry() }
}