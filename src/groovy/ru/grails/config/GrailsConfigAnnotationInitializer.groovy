package ru.grails.config

import grails.util.Holders

@Singleton
class GrailsConfigAnnotationInitializer {

    private List initialized = []

    static void init(Object obj) {
        GrailsConfigAnnotationHandler.instance.initObject(obj, Holders.grailsApplication)
    }

    void initObjectWhenRequired(Object obj) {
        def id = System.identityHashCode(obj)

        if (!initialized.contains(id)) {
            init(obj)
            initialized << id
        }
    }
}
