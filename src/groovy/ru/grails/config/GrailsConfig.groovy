package ru.grails.config
import java.lang.annotation.*

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GrailsConfig {
    String value()
}
