// JsonSerializer.java
import java.lang.reflect.Field;
import java.util.StringJoiner;

public class JsonSerializer {
    public static String toJson(Object obj) {
        Class<?> clazz = obj.getClass();
        StringJoiner joiner = new StringJoiner(", ", "{", "}");

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField annotation = field.getAnnotation(JsonField.class);
                String jsonName = annotation.name();
                try {
                    Object value = field.get(obj);
                    joiner.add("\"" + jsonName + "\": " + formatValue(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return joiner.toString();
    }

    private static String formatValue(Object value) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return String.valueOf(value);
    }
}