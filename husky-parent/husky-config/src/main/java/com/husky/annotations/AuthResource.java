package com.husky.annotations;

public @interface AuthResource {

	String code() default "";

	String desc() default "";
	
}
