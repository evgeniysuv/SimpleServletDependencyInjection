package inject.abstracts;

/**
 * Created by esuvorov on 5/7/17.
 */
public interface ApplicationContext {
    void init(String xmlFile);//todo need to implement parser custom xml file

    Object getBean(String name);

    <T> T getBean(String name, Class<T> clazz);
}
