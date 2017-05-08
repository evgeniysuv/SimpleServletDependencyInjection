package inject;

import inject.abstracts.ApplicationContext;

/**
 * Created by esuvorov on 5/7/17.
 */
public class ApplicationContextImpl implements ApplicationContext {
    public void init(String xmlFile) {

    }

    public Object getBean(String name) {
        return getBean(name, Object.class);
    }

    public <T> T getBean(String name, Class<T> clazz) {


        return null;
    }
}
