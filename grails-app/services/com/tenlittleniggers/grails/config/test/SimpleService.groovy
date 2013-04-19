/*
 * SimpleService
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package com.tenlittleniggers.grails.config.test

import com.tenlittleniggers.grails.config.GrailsConfig
import grails.util.Holders

import javax.annotation.PostConstruct

class SimpleService {

    static transactional = false

    @GrailsConfig("app.superSimpleVariable")
    String superSimpleVariable

    @GrailsConfig("app.privateVariable")
    private privateVariable

    @GrailsConfig("app.nonExistingConfigValue:this is default value")
    String fromDefaultValue

    @GrailsConfig("app.integerValue:-1")
    Integer integerValue

    @PostConstruct
    private setUp() {
        assert superSimpleVariable == Holders.config.app.superSimpleVariable
        assert fromDefaultValue == "this is default value"
        assert integerValue == Holders.config.app.integerValue
        assert privateVariable == Holders.config.app.privateVariable
    }

    String getPrivateVariable() {
        return this.@privateVariable
    }

}
