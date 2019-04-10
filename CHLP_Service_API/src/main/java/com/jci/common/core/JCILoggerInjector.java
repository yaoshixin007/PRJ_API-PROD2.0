package com.jci.common.core;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

/**
 * {@link org.springframework.beans.factory.config.BeanPostProcessor} implementation that InjectableLogger annotated fields Such members to be injected with {@link org.slf4j.Logger} are detected by default JCI Logger'
 * {@link InjectableLogger} annotations
 * {@link org.slf4j.Logger} fields are injected right after construction of a bean, before any config methods are invoked. Such a config field does not have to be public
 * Note: JCILoggerInjector will be registered by the "context:component-scan" XML tags.
 * @author apiadmin
 *
 */

@Component
public class JCILoggerInjector implements BeanPostProcessor {

    /**
     * Default Non-parameterized constructor.
     */
    public JCILoggerInjector() {

        super();
    }

    /**
     * Return bean instance with injected {@link org.slf4j.Logger}.
     *
     * @param bean
     *            Object the target instance the logger is used.
     * @param beanName
     *            String name of the bean having the {@link InjectableLogger} Annotation.
     * @return bean
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        return bean;
    }

    /**
     * Injecting the logger into bean class. postProcessBeforeInitialization method calls with an arbitrary target instance and bean name of the instance, the fields Mark with the {@link InjectTableLogger} annotation will be set with the Logger
     * Instance created using {@link LoggerFactory} 's method getLogger passing the name of the class.
     *
     * @param bean
     *            Object the target instance the logger is used.
     * @param beanName
     *            String name of the bean having the {@link InjectableLogger} Annotation.
     * @return bean
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) {

        ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {

            @Override
            public void doWith(Field field) throws IllegalAccessException {

                // make the field accessible if defined private
                ReflectionUtils.makeAccessible(field);
                if (field.getAnnotation(InjectableLogger.class) != null) {
                    Logger log = LoggerFactory.getLogger(bean.getClass());
                    field.set(bean, log);
                }
            }
        });
        return bean;
    }

}
