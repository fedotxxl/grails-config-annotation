package com.tenlittleniggers.grails.config

class GrailsConfigAnnotationInitializer {

    private static application //not cool but works

    static void setApplication(def application) {
        this.application = application
    }

    static void init(Object obj) {
        def annotated = GrailsConfigAnnotationHandler.getGrailsConfigAnnotationsByFields(obj.class)
        if (annotated) {
            annotated.each { an ->
                def field = an.key, annotation = an.value
                GrailsConfigAnnotationHandler.updateAnnotatedConfigProperty(obj, application.config.flatten(), field, annotation)
            }
        }
    }

}
