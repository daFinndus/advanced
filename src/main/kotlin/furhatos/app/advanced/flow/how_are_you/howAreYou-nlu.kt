package furhatos.app.advanced.flow.how_are_you

import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.util.Language

class PositiveReactionIntent(
    val positiveExpressionEntity: PositiveExpressionEntity? = null,
    val negativeExpressionEntity: NegativeExpressionEntity? = null
) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "@positiveExpressionEntity",
            "Nicht @negativeExpressionEntity",
            "Nicht so @negativeExpressionEntity"
        )
    }

    override fun getNegativeExamples(lang: Language): List<String> {
        return listOf(
            "Nicht @positiveExpressionEntity",
            "@negativeExpressionEntity"
        )
    }
}

class PositiveExpressionEntity : EnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf(
            // Allgemeine positive Begriffe.
            "Ausgezeichnet", "Fabelhaft", "Fantastisch", "Großartig", "Perfekt", "Toll",
            "Erstaunlich", "Unglaublich", "Episch", "Wunderbar", "Prächtig", "Super",

            // Glücklich & Zufrieden.
            "Glücklich", "Froh", "Freudig", "Fröhlich", "Erfreut", "Zufrieden",
            "Überglücklich", "Heiter", "Optimistisch", "Geschmeichelt",
            "Ekstatisch", "Begeistert", "Jubelnd",

            // Energetisch & Aufgeregt.
            "Aufgeregt", "Lebhaft", "Munter", "Peppig", "Verspielt", "Spritzig", "Sonnig",
            "Funkelnd", "Gekitzelt", "Auf Wolke sieben",

            // Ruhig & Zufriedenstellend.
            "Friedlich", "Angenehm", "Dankbar", "Gesättigt", "Geschmeidig", "Berauscht", "Gut",

            // Umgangssprachlich & Ausdrucksstark.
            "Cool", "Nett", "Lachend", "Leicht", "Gut aussehend", "In Ordnung",
            "Alles bestens", "Kann mich nicht beschweren", "Oben"
        )
    }
}

class NegativeReactionIntent(
    val negativeExpressionEntity: NegativeExpressionEntity? = null,
    val positiveExpressionEntity: PositiveExpressionEntity? = null
) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "@negativeExpressionEntity",
            "Nicht @positiveExpressionEntity",
            "Nicht so @positiveExpressionEntity"
        )
    }

    override fun getNegativeExamples(lang: Language): List<String> {
        return listOf(
            "Nicht @negativeExpressionEntity",
            "@positiveExpressionEntity"
        )
    }
}


class NegativeExpressionEntity : EnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf(
            // Allgemein schlecht.
            "Schlecht", "Furchtbar", "Schrecklich", "Grauenhaft", "Miserabel", "Lächerlich",
            "Unzumutbar", "Untragbar", "Bedauerlich",

            // Starke negative Adjektive.
            "Schlimm", "Katastrophal", "Entsetzlich", "Abscheulich", "Ekelhaft",
            "Unverschämt", "Unerträglich", "Nervtötend", "Ernüchternd",

            // Umgangssprachlich & Beleidigend.
            "Scheiße", "Dreck", "Mist", "Zum Kotzen", "Nervig", "Beschissen", "Sinnlos",
            "Zum Verzweifeln", "Frustrierend", "Ätzend",

            // Fehler & Mängel.
            "Fehlerhaft", "Mangelhaft", "Unvollständig", "Kaputt", "Verpfuscht", "Flawed",

            // Unprofessionell & Unangemessen.
            "Unprofessionell", "Unhöflich", "Respektlos", "Unangemessen", "Schäbig",
            "Unakzeptabel", "Unzuverlässig", "Halbherzig", "Lieblos"
        )
    }
}

