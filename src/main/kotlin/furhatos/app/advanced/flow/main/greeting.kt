package furhatos.app.advanced.flow.main

import furhatos.app.advanced.flow.Parent
import furhatos.app.advanced.flow.how_are_you.HowAreYou
import furhatos.app.advanced.nlu.HowAreYouIntent
import furhatos.app.advanced.nlu.NiceToMeetYouIntent
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.Response
import furhatos.nlu.common.Greeting
import furhatos.util.Language

/**
 * Example state of a simple flow to greet a user.
 */
// Define the state as a function to be able to pass arguments to it
fun GreetUser(response: Response<*>? = null): State = state(Parent) {
    onEntry {
        if (response != null) raise(response) // Raise any response that was passed on and handle the response here.
        else furhat.listen() // Or start a listen to collect a response in this state.
    }

    // Handle partial responses where the user said a greeting and something else.
    onPartialResponse<Greeting> {
        furhat.say {
            random {
                +"Hi!"
                +"Hallo."
                +"Guten Tag."
                +"Moin Moin."
            }
        }

        // Raising the secondary intent will cause our triggers to handle the second part of the intent.
        // Also raising the response (it) allows for acting on information in the response - e.g. what user spoke
        raise(it, it.secondaryIntent)
    }

    // Handle an input that was a greeting only.
    onResponse<Greeting> {
        furhat.say {
            random {
                +"Hi!"
                +"Hallo."
                +"Guten Tag."
                +"Moin Moin."
            }
        }

        call(HowAreYou)
        goto(Active)
    }

    // Handle an input that was "How are you?" intent.
    onResponse<HowAreYouIntent> {
        furhat.say {
            +"Mir geht es"
            random {
                +"gut."
                +"sehr gut."
                +"super."
                +"skibidi."
                +"wie ein Honigkuchenpferd."
            }
            Gestures.BigSmile
        }

        goto(Active)
    }

    // If the user said "Nice to meet you."
    onResponse<NiceToMeetYouIntent> {
        furhat.say {
            random {
                +"Freut mich ebenfalls."
                +"Die Freude ist ganz meinerseits."
                +"Ebenfalls."
            }
            +Gestures.BigSmile
        }

        goto(Active)
    }

    onResponse { goto(Active) }
    onNoResponse { goto(Active) }
}

/** Run this to test the intents of this state from the run terminal in IntelliJ. **/
fun main() {
    println("Type to test the intents of this state (Ignore the initial error message).")
    while (true) {
        println("Enter your user response...")
        val utterance = readLine()
        val results = GreetUser(null).getIntentClassifier(lang = Language.GERMAN).classify(utterance!!)

        if (results.isEmpty()) println("No match.")
        else results.forEach { println("Matched ${it.intents} with ${it.conf} confidence.") }

    }
}