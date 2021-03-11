import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author tuhao
 * @date 2020/11/28 2:48 下午
 * @desc
 */
public class BigDecimalTest {

    @Test
    public void testMultiple() {
        //小数位为42位
        BigDecimal doublePrice1 = new BigDecimal(199.99);
        BigDecimal amount1 = doublePrice1.multiply(BigDecimal.valueOf(100));
        boolean res1 = amount1.equals(19999);
        Assert.assertFalse(res1);

        BigDecimal doublePrice2 = BigDecimal.valueOf(199.99);
        BigDecimal intPrice2 = BigDecimal.valueOf(19999);
        BigDecimal amount2 = doublePrice2.multiply(BigDecimal.valueOf(100));
        //int.class
        boolean res2 = amount2.equals(19999);
        Assert.assertFalse(res2);

        //scale:小数位不一致
        boolean res3 = amount2.equals(intPrice2);
        Assert.assertFalse(res3);

        BigDecimal doublePrice3 = BigDecimal.valueOf(0.00);
        BigDecimal doublePrice4 = new BigDecimal(0.00);
        int res4 = doublePrice3.compareTo(doublePrice4);
        Assert.assertEquals(0,res4);
        //2位小数
        BigDecimal doublePrice5 = BigDecimal.valueOf(1.01);
        // 42位小数
        BigDecimal doublePrice6 = new BigDecimal(1.01);
        int res5 = doublePrice5.compareTo(doublePrice6);
        Assert.assertEquals(-1,res5);

    }
}
