/*
 * MainController
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package com.tenlittleniggers.grails.config.test

class MainController {

    def simpleService
    def transactionalService
    def usersBean

    def superSimpleVariable = {
        render simpleService.superSimpleVariable
    }

    def privateVariable = {
        render simpleService.privateVariable
    }

    def fromDefaultValue = {
        render simpleService.fromDefaultValue
    }

    def integerValue = {
        render simpleService.integerValue
    }

    def transactionalVariable = {
        render transactionalService.@transactionalVariable
    }

    def transactionalMethod = {
        render transactionalService.getTransactionalVariable()
    }

    def customBeanVariableValue = {
        render usersBean.customBeanVariableValue
    }
}

