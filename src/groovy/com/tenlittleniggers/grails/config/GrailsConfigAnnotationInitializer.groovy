package com.tenlittleniggers.grails.config

import grails.util.Holders

class GrailsConfigAnnotationInitializer {

    static void init(Object obj) {
        GrailsConfigAnnotationHandler.instance.initObject(obj, Holders.grailsApplication)
    }

}
