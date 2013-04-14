package com.tenlittleniggers.grails.config

import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor

class GrailsConfigBeanPostProcessor implements BeanPostProcessor {
    Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        //init all beans
        GrailsConfigAnnotationInitializer.instance.initObjectWhenRequired(o)

        return o
    }

    Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        //init once more to init transactional services
        GrailsConfigAnnotationInitializer.instance.initObjectWhenRequired(o)

        return o
    }
}
