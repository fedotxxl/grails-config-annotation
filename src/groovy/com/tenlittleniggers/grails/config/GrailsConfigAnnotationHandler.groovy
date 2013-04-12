package com.tenlittleniggers.grails.config

import com.MySuperService
import groovy.util.logging.Slf4j
import org.codehaus.groovy.grails.commons.GrailsApplication

import java.lang.annotation.Annotation
import java.lang.reflect.Field

@Slf4j
class GrailsConfigAnnotationHandler {

    static void initObject(Object obj, GrailsApplication application) {
        def annotated = GrailsConfigAnnotationHandler.getGrailsConfigAnnotationsByFields(obj.class)
        if (annotated) {
            annotated.each { an ->
                def field = an.key, annotation = an.value
                GrailsConfigAnnotationHandler.updateAnnotatedConfigProperty(obj, application.config.flatten(), field, annotation)
            }
        }
    }

    static List<Map> initClass(Class clazz, GrailsApplication application) {
        def answer = []

        def annotated = GrailsConfigAnnotationHandler.getGrailsConfigAnnotationsByFields(clazz)
        if (annotated) {
            def beans = application.mainContext.getBeansOfType(clazz).values()
            beans.each { bean ->
                annotated.each { an ->
                    def field = an.key, annotation = an.value
                    //update bean field value
                    GrailsConfigAnnotationHandler.updateAnnotatedConfigProperty(bean, application.config.flatten(), field, annotation)
                    //save this info
                    answer << [bean, field, annotation]
                }
            }
        }

        return answer
    }

    static void updateAnnotatedConfigProperty(Object obj, ConfigObject config, Field field, Annotation annotation) {
        String configParamAndValue = annotation.value()
        if (!configParamAndValue) {
            log.warn "Can't set config value for field '${field.name}' of class '${obj.class}' because @GrailsConfig.value() == null"
        } else {
            def configParam, value
            def pos = configParamAndValue.indexOf(":")

            if (pos >= 0) {
                configParam = configParamAndValue.substring(0, pos)
                value = configParamAndValue.substring(pos+1)
            } else {
                configParam = configParamAndValue
            }

            def propertyValue = config.get(configParam)
            if (propertyValue == null && value != null) {
                propertyValue = value
            }

            def oldValue = obj.@"${field.name}"
            def newValue
            if (propertyValue == null) {
                newValue = null
            } else {
                def casted = cast(propertyValue, field.type)
                if (casted == null) {
                    log.error "@GrailsConfig: Can't convert value '${propertyValue}' of field '${field.name}' of class '${obj.class.name}' to ${field.type}"
                    return
                } else {
                    newValue = casted
                }
            }

            if (oldValue != newValue) {
                log.info "@GrailsConfig: Overriding old value (${oldValue}) of field '${field.name}' of class '${obj.class.name}' with new one (${propertyValue})"

                //assign getter and internal value because of transactional services
                //http://grails.1312388.n4.nabble.com/Transactional-services-and-variable-assignment-td4643464.html
                obj.@"${field.name}" = newValue
                obj."${field.name}" = newValue
            }
        }
    }

    static Map getGrailsConfigAnnotationsByFields(Class clazz) {
        def answer = [:]

        clazz.declaredFields.each { field ->
            def annotation = field.declaredAnnotations?.find {it.annotationType() == GrailsConfig}
            if (annotation) {
                answer[field] = annotation
            }
        }

        return answer
    }


    private static cast(def object, Class type) {
        try {
            return object.asType(type)
        } catch (e) {
            return null
        }
    }

}
