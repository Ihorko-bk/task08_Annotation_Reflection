package task3;

import annotations.MyFieldAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    @MyFieldAnnotation(canBeNull = false)
    public String l;


    public static void main(String[] args) {
        try {
            showAnnotationValues(Main.class.getDeclaredFields()[0].getDeclaredAnnotations()[0]);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void showAnnotationValues(Annotation annotation) throws InvocationTargetException, IllegalAccessException {
        Class<? extends Annotation> type = annotation.annotationType();
        System.out.println("@" + type.getSimpleName() + "(");
Math.sqrt()
        for (Method method : type.getDeclaredMethods()) {
            Object value = method.invoke(annotation, (Object[])null);
            System.out.println(" " + method.getName() + ": " + value);
        }

        System.out.println(")");
    }
}
