package furhatos.app.advanced.flow.how_are_you

import furhatos.flow.kotlin.Utterance
import furhatos.flow.kotlin.utterance
import furhatos.gestures.Gestures

val phrases = HowAreYouPhrases()

class HowAreYouPhrases {
    val feelGood: String = listOf("Gut", "Sehr gut", "Wunderbar", "Super schön").random()
    val feelGoodUtterance: Utterance
        get() = utterance {
            +"Mir geht es"
            random {
                +"gut."
                +"sehr gut."
                +"super."
                +"skibidi."
            }
            +Gestures.BigSmile
        }

    val howAreYou: Utterance
        get() = utterance {
            random {
                +"Wie geht es dir?"
                +"Wie fühlst du dich?"
                +"Wie geht es dir heute?"
            }
        }

    fun gladYouFeelGood(positiveWord: String? = "Gut"): Utterance {
        if (positiveWord == null) {
            return utterance {
                random {
                    +"Freut mich zu hören."
                    +"Das ist schön!"
                }
            }
        }

        return utterance {
            random {
                +"Freut mich, dass es dir $positiveWord geht."
                +"Schön, dass es dir $positiveWord geht."
            }

            delay(200)
        }
    }

    fun sorryYouFeelBad(negativeWord: String? = "Schlecht"): Utterance {
        if (negativeWord == null) {
            return utterance {
                random {
                    +"Das tut mir leid."
                    +"Oh, das ist schade."
                }
            }
        }

        return utterance {
            random {
                +"Das tut mir leid, dass es dir $negativeWord geht."
                +"Oh, das ist schade, dass es dir $negativeWord geht."
            }

            delay(200)
        }
    }

    val wellFeelingsAreComplex: Utterance
        get() = utterance {
            +Gestures.Thoughtful(0.6, 2.0)
            random {
                +"Mhm, sowas gibt es mal."
                +"Naja, Gefühle sind schon was komplexes."
                +"Ich denke, das ist normal."
            }
        }
}

