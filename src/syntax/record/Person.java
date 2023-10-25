package syntax.record;

public record Person(String name, Person friend) {

    // 可以在record中定义静态变量和静态方法
    // 但是不能定义非静态的实例变量和实例方法
    public static String nation;
    public static String showNation() {
        return nation;
    }

    public Person(String name) {
        this(name, null);
    }

    public String getNameInUpper() {
        return name.toUpperCase();
    }


}
