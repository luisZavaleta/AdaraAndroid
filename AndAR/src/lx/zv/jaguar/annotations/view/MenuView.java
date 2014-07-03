package lx.zv.jaguar.annotations.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MenuView {
	boolean sincronizate() default true;
	boolean photo() default true;
}
