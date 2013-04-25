/*
 * TransactionalService
 */
package ru.grails.config.test

import ru.grails.config.GrailsConfig
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

