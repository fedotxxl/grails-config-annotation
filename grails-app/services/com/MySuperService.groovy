package com

import com.tenlittleniggers.grails.config.GrailsConfig

import javax.annotation.PostConstruct

/**
 * Created with IntelliJ IDEA.
 * User: fbelov
 * Date: 3/29/13
 * Time: 1:14 AM
 * To change this template use File | Settings | File Templates.
 */
class MySuperService {

    @GrailsConfig("my.another:trlolo!!?")
    private configValue = 99

    @GrailsConfig("my.config")
    private anotherConfig = 7

    @PostConstruct
    private setUp() {
        println "transactional: ${this}: ${this.anotherConfig}; ${this.@anotherConfig}"
    }
}
