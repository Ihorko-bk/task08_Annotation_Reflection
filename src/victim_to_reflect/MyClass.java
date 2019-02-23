package victim_to_reflect;

import annotations.*;

import java.io.Serializable;
import java.time.LocalDate;

//@Deprecated
@MyTypeAnnotation
@MyTypeAnnotationWithParams(name = "Class name")
public class MyClass extends MyParentClass implements Serializable, Cloneable {

    @MyFieldAnnotation(canBeNull = false)
    public static String fname;
    @MyFieldAnnotation(canBeNull = true)
    public volatile String mname;
    @MyFieldAnnotation(canBeNull = false)
    public String sname;
    @MyFieldAnnotation(canBeNull = false)
    public LocalDate birthDate;

    SomeEnum someEnum;

    public final String STR = "Final string";

    @MyConstructorAnnotation
    public MyClass() {
    }

    MyClass(int i) {

    }
    protected MyClass(double d) {

    }

    public MyClass(String fname, String mname, String sname, LocalDate birthDate) {
        this.fname = fname;
        this.mname = mname;
        this.sname = sname;
        this.birthDate = birthDate;
    }

    @MyConstructorAnnotation
    private MyClass(String mname) {
        this.mname = mname;
    }

    public static void writeThis(String str) {

    }

    private int getSomeFromInts(int i1, int i2) {
        return 0;
    }

    @MyMethodAnnotation
    void some() {}

    public static void varargsMethod(int...args) {

    }
}

enum SomeEnum {
    FIRST, SECOND, THIRD
}