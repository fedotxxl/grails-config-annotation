import ru.grails.config.GrailsConfigAnnotationHandler
import ru.grails.config.GrailsConfigBeanPostProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ConfigAnnotationGrailsPlugin {

    private static final Logger LOG = LoggerFactory.getLogger("ru.grails.config.GrailsConfigAnnotationGrailsPlugin")

    def version = "1.0"
    def grailsVersion = "2.0 > *"
    def pluginExcludes = [
            "grails-app/controllers/ru/grails/config/test/MainController.groovy",
            "grails-app/services/ru/grails/config/test/TransactionalService.groovy",
            "grails-app/services/ru/grails/config/test/SimpleService.groovy",
            "src/groovy/ru/grails/config/test/UsersBean.groovy"
    ]

    def license = 'APACHE'
    def title = "Grails Config Annotation Plugin"
    def author = "fedor.belov"
    def authorEmail = "fedor.belov@mail.ru"
    def description = '''\
This plugin adds `@GrailsConfig` annotation that gives you simple way to
inject config value into your beans (services, controllers, tagLibs and etc.)
'''

    //watch for all scss file changes
    def watchedResources = [
            'file:./grails-app/**/*.groovy',
            'file:./src/**/*.groovy'
    ]

    def documentation = "https://github.com/fedotxxl/grails-config-annotation/blob/master/README.md"
    def scm = [url: 'https://github.com/fedotxxl/grails-config-annotation']
    def issueManagement = [system: "GITHUB", url: "https://github.com/fedotxxl/grails-config-annotation/issues"]

    private GrailsConfigAnnotationHandler handler = GrailsConfigAnnotationHandler.instance

    def doWithSpring = {
        grailsConfigBeanPostProcessor(GrailsConfigBeanPostProcessor)
    }

    def onChange = { event ->
        try {
            Class clazz
            if(event.source instanceof Class) {
                clazz = event.source
            } else {
                LOG.debug "@GrailsConfig: skip change event - ${event.source}"
            }

            if (clazz) {
                LOG.trace "@GrailsConfig: updating beans config values of class ${event.source}"

                handler.resetClass(clazz)
                handler.initClass(clazz, application)

                LOG.trace "@GrailsConfig: onChange completed"
            }
        } catch (Throwable e) {
            LOG.error "@GrailsConfig: exception on processing change event: ${event} - ${e}", e
        }
    }

    def onConfigChange = { event ->
        LOG.trace "@GrailsConfig: processing config change event"

        //I think we can save beans and update beans
        //But this is safer approach
        handler.annotatedClasses.each { clazz ->
            handler.initClass(clazz, application)
        }

        LOG.trace "@GrailsConfig: completed processing config change event"
    }

}
