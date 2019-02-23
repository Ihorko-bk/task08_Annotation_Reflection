import victim_to_reflect.MyClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class Reflection {

    public static void main(String[] args) {
        showClassDeclaration(new MyClass());
    }

    public static void showClassDeclaration(Object o) {
        showClassDeclaration(o.getClass());
    }
    public static void showClassDeclaration(Class classs) {
        showClassAnnotations(classs);
        Class superclass = classs.getSuperclass();
        Class[] interfaces = classs.getInterfaces();
        System.out.println(
                inYellowColor("class ") +
                        classs.getSimpleName() +
                        ((superclass == Object.class) ? "" :
                                inYellowColor(" extends ") + superclass.getSimpleName()) +
                        ((interfaces.length == 0) ? "" :
                                inYellowColor(" implements") + getStrOfListInterfaces(interfaces)) +
                " {"
        );
        System.out.println();
        showDeclaredFields(classs);
        System.out.println();
        showDeclaredConstructors(classs);
        System.out.println();
        showDeclaredMethods(classs);
        System.out.println();
        System.out.println("}");
    }

    private static String inYellowColor(String string) {
        return yellowColor() + string + normalColor();
    }
    private static String inBYellowColor(String string) {
        return bYellowColor() + string + normalColor();
    }
    private static String yellowColor() {
        return (char)27 + "[33m";
    }
    private static String bYellowColor() {
        return (char)27 + "[93m";
    }
    private static String normalColor() {
        return (char)27 + "[29m";
    }

    private static String getStrOfListInterfaces(Class[] interfaces) {
        StringBuilder sb = new StringBuilder();
        for (Class i: interfaces) {
            sb.append(" " + i.getSimpleName() + ",");
        }
        return sb.substring(0, sb.length());
    }

    public static void showClassAnnotations(Class classs) {
        Annotation[] annotations = classs.getDeclaredAnnotations();
        if (annotations.length == 0) return;
        for (Annotation a:annotations) System.out.println(inBYellowColor("@" + a.annotationType().getSimpleName()));
    }
    public static void showFieldsAnnotations(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        showAnnotations(annotations);
    }
    public static void showConstructorAnnotations(Constructor constructor) {
        Annotation[] annotations = constructor.getDeclaredAnnotations();
        showAnnotations(annotations);
    }
    public static void showMethodAnnotations(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        showAnnotations(annotations);
    }
    static void showAnnotations(Annotation[] annotations) {
        if (annotations.length == 0) return;
        for (Annotation a:annotations) {
            System.out.println(inBYellowColor("  @" + a.annotationType().getSimpleName()));
        }
    }

    public static void showDeclaredFields(Class classs) {
        Field[] fields = classs.getDeclaredFields();
        if (fields.length == 0) return;
        for(Field f: fields) {
            showFieldsAnnotations(f);

        }
    }
    public static void showField(Field field) {
        int modifiers = field.getModifiers();
        Class type = field.getType();
        System.out.println("  " +
                ((modifiers == 0) ? "" : inYellowColor(Modifier.toString(modifiers)+" ")) +
                ((type.isPrimitive()) ? inYellowColor(type.getSimpleName()) : type.getSimpleName()) + " " +
                field.getName() + ";"
        );
    }

    public static void showDeclaredConstructors(Class classs) {
        Constructor[] constructors = classs.getDeclaredConstructors();
        int modifiers;
        for (Constructor c: constructors) {
            showConstructorAnnotations(c);
            modifiers = c.getModifiers();
            System.out.println( "  " +
                    (modifiers == 0 ? "" : inYellowColor(Modifier.toString(modifiers)+" ")) +
                    c.getName() + "(" +
                    getParametersString(c.getParameterTypes(), c.getParameters()) +
                    ");");
        }
    }

    public static void showDeclaredMethods(Class classs) {
        Method[] methods = classs.getDeclaredMethods();
        if (methods.length == 0) return;
        int modifiers;
        Class returnType;
        for (Method m: methods) {
            showMethodAnnotations(m);
            modifiers = m.getModifiers();
            returnType = m.getReturnType();
            System.out.println( "  " +
                    (modifiers == 0 ? "" : inYellowColor(Modifier.toString(modifiers)+" ")) +
                    ((m.getReturnType().isPrimitive()) ?
                            inYellowColor(returnType.getSimpleName()) :
                            returnType.getSimpleName()) + " " +
                    m.getName() + "(" +
                    getParametersString(m.getParameterTypes(), m.getParameters()) +
                    ");");
        }
    }

    private static String getParametersString(Class[] parametersTypes, Parameter[] parameters) {
        StringBuilder sb = new StringBuilder();
        Class type;
        for (int i = 0; i < parametersTypes.length; i++) {
            type = parameters[i].getType();
            sb.append(((type.isPrimitive()) ? inYellowColor(type.getSimpleName()) : type.getSimpleName()) + " " +
                    parameters[i].getName() +
                    ((i!=parameters.length-1)?", ":""));
        }
        return sb.toString();
    }


}
