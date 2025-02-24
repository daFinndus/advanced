package furhatos.app.advanced.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

/**
 * Define intents to match a user utterance and assign meaning to what they said.
 * Note that there are more intents available in the Asset Collection in furhat.libraries.standard.NluLib
 **/

class NiceToMeetYouIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Freut mich, dich kennenzulernen.",
            "Schön, dich zu treffen.",
            "Sehr erfreut, dich zu sehen.",
            "Es ist mir eine Freude, dich kennenzulernen.",
            "Toll, dich zu treffen.",
            "Endlich lernen wir uns kennen!",
            "Schön, dich mal in echt zu sehen.",
            "Nett, dich kennenzulernen.",
            "Ich freue mich, dich kennenzulernen.",
            "Angenehm!"
        )
    }
}

class HowAreYouIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Wie geht es dir?",
            "Wie läuft es bei dir?",
            "Was geht?",
            "Wie steht’s?",
            "Wie fühlst du dich?",
            "Was gibt’s Neues?",
            "Wie ist die Lage?",
            "Wie läuft dein Tag?",
            "Alles gut bei dir?",
            "Wie sieht’s aus?",
            "Alles klar bei dir?"
        )
    }
}


class HelpIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Ich brauche Hilfe.",
            "Hilf mir bitte.",
            "Kann mir jemand helfen?",
            "Ich benötige Unterstützung!",
            "Kannst du mir helfen?",
            "Ich komme nicht weiter.",
            "Ich weiß nicht, was ich tun soll.",
            "Bitte hilf mir!"
        )
    }
}

class WhatIsThisIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Was ist das?",
            "Was soll ich sagen?",
            "Was kannst du?",
            "Was ist das hier?",
            "Was machst du?",
            "Was muss ich tun?",
            "Ich weiß nicht, was ich machen soll.",
            "Was soll ich jetzt tun?",
            "Soll ich etwas sagen?",
            "Was geht hier vor?",
            "Was passiert hier?",
            "Kann mir jemand erklären, was los ist?",
            "Ich verstehe das nicht."
        )
    }
}
