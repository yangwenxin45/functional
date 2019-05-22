package lambda;

@FunctionalInterface
interface Interface1 {
    int doubleNum(int i);

    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface Interface2 {
    int doubleNum(int i);

    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface Interface3 extends Interface2, Interface1 {

    @Override
    default int add(int x, int y) {
        return Interface1.super.add(x, y);
    }
}

/**
 * lambda表达式就是实现了指定接口的对象实例
 */
public class LamdbaDemo1 {
    public static void main(String[] args) {
        Interface1 i1 = (i) -> i*2;
        System.out.println(i1.add(3, 7));
        System.out.println(i1.doubleNum(20));
        // 最常见写法
        Interface1 i2 = i -> i*2;

        Interface1 i3 = (int i) -> i*2;

        Interface1 i4 = (int i) -> {
            System.out.println("-----");
            return i*2;
        };
    }
}
