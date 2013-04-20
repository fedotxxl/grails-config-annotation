import com.tenlittleniggers.grails.config.GrailsConfigAnnotationHandler
import com.tenlittleniggers.grails.config.GrailsConfigBeanPostProcessor

class GrailsConfigAnnotationGrailsPlugin {
    // the plugin version
    def version = "1.0"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/controllers/com/tenlittleniggers/grails/config/test/MainController.groovy",
            "grails-app/services/com/tenlittleniggers/grails/config/test/TransactionalService.groovy",
            "grails-app/services/com/tenlittleniggers/grails/config/test/SimpleService.groovy",
            "src/groovy/com/tenlittleniggers/grails/config/test/UsersBean.groovy"
    ]

    def title = "Grails Config Annotation Plugin" // Headline display name of the plugin
    def author = "fedor.belov"
    def authorEmail = "fedor.belov@mail.ru"
    def description = '''\
Brief summary/description of the plugin.
'''

    //watch for all scss file changes
    def watchedResources = ["file:./**/*.groovy"]

    def documentation = "https://github.com/fedotxxl/grails-config-annotation/blob/master/README.md"
    def scm = [url: 'https://github.com/fedotxxl/grails-config-annotation']
    def issueManagement = [system: "GITHUB", url: "https://github.com/fedotxxl/grails-config-annotation/issues"]

    private GrailsConfigAnnotationHandler handler = GrailsConfigAnnotationHandler.instance

    def doWithSpring = {
        grailsConfigBeanPostProcessor(GrailsConfigBeanPostProcessor)
    }

    def onChange = { event ->
        try {
            Class clazz = null
            if(event.source instanceof java.lang.Class) {
                clazz = event.source
            } else {
                println "event - ${event.source}"
            }

            if (clazz) {
                println "updating beans config values of class ${event.source}"

                handler.resetClass(clazz)
                handler.initClass(clazz, application)

                println "completed"
            }
        } catch (Throwable e) {
            println "Config annotation: exception on processing change event: ${event} - ${e}"
            e.printStackTrace()
        }
    }

    def onConfigChange = { event ->
        //I think we can save beans and update beans
        //But this is safer approach
        handler.annotatedClasses.each { clazz ->
            handler.initClass(clazz, application)
        }
    }

}
