/*
 * UsersBean
 */
package ru.grails.config.test

import ru.grails.config.GrailsConfig

class UsersBean {

    @GrailsConfig("app.customBeanVariable")
    private customBeanVariableValue

    def getCustomBeanVariableValue() {
        return this.@customBeanVariableValue
    }
}