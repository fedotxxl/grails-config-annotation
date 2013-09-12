Description
-----------
<pre>
Russian documentation is available <a href="https://github.com/fedotxxl/grails-config-annotation/blob/master/README_RU.md">here</a>
</pre>
This plugin simplifies process of using config value in your Spring beans (controllers, services, taglibs and etc.). Let's check how you can use config value now:

```java
import javax.annotation.PostConstruct

class MyService {

    def grailsApplication

    private myVariable

    @PostConstruct
    private setUp() {
        myVariable = grailsApplication.config.app.myVariable
    }

    void doSomeCoolStuff() {
        println myVariable
    }

}
```
I think this is not the best approach - why do you need to inject `grailsApplication` bean only to read config value? Let's improve this code:
```java
import grails.util.Holders

import javax.annotation.PostConstruct

class MyService {

    private myVariable

    @PostConstruct
    private setUp() {
        myVariable = Holders.config.app.myVariable
    }

    void doSomeCoolStuff() {
        println myVariable
    }

}
```
Much better. But we can make it even shorter.

How can I use Grails Config Annotation to inject config value?
----------------------------------------------------------------------------------
Here is similar problem solved with Grails Config Annotation:

```java
import ru.grails.config.GrailsConfig

class MyService {

    @GrailsConfig("app.myVariable")
    private myVariable

    void doSomeCoolStuff() {
        println myVariable
    }

}
```
All you have to do is:  
1. Import annotation: `import ru.grails.config.GrailsConfig`
2. Add `@GrailsConfig` annotation to the variable. Annotation value should be the same as variable name from `Config.groovy` file (in our case `app.myVariable`):  `@GrailsConfig("app.myVariable")`.

Default values
---------------------
You can specify default variable value. When `Config.groovy` file doesn't have required variable default value will be used to configure variable. To configure default value use the following annotation value structure: `config_variable_name_to_inject:default value`. For example:

```java
import ru.grails.config.GrailsConfig

class MyService {

    @GrailsConfig("app.myVariable:my default value")
    private myVariable

    void doSomeCoolStuff() {
        println myVariable
    }

}
```
When file `Config.groovy` doesn't have variable `app.myVariable`, then default value `my default value` will be used to configure variable `myVariable`.

Default values defined by class
---------------------
If `@GrailsConfig` annotation doesn't specify default value then value defined by class will be used as default. For example:

```java
import ru.grails.config.GrailsConfig

class MyService {

    @GrailsConfig("app.myDelay")
    private Long myDelay = 5000

    void doSomeCoolStuff() {
        sleep(myDelay)
    }

}
```
When file `Config.groovy` doesn't have variable `app.myDelay`, then class value `5000` will be used to configure variable `myDelay`.

Types casting
--------------------
You can explicitly specify the variable type. Then variable value will be type casted to required type. For example:

```java
import ru.grails.config.GrailsConfig

class MyService {

    @GrailsConfig("app.myVariable:-1")
    private Integer myVariable

    void doSomeCoolStuff() {
        println myVariable
    }

}
```
In this case value of config variable `app.myVariable` or default value `-1` will be type casted to `Integer` type.

Code reloading
---------------
This plugin supports development with enabled code reloading (`grails run-app`) - variable values should be updated as you expect it. Variable value should be updated in the following cases:

*   When you add/remove annotated variable
*   When you change config variable value in `Config.groovy`
*   When you change value of `@GrailsConfig` annotation

@GrailsConfig and Grails Controllers
---------------
Don't use `@GrailsConfig` with Grails Controllers! By default Grails Controllers have `prototype` scope. As documentation [says](http://grails.org/doc/latest/guide/theWebLayer.html#controllersAndScopes) `A new controller will be created for each request`.
It means that `@GrailsConfig` annotation will spend more resources injecting config value for each request. You've got two ways to solve this problem:

1. Change controller's scope to `singleton`
2. Move `@GrailsConfig` annotation into service.

If you have any problems
-------------------------
If you have any problems or suggestions pls feel free [to ask me](https://github.com/fedotxxl/grails-config-annotation/issues) - I'll be glad to answer you.

In conclusion
------------
Pls note that `@GrailsConfig` only works in Spring beans (controller, services, tagLib's and etc.). Don't use it with POJO/POGO.