package furhatos.app.advanced

import furhatos.app.advanced.flow.Init
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class Advanced : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}