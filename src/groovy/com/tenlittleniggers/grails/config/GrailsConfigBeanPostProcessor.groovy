package com.tenlittleniggers.grails.config

import grails.util.Holders
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor

class GrailsConfigBeanPostProcessor implements BeanPostProcessor {
    Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        //init all beans
        GrailsConfigAnnotationHandler.initObject(o, Holders.grailsApplication)

        return o
    }

    Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        //init once more to init transactional services
        GrailsConfigAnnotationHandler.initObject(o, Holders.grailsApplication)

        return o
    }
}
