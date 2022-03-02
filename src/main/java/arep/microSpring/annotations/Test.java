package arep.microSpring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Las anotaciones deben ser registradas en el archivo
// de clase por el compilador y retenidas por la VM en
// tiempo de ejecución, para que puedan leerse reflexivamente
@Retention(RetentionPolicy.RUNTIME)
// Denota que un elemento es un metodo
@Target(ElementType.METHOD)
public @interface Test {
}
