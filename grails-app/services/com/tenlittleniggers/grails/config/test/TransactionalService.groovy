/*
 * TransactionalService
 */
package com.tenlittleniggers.grails.config.test

import com.tenlittleniggers.grails.config.GrailsConfig
import grails.util.Holders

import javax.annotation.PostConstruct

class TransactionalService {

    @GrailsConfig("app.transactionalVariable")
    def transactionalVariable

    @PostConstruct
    private setUp() {
        assert this.transactionalVariable == Holders.config.app.transactionalVariable
        assert this.@transactionalVariable == Holders.config.app.transactionalVariable
    }

}

