Описание
-----------
Данный плагин позволяет удобно использовать конфигурационные данные (как правило из файла `Config.groovy`) в бинах Spring'а (сервисах, контроллерах, taglib'ах и т.д.).
Рассмотрим, как это можно было сделать раньше:
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
Подход неплохой, но зачем внедрять бин `grailsApplication` только для того, чтобы получить конфигурационное значение? Давайте чуть-чуть улучшим данный код:
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
Уже лучше. Но можно сделать данный код еще короче...

Как получать конфигурационные значения при помощи плагина Grails Config Annotation?
----------------------------------------------------------------------------------
Посмотрите как просто получить конфигурационное значение, используя плагин Grails Config Annotation:

```java
import com.tenlittleniggers.grails.config.GrailsConfig

class MyService {

    @GrailsConfig("app.myVariable")
    private myVariable

    void doSomeCoolStuff() {
        println myVariable
    }

}
```
Все очень просто:

1. Импортируем аннотацию: `import com.tenlittleniggers.grails.config.GrailsConfig`
2. Добавляем аннотацию к переменной. В значении аннотации указываем имя переменной, которую необходимо получить из файла `Config.groovy` (в нашем случае `app.myVariable`):  `@GrailsConfig("app.myVariable")`.

Значения по-умолчанию
---------------------
Вы можете указать значение по-умолчанию для переменной. Если в файле `Config.groovy` требуемая конфигурационная переменная не найдена, то будет использовано значение по-умолчанию. Для этого в значении аннотации добавьте `:`, а затем значение по-умолчанию. Например:

```java
import com.tenlittleniggers.grails.config.GrailsConfig

class MyService {

    @GrailsConfig("app.myVariable:my default value")
    private myVariable

    void doSomeCoolStuff() {
        println myVariable
    }

}
```
Если в файле `Config.groovy` переменная `app.myVariable` найдена не будет, то для переменной `myVariable` будет использовано значение `my default value`.

Преобразование типов
--------------------
Вы можете явно указать тип переменной. В этом случае конфигурационное значение будет преобразовано к требуемому типу, например:

```java
import com.tenlittleniggers.grails.config.GrailsConfig

class MyService {

    @GrailsConfig("app.myVariable:-1")
    private Integer myVariable

    void doSomeCoolStuff() {
        println myVariable
    }

}
```
В данном примере значение конфигурационной переменной `app.myVariable` или значение по-умолчанию `-1` будет преобразовано к типу `Integer`.

Перегрузка кода
---------------
Плагин полностью поддерживает разработку с включенной перегрузкой кода (`grails run-app`) - значения конфигурационных переменных должно обновляться, как это и ожидается. Это включает в себя ситуации:

*   Добавление/удаление переменной
*   Изменение значения переменной в файле `Config.groovy`
*   Изменение значения аннотации `@GrailsConfig`

При возникновении проблем
-------------------------
Если у вас какие-либо проблемы с плагином или есть предложения по усовершенствованию, пожалуйста, [сообщите мне](https://github.com/fedotxxl/grails-config-annotation/issues) - буду рад ответить. 

В заключение
------------
Данный плагин добавляет в Grails функциональность очень похожу на аннотацию `@Value` в Spring. Помните, что аннотация `@GrailsConfig` работает только в бинах Spring'а (контроллеры, сервисы, tagLib'ы и т.д.).