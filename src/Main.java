// Main.java
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Задание 1.1: Лямбда для Printable ===");
        Printable printable = () -> System.out.println("Hello, Printable!");
        printable.print();

        System.out.println("\n=== Задание 1.2: Проверка строки (Predicate) ===");
        // 1.2.1: проверка, что строка не null
        Predicate<String> notNull = s -> s != null;
        // 1.2.2: проверка, что строка не пуста
        Predicate<String> notEmpty = s -> !s.isEmpty();
        // 1.2.3: комбинация через and()
        Predicate<String> notNullOrEmpty = notNull.and(notEmpty);

        String test1 = null;
        String test2 = "";
        String test3 = "Java";

        System.out.println("null не null? " + notNull.test(test1));
        System.out.println("null не пуст? " + notEmpty.test(test1)); // будет NullPointerException, поэтому осторожно
        // безопасная проверка:
        System.out.println("null не null и не пуст? " + (test1 != null ? notEmpty.test(test1) : false));
        System.out.println("\"\" не null и не пуст? " + notNullOrEmpty.test(test2));
        System.out.println("\"Java\" не null и не пуст? " + notNullOrEmpty.test(test3));

        System.out.println("\n=== Задание 1.3: Проверка строки (J/N ... A) ===");
        Predicate<String> startsWithJorN = s -> s.startsWith("J") || s.startsWith("N");
        Predicate<String> endsWithA = s -> s.endsWith("A");
        Predicate<String> combined = startsWithJorN.and(endsWithA);

        String test4 = "JavaA";
        String test5 = "NovaA";
        String test6 = "Joker";
        System.out.println(test4 + " -> " + combined.test(test4));
        System.out.println(test5 + " -> " + combined.test(test5));
        System.out.println(test6 + " -> " + combined.test(test6));

        // Дополнительные задания (1.4-1.6) – раскомментируйте при желании
        // demoExtra();

        System.out.println("\n=== Задание 2.1: Обработчик аннотации @DeprecatedEx ===");
        DeprecatedExProcessor processor = new DeprecatedExProcessor();
        processor.processClass(ExampleClass.class);

        // Дополнительное задание 2.2* – раскомментируйте при желании
        // demoJsonSerialization();
    }

    // Демонстрация дополнительных заданий 1.4-1.6
    public static void demoExtra() {
        System.out.println("\n=== Задание 1.4*: Consumer для HeavyBox ===");
        HeavyBox box = new HeavyBox(100);
        Consumer<HeavyBox> ship = b -> System.out.println("Отгрузили ящик с весом " + b.weight);
        Consumer<HeavyBox> send = b -> System.out.println("Отправляем ящик с весом " + b.weight);
        Consumer<HeavyBox> combinedConsumer = ship.andThen(send);
        combinedConsumer.accept(box);

        System.out.println("\n=== Задание 1.5*: Function для числа ===");
        Function<Integer, String> sign = n -> {
            if (n > 0) return "Положительное число";
            else if (n < 0) return "Отрицательное число";
            else return "Ноль";
        };
        System.out.println("5 -> " + sign.apply(5));
        System.out.println("-3 -> " + sign.apply(-3));
        System.out.println("0 -> " + sign.apply(0));

        System.out.println("\n=== Задание 1.6*: Supplier случайного числа ===");
        Supplier<Integer> randomSupplier = () -> new Random().nextInt(11); // от 0 до 10 включительно
        System.out.println("Случайное число: " + randomSupplier.get());
        System.out.println("Случайное число: " + randomSupplier.get());
    }

    // Демонстрация дополнительного задания 2.2*
    public static void demoJsonSerialization() {
        System.out.println("\n=== Задание 2.2*: Сериализация в JSON ===");
        ExampleClass obj = new ExampleClass(42, "Test", 100);
        String json = JsonSerializer.toJson(obj);
        System.out.println("JSON: " + json);
    }
}