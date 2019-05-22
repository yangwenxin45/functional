package lambda;

import java.util.function.Consumer;

/**
 * 变量引用
 */
public class VarDemo {
    public static void main(String[] args) {
        final String str = "我们的时间";
        Consumer<String> consumer = s -> System.out.println(s + str);
        consumer.accept("1211");
    }
}
