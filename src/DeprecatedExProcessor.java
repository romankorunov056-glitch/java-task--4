// DeprecatedExProcessor.java
import java.lang.reflect.Method;

public class DeprecatedExProcessor {
    public void processClass(Class<?> clazz) {
        // Проверка аннотации на классе
        if (clazz.isAnnotationPresent(DeprecatedEx.class)) {
            DeprecatedEx annotation = clazz.getAnnotation(DeprecatedEx.class);
            System.out.println("! класс '" + clazz.getSimpleName() + "' устарел – альтернатива: '" + annotation.message() + "'");
        }

        // Проверка методов
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(DeprecatedEx.class)) {
                DeprecatedEx annotation = method.getAnnotation(DeprecatedEx.class);
                System.out.println("! метод '" + method.getName() + "' устарел – альтернатива: '" + annotation.message() + "'");
            }
        }
    }
}