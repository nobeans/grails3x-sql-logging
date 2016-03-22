import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

root(ERROR, ['STDOUT'])

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}


// SQLを出力する(プレイスホルダは「?」表記のまま)
logger 'org.hibernate.SQL', DEBUG, ['STDOUT'], false

// SQLのプレイスホルダに対するパラメータを出力する(enum以外)
logger 'org.hibernate.type.descriptor.sql.BasicBinder', TRACE, ['STDOUT'], false

// SQLのプレイスホルダに対するパラメータを出力する(enum)
logger 'org.hibernate.type.EnumType', TRACE, ['STDOUT'], false

// HibernateではなくGroovy SQLを使っている場合も漏らさずロギングする
logger 'groovy.sql.Sql', DEBUG, ['STDOUT'], false
