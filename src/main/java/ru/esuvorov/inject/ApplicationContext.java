package ru.esuvorov.inject;

/**
 * Created by esuvorov on 5/7/17.
 */
public interface ApplicationContext {
    void init(String xmlFile);

    Object getBean(String name);

    <T> T getBean(String name, Class<T> clazz);
}
