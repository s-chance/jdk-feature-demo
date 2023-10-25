package syntax.record;

import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;

public class RecordTest {

    // jdk 14 new feature preview
    // record是一种新的引用类型，类似于class和interface
    // record可以自动编译出构造器、getter、equals、hashCode、toString等方法
    // 但是它不能自动编译出setter方法，因为record是不可变的
    // record的实用性还不是很高，因为它不能继承其他类，也不能实现接口
    @Test
    public void testRecord() {
        // 使用Dog类测试
        Dog dog1 = new Dog("旺财", 3);
        Dog dog2 = new Dog("大黄", 5);
        Dog dog3 = new Dog("拉布拉多", 2);
        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println(dog3);

        // 使用Person类测试
        // 构造器测试
        Person person = new Person("张三", new Person("李四", null));
        // toString测试
        System.out.println(person);
        // equals测试
        Person person1 = new Person("张三", new Person("李四", null));
        System.out.println(person.equals(person1));
        // hashCode测试
        HashSet<Person> set = new HashSet<>();
        set.add(person);
        set.add(person1);
        for (Person p : set) {
            System.out.println(p);
        }

        // 获取属性测试
        System.out.println(person.name());
        System.out.println(person.friend());

        // 自定义方法测试
        Person alice = new Person("alice");
        System.out.println(alice.getNameInUpper());
        Person.nation = "China";
        System.out.println(Person.showNation());
    }
}

// 旧写法
class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Point point = (Point) object;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

// 新写法
record RPoint(int x, int y) {}

