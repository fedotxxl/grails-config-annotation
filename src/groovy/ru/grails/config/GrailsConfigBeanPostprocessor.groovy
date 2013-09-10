package ru.grails.config

import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor

class GrailsConfigBeanPostProcessor implements BeanPostProcessor {

    private static initializer = GrailsConfigAnnotationInitializer.instance

    Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        //init all beans
        initializer.silentInit(o)

        return o
    }

    Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        //init once more to init transactional services
        initializer.silentInit(o)

        return o
    }
}
