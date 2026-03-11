// ExampleClass.java
@DeprecatedEx(message = "используйте NewExampleClass")
public class ExampleClass {
    @JsonField(name = "id")
    private int id;

    @JsonField(name = "description")
    private String text;

    private int secret; // без аннотации не будет в JSON

    public ExampleClass(int id, String text, int secret) {
        this.id = id;
        this.text = text;
        this.secret = secret;
    }

    @DeprecatedEx(message = "используйте newMethod()")
    public void oldMethod() {
        System.out.println("Старый метод");
    }

    public void newMethod() {
        System.out.println("Новый метод");
    }
}