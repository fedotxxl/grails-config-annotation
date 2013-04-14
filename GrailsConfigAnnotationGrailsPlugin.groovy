import com.MySuperService
import com.tenlittleniggers.grails.config.GrailsConfigAnnotationHandler
import com.tenlittleniggers.grails.config.GrailsConfigAnnotationHandler
import com.tenlittleniggers.grails.config.GrailsConfigAnnotationInitializer
import com.tenlittleniggers.grails.config.GrailsConfigBeanPostProcessor
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.core.io.FileSystemResource

import java.lang.annotation.Annotation
import java.lang.reflect.Field

class GrailsConfigAnnotationGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Grails Config Annotation Plugin" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''

    //watch for all scss file changes
    def watchedResources = ["file:./**/*.groovy"]

//    def observe = ['*']

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/grails-config-annotation"

    private List annotatedBeans = []
    static Set<Class> annotatedClasses = []

    def doWithSpring = {
        grailsConfigBeanPostProcessor(GrailsConfigBeanPostProcessor)
    }

    def doWithApplicationContext = { applicationContext ->
//        //init handler
//        println GrailsConfigAnnotationHandler.calls
//        println GrailsConfigAnnotationHandler.times
//        GrailsConfigAnnotationInitializer.application = application
//
//        //update beans
//        application.allArtefacts.each { clazz ->
//            GrailsConfigAnnotationHandler.initClass(clazz, application)
//        }
    }

//    private updateAnnotatedConfigPropertiesOfClass(def clazz, def application) {
//        def annotated = GrailsConfigAnnotationHandler.getGrailsConfigAnnotationsByFields(clazz)
//        if (annotated) {
//            def beans = application.mainContext.getBeansOfType(clazz).values()
//            beans.each { bean ->
//                annotated.each { an ->
//                    def field = an.key, annotation = an.value
//                    //update bean field value
//                    GrailsConfigAnnotationHandler.updateAnnotatedConfigProperty(bean, application.config.flatten(), field, annotation)
//                    //save this info for config reload
//                    annotatedBeans << [bean, field, annotation]
//                    annotatedClasses << clazz
//                }
//            }
//        }
//    }

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

                GrailsConfigAnnotationHandler.resetClass(clazz)
                GrailsConfigAnnotationHandler.initClass(clazz, application)

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
        GrailsConfigAnnotationHandler.annotatedClasses.each { clazz ->
            GrailsConfigAnnotationHandler.initClass(clazz, application)
        }
    }

}
