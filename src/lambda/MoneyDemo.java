package lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

// lambda 只需要知道输入是什么，输出是什么
interface IMoneyFormat{
    String format(int i);
}

class MyMoney {
    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer, String> moneyFormat) {
        System.out.println("我的存款：" + moneyFormat.apply(this.money));
    }
}

public class MoneyDemo {
    public static void main(String[] args) {
        MyMoney me = new MyMoney(99999999);
        Function<Integer, String> moneyFormat = i -> new DecimalFormat("#,###").format(i);
//        me.printMoney(moneyFormat);

        // 函数接口链式操作
        me.printMoney(moneyFormat.andThen(s -> "人民币" + s));
    }
}
