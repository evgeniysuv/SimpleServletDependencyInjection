package ru.esuvorov.inject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by esuvorov on 5/7/17.
 */
public class ApplicationContextImpl implements ApplicationContext {

    private static final String BEAN_TAG_NAME = "bean";
    private static final String BEAN_ATTRIBUTE_NAME = "name";
    private static final String BEAN_ATTRIBUTE_CLASS = "class";
    private Map<String, Object> beans = new HashMap<>();

    public void init(String xmlFile) {
        File xmlCfgFile = getCfgFile(xmlFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlCfgFile);
        } catch (SAXException | IOException | ParserConfigurationException e1) {
            e1.printStackTrace();
        }
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName(BEAN_TAG_NAME);

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                try {
                    String beanName = eElement.getAttribute(BEAN_ATTRIBUTE_NAME);
                    Object bean = Class.forName(eElement.getAttribute(BEAN_ATTRIBUTE_CLASS)).newInstance();
                    beans.put(beanName, bean);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private File getCfgFile(String xmlFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(xmlFile).getFile());
    }


    public Object getBean(String name) {
        return getBean(name, Object.class);
    }

    public <T> T getBean(String name, Class<T> clazz) {
        T bean = (T) beans.get(name);
        if (bean != null) {
            return bean;
        }
        throw new IllegalArgumentException("No entity with name: " + name);
    }
}
