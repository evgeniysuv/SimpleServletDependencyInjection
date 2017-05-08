package controller;

import annotation.Inject;
import inject.abstracts.ApplicationContext;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;

/**
 * Created by esuvorov on 5/7/17.
 */
public class DependencyInjectionServlet extends HttpServlet {

    private static final String APP_CTX_PATH = "appCtxPath";
    private static final String APP_CTX_CLASS = "appCtxClass";

    private static Logger logger = Logger.getLogger(DependencyInjectionServlet.class.getName());

    @Override
    public void init() throws ServletException {
        ApplicationContext context = null;
        try {
            context = (ApplicationContext) Class.forName(this.getInitParameter(APP_CTX_CLASS)).newInstance();
            context.init(APP_CTX_PATH);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        if (context == null)
            return;


        Field[] fields = this.getClass().getDeclaredFields();
        System.out.println("DependencyInjectionServlet init() method has been called");
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().getDeclaredFields().length);
        for (Field field : this.getClass().getDeclaredFields()) {
            field.getName();
        }
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName());
            System.out.println(field.getAnnotations());
            Inject annotation = field.getAnnotation(Inject.class);
            if (annotation != null) {
                String beanName = annotation.value();
                System.out.println("@Inject annotation with value = " + beanName + "has been found");
                Object bean = context.getBean(beanName);

                if (bean == null) {
                    throw new IllegalStateException();
                }
                try {
                    field.set(this, bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
