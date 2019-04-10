package com.jci.common.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a {@link org.slf4j.static Logger} field to be
 * injected by Spring's
 * dependency injection facilities. {@link org.slf4j.static Logger} Field is injected
 * right after construction,property set and custom init
 * method of a bean, before any config methods are invoked
 * @author apiadmin
 * @version 
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface InjectableLogger {

}
