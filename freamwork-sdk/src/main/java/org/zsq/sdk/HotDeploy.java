package org.zsq.sdk;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class HotDeploy {

    private static DefaultListableBeanFactory defaultListableBeanFactory;


    public static void execute(String jarLocation) throws Exception {

        Set<String> classNameSet = readJarFile(jarLocation);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:".concat(jarLocation))}, Thread.currentThread().getContextClassLoader());
        for (String item : classNameSet) {
            Class cls = urlClassLoader.loadClass(item);
            if (isSpringBeanClass(cls)) {
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(cls);
                AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
                defaultListableBeanFactory.registerBeanDefinition(transformName(item), beanDefinitionBuilder.getBeanDefinition());

            }
        }

    }

    public static void setFactory(DefaultListableBeanFactory factory) {
        HotDeploy.defaultListableBeanFactory = factory;
    }


    private static String transformName(String className) {
        String[] split = className.split("\\.");
        String lastString=split[split.length-1];
        String first = lastString.substring(0, 1);
        first=first.toLowerCase();
        String end = lastString.substring(1);
        return first.concat(end);
    }


    public static Set<String> readJarFile(String jarAddress) throws IOException {
        Set<String> classNameSet = new HashSet<String>();
        JarFile jarFile = new JarFile(jarAddress);
        Enumeration<JarEntry> entries = jarFile.entries();//遍历整个jar文件
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String name = jarEntry.getName();
            if (name.endsWith(".class")) {
                String className = name.replace(".class", "").replaceAll("/", ".");
                classNameSet.add(className);
            }
        }
        return classNameSet;
    }


    /**
     * 方法描述 判断class对象是否带有spring的注解
     */
    public static boolean isSpringBeanClass(Class<?> cla) {
        if (cla == null) {
            return false;
        }
        //是否是接口
        if (cla.isInterface()) {
            return false;
        }
        //是否是抽象类
        if (Modifier.isAbstract(cla.getModifiers())) {
            return false;
        }
        if (cla.getAnnotation(Component.class) != null) {
            return true;
        }
        if (cla.getAnnotation(Repository.class) != null) {
            return true;
        }
        if (cla.getAnnotation(Service.class) != null) {
            return true;
        }
        return false;
    }
}
