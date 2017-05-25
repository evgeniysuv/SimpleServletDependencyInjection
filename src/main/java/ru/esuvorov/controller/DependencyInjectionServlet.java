package ru.esuvorov.controller;

import org.apache.log4j.Logger;
import ru.esuvorov.annotation.Inject;
import ru.esuvorov.exceptions.ApplicationContextNotFoundException;
import ru.esuvorov.inject.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by esuvorov on 5/7/17.
 */
public class DependencyInjectionServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DependencyInjectionServlet.class);
    private static final String APP_CTX_CONFIG_PATH = "appCtxPath";
    private static final String APP_CTX_CLASS = "appCtxClass";

    @Override
    public void init() throws ServletException {
        ApplicationContext context = getApplicationContext();
        List<Field> injectedFields = getInjectedFields(this.getClass());

        for (Field field : injectedFields) {
            field.setAccessible(true);
            Inject injectAnnotation = field.getAnnotation(Inject.class);
            String beanName = injectAnnotation.value();
            Object bean = context.getBean(beanName);
            try {
                field.set(this, bean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private ApplicationContext getApplicationContext() {
        ApplicationContext context = null;
        try {
            context = (ApplicationContext) Class.forName(this.getInitParameter(APP_CTX_CLASS)).newInstance();
            context.init(this.getInitParameter(APP_CTX_CONFIG_PATH));
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        if (context != null)
            return context;

        throw new ApplicationContextNotFoundException();
    }

    private List<Field> getInjectedFields(Class clazz) {
        Field[] allFields = clazz.getDeclaredFields();
        List<Field> injectedFields = new ArrayList<>();
        for (Field field : allFields) {
            field.setAccessible(true);
            Inject injectAnnotation = field.getAnnotation(Inject.class);
            if (injectAnnotation != null) {
                LOGGER.debug("@Inject annotation on field = " + field.getName() + "has been found");
                injectedFields.add(field);
            }
        }
        return injectedFields;
    }
}
