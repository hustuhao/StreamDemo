package collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author tuhao
 * @date 2020/12/23 10:35 上午
 * @desc 集合类
 */
public class CollectionsTest {


    @Test
    public void testCollectionsList() {
        // 返回空集合
        List<Object> objects = Collections.emptyList();
        objects = Collections.EMPTY_LIST;

        // 把单个元素变成集合:会修改操作的集合
        List<Integer> integers = Collections.singletonList(1);
        Assert.assertEquals(integers.size(), 1);

        try {
            Collections.addAll(integers, 2, 3, 4, 5);
            Assert.assertEquals(integers.size(), 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        // 统一填充 list
        Collections.fill(list,0);

        // 不可变集合：修改会抛出异常：RuntimeException
        Collection<Integer> unmodifiableIntegers = Collections.unmodifiableCollection(integers);
        try {
            unmodifiableIntegers.add(6);
        } catch (Exception e) {
            System.out.println("-------------unmodifiableCollection----------------");
            e.printStackTrace();
        }

        //线程安全的list
        Collections.synchronizedList(integers);
    }

    /**
     * 求交集
     */
    @Test
    public void testCommonObject() {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);

        ArrayList<List<Integer>> all = new ArrayList<>();
        all.add(list1);
        all.add(list2);

        List<Integer> result = all.stream().reduce((l1, l2) -> {
            l1.retainAll(list2);
            return l1;
        }).orElse(Collections.emptyList());
        Assert.assertEquals(result.size(), 1);
    }

}
