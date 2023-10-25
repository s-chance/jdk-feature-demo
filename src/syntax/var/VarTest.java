package syntax.var;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class VarTest {

    // jdk 10 new feature
    @Test
    public void testVar() {
        // var可以用于局部变量声明时省略显式的类型声明
        var list = new ArrayList<String>();

        var set = new LinkedHashSet<Integer>();

        list.add("hello");
        list.add("world");

        for (var v : list) {
            System.out.println(v);
        }

        for (var i = 0; i < 4; i++) {
            System.out.println("i = " + i);
        }

        set.add(1);
        set.add(1);
        set.add(2);
        set.add(3);

        var iterator = set.iterator();

        // var不适用于以下场景
        // 1.成员变量
        // 2.数组变量
        // 3.方法的返回值类型
        // 4.方法的参数类型
        // 5.没有初始化的方法内的局部变量
        // 6.异常类型Exception等
        // 7.Lambda表达式中函数式接口类型
        // 8.方法引用中函数式接口类型
    }
}
