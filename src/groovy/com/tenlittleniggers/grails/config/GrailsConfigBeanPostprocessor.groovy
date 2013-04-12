package com.tenlittleniggers.grails.config

import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor

class GrailsConfigBeanPostprocessor implements BeanPostProcessor {
    Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        println "before init"
        return o
    }

    Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        println "after init"
        return o
    }
}
