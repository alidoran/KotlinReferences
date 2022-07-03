package alidoran.kotlin_base;


import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

import ir.alidoran.teach_kotlin.ExtensionsKotlin;
import ir.alidoran.teach_kotlin.MethodTypeManualName;import ir.alidoran.teach_kotlin.ObjectLearn;import ir.alidoran.teach_kotlin.TwoSameNameExtension;import kotlin.jvm.functions.Function0;


public class AccessKotlinFromJava {
    public static void main(String[] args){
        AccessKotlinFromJava.fInput(() -> true);
        AccessKotlinFromJava.fInput2(new InterfaceLearn.SimpleInterface() {
            @NonNull
            @Override
            public String getInterfaceName() {
                return "Ali";
            }
        });
    }

    public static void fInput(Function0<Boolean> as){
        as.invoke();
    }

    public static void fInput2(InterfaceLearn.SimpleInterface interfaceL){

    }

    public static void JavaMethod() {
        MethodTypeManualName.outOfClassMethod();
    }

    public static void callExtension() {
        Character lChar = ExtensionsKotlin.lastChar("abc");
        Character s = ExtensionsKotlin.firstCharRepeat("abc", 2);
    }

    public void callKotlinObject(){
        ObjectLearn.SingletonKotlin.INSTANCE.objectMethod();
    }

    public void callKotlinSingleton(){
        ObjectLearn.ObjectWithoutAnnotation.INSTANCE.getA();
        ObjectLearn.ObjectWithAnnotation.getB();
    }

    public void callTwoSameNameExtension(){
        List<Integer> intList = Arrays.asList(1,2);
        List<Double> doubleList = Arrays.asList(1d,2d);
        new TwoSameNameExtension().sameNameIntInput(intList);
        new TwoSameNameExtension().sameName(doubleList);
    }
}
