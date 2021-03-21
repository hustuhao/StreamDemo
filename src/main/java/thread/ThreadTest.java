package thread;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tuhao
 * @date 2021/3/14 8:33 下午
 * @desc
 */
public class ThreadTest {


    private ThreadLocal<Integer> userId = new ThreadLocal<>();

    private ThreadLocal<Integer> pwd = new ThreadLocal<>();

    @Test
    public void test() {
        userId.set(1);
        System.out.println("main-userId:"+userId);
        pwd.set(2);
        System.out.println("main-pwd:"+pwd);

        System.out.println("---------------");
        Thread thread0 = new Thread(() -> {
            Assert.assertNull(userId.get());
            Assert.assertNull(pwd.get());
            userId.set(100);
            pwd.set(200);
            Integer curUserId = userId.get();
            Integer curPwd = pwd.get();
            System.out.println("thread1-userId:"+userId);
        });
        thread0.start();

        Thread thread1 = new Thread(() -> {
            Assert.assertNull(userId.get());
            Assert.assertNull(pwd.get());
            pwd.set(200);
            userId.set(100);
            Integer curPwd = pwd.get();
            Integer curUserId = userId.get();
            System.out.println("thread2-userId:"+userId);
        });
        thread1.start();
    }
}
