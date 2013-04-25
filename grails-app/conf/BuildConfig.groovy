grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
    }
    dependencies {
        //testing
        test ("org.grails.plugins:spock:0.7") {
            excludes "spock-grails-support"
            export = false
        }
        test ("org.spockframework:spock-grails-support:0.7-groovy-1.8") {
            export = false
        }
    }

    plugins {
        build(":tomcat:$grailsVersion",
              ":release:2.0.3",
              ":rest-client-builder:1.0.2") {
            export = false
        }
    }
}
