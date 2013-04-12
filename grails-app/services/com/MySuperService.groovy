package com

import com.tenlittleniggers.grails.config.GrailsConfig

/**
 * Created with IntelliJ IDEA.
 * User: fbelov
 * Date: 3/29/13
 * Time: 1:14 AM
 * To change this template use File | Settings | File Templates.
 */
class MySuperService {

    @GrailsConfig("my.another:trlolo!!?")
    Integer configValue = 99

    @GrailsConfig("my.config")
    Integer anotherConfig = 7
}
