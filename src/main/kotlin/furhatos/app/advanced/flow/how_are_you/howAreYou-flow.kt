package furhatos.app.advanced.flow.how_are_you

import furhatos.flow.kotlin.*
import furhatos.util.Language


/**
 * Tip!
 *
 * This subflow has all relevant resources including intents, entities and phrases together in its own package
 * without any dependencies to other skills files. This allows for easy reuse of the subflow in other skills.
 */

/**
 * Example of a subflow.
 * Flow will ask about how to user feels today and return.
 **/
val HowAreYou: State = state {
    onEntry { furhat.ask(phrases.howAreYou) }

    onResponse<PositiveReactionIntent> { // This will handle the answer of the user to "How are you?"
        val positiveWord: String? = it.intent.positiveExpressionEntity?.text // Check for what word the person used in the intent.
        furhat.say(phrases.gladYouFeelGood(positiveWord))
        terminate()
    }

    onResponse<NegativeReactionIntent> { // This will handle the answer of the user to "How are you?"
        val negativeWord: String? = it.intent.negativeExpressionEntity?.text // Check for what word the person used in the intent.
        furhat.say(phrases.sorryYouFeelBad(negativeWord))
        terminate()
    }

    onResponse { // If not in the positive reaction list, we try to assume nothing.
        furhat.say(phrases.wellFeelingsAreComplex)
        delay(400)
        terminate()
    }

    onNoResponse { terminate() }
}

/** Run this to test the intents of this state from the command line. **/
fun main() {
    while (true) {
        val utterance = readLine()
        val results = HowAreYou.getIntentClassifier(lang = Language.GERMAN).classify(utterance!!)

        if (results.isEmpty()) println("No match.")
        else results.forEach { println("Matched ${it.intents} with ${it.conf} confidence.") }
    }
}