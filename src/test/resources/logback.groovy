import de.huxhorn.lilith.logback.appender.ClassicMultiplexSocketAppender
import de.huxhorn.lilith.logback.encoder.ClassicLilithEncoder

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.DEBUG

scan('5 minutes')
setupAppenders()

def setupAppenders() {

    appender("STDOUT", ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
        }
    }

    // Lilith Log appender
    appender("LILITH_LOG", RollingFileAppender) {
        file = "${baseName}.lilith"
        encoder(ClassicLilithEncoder) {
            includeCallerData = true
        }
        rollingPolicy(FixedWindowRollingPolicy) {
            fileNamePattern = "${baseName}.%i.lilith.zip"
            minIndex = 1
            maxIndex = 9
        }
        triggeringPolicy(SizeBasedTriggeringPolicy) {
            maxFileSize = "2MB"
        }
        append = true
    }

// Lilith socket appender
    appender("LILITH_SOCKET", ClassicMultiplexSocketAppender) {
        compressing = true
        reconnectionDelay = 10000
        includeCallerData = true
        remoteHost = "localhost"
    }

    //   logger("org.springframework", INFO)
    root(DEBUG, ["STDOUT", "LILITH_LOG", "LILITH_SOCKET"])
}