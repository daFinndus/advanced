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
            "Glad to meet you!",
            "A pleasure to meet you!",
            "Nice to see you!",
            "Great to meet you!",
            "Happy to see you!",
            "Very nice to finally meet you!",
            "Fun to meet up with you!"
        )
    }
}

class HowAreYouIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "How are you?",
            "How are you doing today?",
            "What's up?",
            "How are things with you?",
            "How's it going?",
            "How are you feeling?",
            "How's life?",
            "What's going on with you?"
        )
    }
}

class HelpIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "I need help.",
            "Help me please.",
            "Can someone help me?",
            "I need assistance."
        )
    }
}

class WhatIsThisIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "What is this?",
            "What am I supposed to say?",
            "What should I say?",
            "I don't know what to do.",
            "What am I supposed to do now?",
            "Should I say something?",
            "What's going on?",
            "What is happening here?",
            "Can someone tell me what is going on?"
        )
    }
}