package syntax.switchfeature;

import org.junit.Test;

public class SwitchTest {

    // before jdk 12
    @Test
    public void testSwitch() {
        Color color = Color.RED;

        // 每个case后面都需要加break，否则会出现case穿透
        // 所有的case共享一个作用域，不能在不同的case定义同名变量
        // switch无法返回值
        switch (color) {
            case RED:
                System.out.println("RED");
                break;
            case GREEN:
                System.out.println("GREEN");
                break;
            default:
                System.out.println("BLUE");
        }
    }


    // jdk 12 new feature preview
    @Test
    public void testNewSwitch() {
        // 使用新的switch表达式，简化了写法
        // 使用 case L -> 的格式替代了break，也就是说不需要再写break了
        // 同时新写法将case压缩到一行，看起来更加简洁
        // 新版本jdk向下兼容，可以使用旧的写法，但是不能在同一个switch中混用新旧写法
        Color color = Color.RED;
        switch (color) {
            case RED -> System.out.println("RED");
            case GREEN -> System.out.println("GREEN");
            default -> System.out.println("BLUE");
        }
        // switch返回值可以这样写，这里可加也可不加yield
        // yield 在jdk 13中引入，jdk 12中还不能使用
        int n = switch (color) {
            case RED -> 1;
            case GREEN -> 2;
            default -> 3;
        };
        System.out.println("n = " + n);
    }

    // jdk 13 new feature preview, jdk 14 formal supported
    @Test
    public void testYield() {
        // 新版本jdk引入了yield关键字，可以在switch中使用
        // yield的作用是返回一个值，这个值就是switch的返回值
        // 在case L -> 的格式中，如果case体很简单也可以直接写返回值例如 case 1 -> 1;
        // 如果是旧写法，则需要使用yield返回值
        // yield 和 return 的区别是，yield会暂停当前的执行，而return会直接结束当前的执行
        Color color = Color.RED;
        int n = switch (color) {
            case RED -> {
                System.out.println("RED");
                yield 1;
            }
            case GREEN -> {
                System.out.println("GREEN");
                yield 2;
            }
            default -> {
                System.out.println("BLUE");
                yield 3;
            }
        };
        System.out.println("n = " + n);
    }


    // jdk 17 new feature preview
    // switch模式匹配
    // 旧写法
    static String formatter(Object o) {
        String formatted = "unknown";
        if (o instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (o instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (o instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (o instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }

    // 新写法，jdk 17 中需要开启preview特性才能使用，jdk 21 中正式发布
    static String patternSwitch(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> o.toString();
        };
    }

    @Test
    public void testPattern() {
        System.out.println(formatter(11));
        System.out.println(patternSwitch(22717.4));
    }
}
enum Color {
    RED, GREEN, BLUE
}