/*
 * SimpleGrailsAnnotationSpec
 */
package ru.grails.config.test
import grails.plugin.spock.IntegrationSpec
import grails.util.Holders

class SimpleGrailsAnnotationSpec extends IntegrationSpec  {

    def simpleService
    def transactionalService
    def usersBean

    def "check @GrailsConfig values"() {
        when:
        def config = Holders.config
        
        then:
        assert simpleService.superSimpleVariable == config.app.superSimpleVariable
        assert simpleService.fromDefaultValue == "this is default value"
        assert simpleService.integerValue == config.app.integerValue
        assert simpleService.privateVariable == config.app.privateVariable
        assert simpleService.nullValue == null
        assert simpleService.simpleDefaultValue == new SimpleService().simpleDefaultValue
        assert transactionalService.@transactionalVariable == config.app.transactionalVariable
        assert transactionalService.getTransactionalVariable() == config.app.transactionalVariable
        assert usersBean.customBeanVariableValue == config.app.customBeanVariable
    }

}
