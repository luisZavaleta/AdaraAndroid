package lx.zv.caching.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import lx.zv.caching.disposal.DisposalEnum;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Disposal {
	DisposalEnum disposalEnum() default DisposalEnum.LAST_FREQUENTLY_USED;
	int maxObjects() default 100;
	long maxMeters() default 1000;
	boolean preloadAsync() default false;
}
