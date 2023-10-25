package syntax.instanceoffeature;

import org.junit.Test;

public class InstanceofTest {

    // before jdk 14
    @Test
    public void testInstanceof() {
        Object obj = new String("hello world");
        if (obj instanceof String) {
            String str = (String) obj;
            System.out.println(str.contains("hello"));
        } else {
            System.out.println("not String");
        }
    }

    // after jdk 14, jdk 16 formal supported
    @Test
    public void testInstanceof2() {
        Object obj = new String("hello world");

        // 新特性省去了强制类型转换的代码
        if (obj instanceof String str) {
            System.out.println(str.contains("hello"));
        } else {
            System.out.println("not String");
        }
    }
}

class InstanceOf {
    String str = "abc";

    public void test(Object obj) {
        if (obj instanceof String str) { // str的作用域仅在if之内
            System.out.println(str.toUpperCase());
        } else {
            System.out.println(str.toLowerCase());
        }
    }
}

class Monitor {
    private String model;
    private double price;

 /*   public boolean equals(Object o) {
        if (o instanceof Monitor monitor) {
            if (model.equals(monitor.model)
                    && price == monitor.price) {
                return true;
            }
        }
        return false;
    }*/

    // 换成以下写法
    public boolean equals(Object o) {
        return o instanceof Monitor monitor
                && model.equals(monitor.model)
                && price == monitor.price;
    }
}