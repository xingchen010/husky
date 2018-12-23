package com.husky.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.husky.enums.Polocy;
import static com.husky.enums.Polocy.*;


@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AuthOperation {
 
	String code() default "";

	String desc() default "";
	
	Polocy polocy() default LOGIN;
}
