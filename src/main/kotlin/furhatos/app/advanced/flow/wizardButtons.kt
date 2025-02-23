package furhatos.app.advanced.flow

import furhatos.app.advanced.flow.how_are_you.HowAreYou
import furhatos.app.advanced.flow.main.*
import furhatos.app.advanced.setting.*
import furhatos.flow.kotlin.Color
import furhatos.flow.kotlin.Section
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.partialState

/** Universal button ta always be present **/
val UniversalWizardButtons = partialState {
    onButton("Restart", color = Color.Red, section = Section.LEFT) {
        goto(Init)
    }
    onButton("Stop speaking", color = Color.Red, section = Section.LEFT) {
        furhat.stopSpeaking()
    }
}

/** Buttons to speed up testing **/
val TestButtons = partialState {
    onButton("Idle", color = Color.Blue, section = Section.RIGHT) {
        goto(Idle)
    }
    onButton("Waiting for engaged user", color = Color.Blue, section = Section.RIGHT) {
        goto(WaitingToStart)
    }
    onButton("Active", color = Color.Blue, section = Section.RIGHT) {
        goto(Active)
    }
    onButton("Greeting", color = Color.Blue, section = Section.RIGHT) {
        goto(GreetUser(null))
    }
    onButton("How are you", color = Color.Blue, section = Section.RIGHT) {
        call(HowAreYou)
    }
    onButton("Nap", color = Color.Blue, section = Section.RIGHT) {
        goto(Nap)
    }
    onButton("Deep sleep", color = Color.Blue, section = Section.RIGHT) {
        goto(DeepSleep)
    }

    onButton("WakeUp", color = Color.Yellow, section = Section.RIGHT) {
        furhat.wakeUp()
    }
    onButton("Wake up", color = Color.Yellow, section = Section.RIGHT) {
        furhat.fallASleep()
    }
    onButton("Wake up", color = Color.Yellow, section = Section.RIGHT) {
        furhat.beIdle()
    }
    onButton("Wake up", color = Color.Yellow, section = Section.RIGHT) {
        furhat.beActive()
    }

    onButton("Set furhat persona", color = Color.Yellow, section = Section.RIGHT) {
        activate(furhatPersona)
    }
}