package task2;

import annotations.MyFieldAnnotation;
import javafx.scene.effect.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {

    @MyFieldAnnotation(canBeNull = false)
    private String firstName;

    @MyFieldAnnotation(canBeNull = false)
    public String lastName;

    public String somethingElse;

    int age;


    public static void main(String[] args) {
        showFieldsWithAnotation(Main.class, MyFieldAnnotation.class);
    }



    public static void showFieldsWithAnotation(Class classs, Class<? extends Annotation> annotation) {
        Field[] fields = classs.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(annotation)) {
                showField(f);
            }
        }
    }

    public static void showField(Field field) {
        int modifiers = field.getModifiers();
        Class type = field.getType();
        System.out.println(
                ((modifiers == 0) ? "" : inYellowColor(Modifier.toString(modifiers)+" ")) +
                ((type.isPrimitive()) ? inYellowColor(type.getSimpleName()) : type.getSimpleName()) + " " +
                field.getName() + ";"
        );
    }
    private static String inYellowColor(String string) {
        return yellowColor() + string + normalColor();
    }
    private static String yellowColor() {
        return (char)27 + "[33m";
    }
    private static String normalColor() {
        return (char)27 + "[29m";
    }
}
