import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author tuhao
 * @date 2020/11/28 1:48 下午
 * @desc
 */
public class IntegerTest {
    @Test
    public void test1() {
        Integer i1 = 66;// 通过反编译可以知道 Integer i = 66; 这种形式声明的变量是通过 java.lang.Integer#valueOf(int) 来构造 Integer 对象的
        Integer i2 = 66;
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i1 == i2);// true，在[-128,127]内时，会直接从 IntegerCache 中获取 Integer
        System.out.println(i3 == i4);// false，属于两个对象
    }

    @Test
    public void test2() {
        Integer i1 = new Integer(5);
        Integer i2 = new Integer(5);// 通过 new 来创建的两个 Integer 对象
        Integer i3 = 5;// 调用 valueOf 将 5 自动装箱成 Integer 类型
        int i4 = 5;// 基本数据类型 5
        System.out.println(i1 == i3);     // false，两个引用没有引用同一对象
        System.out.println(i1 == i2);     // false，两个通过 new 创建的 Integer 对象不是同一个引用
        System.out.println(i3 == i4);     // true，i3 调用 java/lang/Integer.intValue:()I 自动拆箱成 int 类型再和 i4 比较
    }

    @Test
    public void test3() {
        System.setProperty("age", "18");
        Integer age = Integer.getInteger("age", 25);
        System.out.println(age);
    }

    @Test
    public void testCompare() {
        Integer a = new Integer(2000);
        Long A = new Long(2000);

        //永远是false
        boolean res1 = A.equals(a);
        Assert.assertFalse(res1);

        //永远是false
        boolean res2 = A.equals(2000);
        Assert.assertFalse(res2);

        boolean res3 = A.equals(2000L);
        Assert.assertTrue(res3);

        BigDecimal price1 = new BigDecimal(0.00d);
        boolean res4 = price1.doubleValue() == 0;
        Assert.assertTrue(res4);

        BigDecimal price2 = new BigDecimal(1.01);
        boolean res5 = price2.doubleValue() == 1.01d;
        Assert.assertTrue(res5);
    }

    @Test
    public void testInstanceOf() {
        Integer a = new Integer(2000);
        Long A = new Long(2000);

        //永远是true
        boolean res1 = A instanceof Long;
        Assert.assertTrue(res1);

        //永远是false
        Object obj = new Object();
        boolean res2 = obj instanceof Long;
        Assert.assertFalse(res2);

        //测试包装
        int int1 = 1;
        Integer it = new Integer(1);
        boolean b = it instanceof Integer;
        it.equals(1);

    }
}
