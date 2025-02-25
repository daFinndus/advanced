package furhatos.app.advanced.flow

import furhatos.app.advanced.Advanced
import furhatos.app.advanced.flow.main.Idle
import furhatos.app.advanced.flow.main.WaitingToStart
import furhatos.app.advanced.flow.paralell.InteractionGlow
import furhatos.app.advanced.setting.*
import furhatos.app.advanced.utils.loadProperties
import furhatos.autobehavior.bigSmileProbability
import furhatos.autobehavior.enableSmileBack
import furhatos.autobehavior.smileBlockDelay
import furhatos.autobehavior.smileProbability
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.nlu.LogisticMultiIntentClassifier
import furhatos.util.CommonUtils
import java.io.File

/** Flow parameters */
var testMode: Boolean = false
var noMatch = 0
var noInput = 0

// Get the log4J logger available from furhat.system.logger to print messages to the IntelliJ console and the log console in the web interface.
// Set the logging level in skill.properties
val log = CommonUtils.getLogger(Advanced::class.java)

/** State to initiate the skill, setting interaction parameters and starting the dialogue flow **/
val Init: State = state {
    init {
        /** Load properties file **/
        loadProperties()

        /** Set testMode for speedy testing */
        testMode = true

        /** Define listening parameters */
        furhat.param.endSilTimeout = 1000 // Time to wait for silence after user has stopped speaking to trigger onResponse
        furhat.param.noSpeechTimeout = 5000 // Time to wait for user to start speaking before triggering onNoResponse
        furhat.param.maxSpeechTimeout = 15000 // Time until the robot interrupts the user if they speak too long

        /** Set our default interaction parameters */
        users.engagementPolicy = defaultEngagementPolicy

        /** Define our default engagement parameters */
        users.attentionGainedThreshold = DEFAULT_ATTENTION_GAINED_THRESHOLD
        users.attentionLostThreshold = DEFAULT_ATTENTION_LOST_THRESHOLD

        /** Define our default smile back behavior */
        furhat.enableSmileBack = true
        smileBlockDelay = 5000
        smileProbability = 0.5
        bigSmileProbability = 0.3

        /** Enable alternate intent classifier
        see: https://docs.furhat.io/nlu/#alternative-classification */
        LogisticMultiIntentClassifier.setAsDefault()

        /** Set default microepressions **/
        furhat.setMicroexpression(DEFAULT_MICROEXPRESSIONS)

        /** Initiate loggers **/
        // Dialog logger is enabled when there is dialog happening - see "beActive()"
        // The flow logger will dump a detailed log of the flow.
        // Use the general log4J logger to print log messages for development purposes.
        if (testMode) {
            // Ensure the logs directory exists before writing the log file
            val logDir = File("logs")
            if (!logDir.exists()) logDir.mkdirs() // Create directory if it doesn't exist

            val flowLoggerFile = File(logDir, "flowlog.txt")
            flowLogger.start(flowLoggerFile)
        }

        /** Start parallel flow to manage the LED **/
        parallel(abortOnExit = false) { goto(InteractionGlow) }
    }

    onEntry {
        /** Set our main character - defined in personas */
        activate(furhatPersona)

        /** Start interaction */
        when {
            users.hasAny() -> {
                log.debug("User present - start the interaction.")
                furhat.attend(users.random)
                goto(WaitingToStart)
            }

            else -> {
                log.debug("No users present - idling.")
                goto(Idle)
            }
        }
    }
}

