package ru.grails.config

import grails.util.Holders

@Singleton
class GrailsConfigAnnotationInitializer {

    private List initialized = []
    private static handler = GrailsConfigAnnotationHandler.instance

    static void init(Object obj, Boolean silent = false) {
        handler.silent = silent
        handler.initObject(obj, Holders.grailsApplication)
    }

    void silentInit(Object obj) {
        def silent = false
        def id = System.identityHashCode(obj)

        if (!initialized.contains(id)) {
            initialized << id
        } else {
            silent = true
        }

        init(obj, silent)
    }
}
