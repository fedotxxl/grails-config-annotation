/*
 * SimpleService
 */
package com.tenlittleniggers.grails.config.test
import com.tenlittleniggers.grails.config.GrailsConfig

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

    @GrailsConfig("app.nullVarialbe:default value")
    String nullValue

    String getPrivateVariable() {
        return this.@privateVariable
    }

}
