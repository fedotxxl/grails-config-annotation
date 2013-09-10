grails.views.gsp.encoding="UTF-8"

log4j = {
    info 'ru.grails'

    error 'org.codehaus.groovy.grails',
          'org.springframework',
          'org.hibernate',
          'net.sf.ehcache.hibernate'
}

app {
    superSimpleVariable = "this is simple string variable"
    integerValue = 69
    privateVariable = "private variable value"
    transactionalVariable = "transactional variable value"
    customBeanVariable = "my custom bean"
    nullVarialbe = null
}
