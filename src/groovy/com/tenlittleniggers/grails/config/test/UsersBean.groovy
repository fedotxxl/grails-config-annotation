/*
 * UsersBean
 */
package com.tenlittleniggers.grails.config.test

import com.tenlittleniggers.grails.config.GrailsConfig

class UsersBean {

    @GrailsConfig("app.customBeanVariable")
    private customBeanVariableValue

    def getCustomBeanVariableValue() {
        return this.@customBeanVariableValue
    }
}