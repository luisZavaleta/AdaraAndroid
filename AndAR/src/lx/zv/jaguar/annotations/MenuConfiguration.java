package lx.zv.jaguar.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.graphics.Typeface;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MenuConfiguration {
	
int textSize() default 20;	
int[] ARGBColor() default {0xff,0x00,0x00,0x00};
boolean antiAlias() default true;
/*
 Rules for font family:
 1.- Intenta tratar el string como uno de los fontfamilies por default de android. 
 2.- Desde el asset Manager.
 3.- Desde archivo.
 4.- Si no puede con ninguno de los tres carga la fuente por default.
 */
String fontFamily() default "" ;
int fontStyle() default Typeface.NORMAL;
float widthPercentage() default .9f;
float heightPercentage() default .25f;

}
