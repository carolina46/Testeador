package anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface MockStringAttribute {
	String[] value();
}
