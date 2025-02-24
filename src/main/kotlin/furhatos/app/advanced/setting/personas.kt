package furhatos.app.advanced.setting

import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.voice.AzureVoice
import furhatos.flow.kotlin.voice.Voice
import furhatos.util.Gender
import furhatos.util.Language

class Persona(val name: String, val mask: String = "adult", val face: String, val voice: Voice)

fun FlowControlRunner.activate(persona: Persona) {
    furhat.voice = persona.voice
    furhat.character = persona.face
    furhat.setInputLanguage(Language.GERMAN)
}

val furhatPersona = Persona(
    name = "Jan",
    face = "Marty",
    voice = AzureVoice(language = Language.GERMAN, gender = Gender.MALE)
)
