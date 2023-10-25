package syntax.textblock;

import org.junit.Test;

public class TextBlock {

    // jdk 13 new feature
    // 文本块，在python诞生之初就有了，现在java也有了
    // 但是为什么当初没有引入呢？因为java中的字符串是不可变的，如果引入文本块，那么就会引入可变的字符串即转义字符，这是java设计者不想看到的
    // 所以到了现在的文本块依旧是不可变的，文本块只是一种语法糖，编译器会在编译时将文本块转换为普通的字符串
    @Test
    public void testTextBlock() {
        // 传统的字符串拼接方式
        String str = "hello\n"
                + "world\n"
                + "java\n";
        System.out.print(str);
        // 使用文本块
        String textBlock = """
                hello
                world
                """;
        System.out.print(textBlock);

        // 如果末尾不需要换行，可以这么写
        String textBlock2 = """
                hello
                world""";
        System.out.print(textBlock2);

        // 文本块对于内容比较多的字符串拼接非常有用，例如html代码、sql代码和json代码等
        // 传统的写法
        String html =
                "<html>\n" +
                "    <body>\n" +
                "        <p>Hello, world</p>\n" +
                "    </body>\n" +
                "</html>\n";

        String json =
                "{\n" +
                "    \"name\": \"Bob\",\n" +
                "    \"age\": 20\n" +
                "}\n";
        // 使用文本块
        String htmlBlock = """
                <html>
                    <body>
                        <p>Hello, world</p>
                    </body>
                </html>
                """;
        String jsonBlock = """
                {
                    "name": "Bob",
                    "age": 20
                }
                """;

        // jdk 14 中引入了 \s 用于表示空白字符，包括空格、制表符、换行符等
        // 单独一个 \ 可以取消换行符的转义
        String htmlBlock2 = """
                <html>\s
                    <body>\s
                        <p>Hello, world</p>\s
                    </body>\s
                </html>\s
                """;
        System.out.print(htmlBlock2);

        // jdk 15 中文本块成为了永久特性，不再是预览特性
    }
}
