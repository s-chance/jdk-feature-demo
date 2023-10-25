package syntax.trycatch;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TryCatchTest {
    // before jdk 7
    @Test
    public void testTryCatch() {
        // 传统的try-catch-finally写法，需要在finally中关闭资源
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("test.txt");
            bw = new BufferedWriter(fw);
            bw.write("hello");
        } catch (Exception e) {
            throw new RuntimeException("init failed");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("close failed");
            } finally {

                System.out.println("is fw closed? " + fw);
                System.out.println("is bw closed? " + bw);

                try {
                    if (fw != null) {
                        fw.close();
                    }
                } catch (Exception e) {
                    throw new RuntimeException("close failed");
                }
            }
        }
    }

    // after jdk 7
    @Test
    public void testTryCatch2() {
        // 在try()中初始化的资源，会在try语句结束后自动释放
        try (FileWriter fw = new FileWriter("test.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("hello");
        } catch (Exception e) {
            throw new RuntimeException("init failed");
        }

        try (FileInputStream fis = new FileInputStream("test.txt");
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr);
             FileOutputStream fos = new FileOutputStream("test2.txt");
             // convert to GBK
             OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
             BufferedWriter bw = new BufferedWriter(osw);
        ) {
            String str;
            while ((str = br.readLine()) != null) {
                bw.write(str);
                bw.newLine();
            }
        } catch (FileNotFoundException e) {
             throw new RuntimeException("file not found");
        } catch (IOException e) {
            throw new RuntimeException("io exception");
        }
    }

    // jdk 9 new feature
    @Test
    public void testTryCatch3() {
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        // 可以在外部初始化流，再到try ()内部进行引用，被引用的流对象也会在执行完后自动释放
        try (reader; writer) {
            // try () 中的内容不可再修改
            writer.write("123");
        } catch (IOException e) {
            throw new RuntimeException("io exception");
        }
    }
}
