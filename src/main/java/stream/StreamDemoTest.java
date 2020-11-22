package stream;

import bo.StudentBO;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author tuhao
 * @date 2020/11/15 9:54 下午
 * @desc 流的一些实例
 */
public class StreamDemoTest {

    private List<StudentBO> studentBOList = new ArrayList();

    @Before
    public void initTest() {
        StudentBO studentA = new StudentBO();
        StudentBO studentB = new StudentBO();
        StudentBO studentC = new StudentBO();
        StudentBO studentD = new StudentBO();

        studentA.setId(1);
        studentA.setAge(20);
        studentA.setName("Tom");
        studentA.setClassId(1);
        studentA.setScore(100);
        studentA.setHeight(175);
        studentA.setWeight(60);

        studentB.setId(2);
        studentB.setAge(19);
        studentB.setName("Ken");
        studentB.setClassId(2);
        studentB.setScore(60);
        studentB.setHeight(180);
        studentB.setWeight(60);

        studentC.setId(3);
        studentC.setAge(19);
        studentC.setName("Bob");
        studentC.setClassId(2);
        studentC.setScore(70);
        studentC.setHeight(175);
        studentC.setWeight(80);

        studentBOList.add(studentA);
        studentBOList.add(studentB);
        studentBOList.add(studentC);
        studentBOList.add(studentD);
    }

    @Test
    public void createStream() {
        //1、Collection 接口

        //2、Arrays 的stream方法
        int[] intArray = {1,2,3,4,5};
        IntStream stream = Arrays.stream(intArray);
    }

    @Test
    public void testMapA() {
        /**
         * 求平方
         */
        List <Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> result1 = list.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(result1);

        /**
         * 筛选id不为空的学生id
         */
        List<Integer> reuslt2 = studentBOList.stream()
                .filter(studentBO -> studentBO.getId() != null)
                .map(studentBO -> {
                    Integer res = studentBO.getScore() * studentBO.getScore();
                    return res;
                }).collect(Collectors.toList());
        System.out.println(reuslt2);
    }

    @Test
    public void testFilter() {
        //筛选id不为空的元素
        List<Integer> reuslt1 = studentBOList.stream()
                .filter(studentBO -> studentBO.getId() != null)
                .map(studentBO -> {
            Integer res = studentBO.getScore() * studentBO.getScore();
            return res;
        }).collect(Collectors.toList());
        System.out.println(reuslt1);

        //筛选score的平方大于 700的学生，并返回 score的平方
        List<Integer> reuslt2 = studentBOList.stream().filter(studentBO -> {
            if (studentBO.getId() == null) {
                return false;
            }
            Integer score2 = studentBO.getScore() * studentBO.getScore();
            return score2 > 7000;
        }).map(studentBO -> {
            Integer res = studentBO.getScore() * studentBO.getScore();
            return res;
        }).collect(Collectors.toList());
        System.out.println(reuslt2);

        //筛选年龄大于18岁的学生集合
        List<StudentBO> sudentList
                = studentBOList.stream().filter(bo -> bo.getAge() > 18).collect(Collectors.toList());
    }

    @Test
    public void testDistinct() {
        /**
         * 对数字去重
         */
        List <Integer> list = Arrays.asList(1, 2, 2, 3, 4, 5, 5);
        List<Integer> result = list.stream().sorted().distinct().collect(Collectors.toList());
        System.out.println(result);

        /**
         * 对学生集合去重
         */
        StudentBO studentE = new StudentBO();
        studentE.setId(3);
        studentE.setAge(19);
        studentE.setName("Bob");
        studentE.setClassId(2);
        studentE.setScore(70);
        studentBOList.add(studentE);
        List<StudentBO> list1 = studentBOList.stream().distinct().collect(Collectors.toList());
    }

    @Test
    public void testSorted() {
        // 自然排序
        List <Integer> list = Arrays.asList(1, 2, 5, 4, 3);
        List<Integer> integers = list.stream().sorted().collect(Collectors.toList());

        // 按年龄排序
        List<StudentBO> result = studentBOList.stream()
                .filter(studentBO -> studentBO.getId() != null)
                .sorted((s1, s2) -> {
                    return s1.getAge().compareTo(s2.getAge());
                }).collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void testSkip() {
        List <Integer> list = Arrays.asList(1, 2, 2, 3, 4, 5, 5);
        List<Integer> result = list.stream().skip(5).collect(Collectors.toList());
        // [5, 5]
        System.out.println(result);
    }

    @Test
    public void testLimit() {
        List <Integer> list = Arrays.asList(1, 2, 2, 3, 4, 5, 5);
        List<Integer> result = list.stream().limit(5).collect(Collectors.toList());
        // [1, 2, 2, 3, 4]
        System.out.println(result);
    }

    /********************终端操作********************/
    @Test
    public void testMaxMIn() {
        /**
         * 获取id最大的学生
         */
        StudentBO max
                = studentBOList
                .stream()
                .filter( bo -> bo.getId() != null)
                .max(StudentBO::compare).get();
        /**
         * 获取id最小的学生
         */
        StudentBO min
                = studentBOList
                .stream()
                .filter( bo -> bo.getId() != null)
                .min(StudentBO::compare).get();

        /**
         * 获取年龄最大的学生
         */
        StudentBO max2
                = studentBOList
                .stream()
                .filter( bo -> bo.getId() != null)
                .max(Comparator.comparing(StudentBO::getAge)).get();

        /**
         * for 循环实现：获取分数最大的学生
         */
        boolean seen = false;
        StudentBO best = null;
        for (StudentBO bo : studentBOList) {
            if (bo.getId() != null) {
                if (!seen || bo.getScore().compareTo(best.getScore()) > 0) {
                    seen = true;
                    best = bo;
                }
            }
        }
        StudentBO max3
                = (seen ? Optional.of(best) : Optional.<StudentBO>empty()).get();

        /**
         * stream 实现获取分数最大的学生
         */
        StudentBO max4
                = studentBOList
                .stream()
                .filter( bo -> bo.getId() != null)
                .max((s1, s2) -> {return s1.getScore().compareTo(s2.getScore());}).get();

    }

    @Test
    public void testCount() {
        //parallelStream 是流并行处理程序的代替方法。
        List<Integer> integers = Arrays.asList(1, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        //统计1的个数
        long count1 = integers.parallelStream().filter(i -> {return i.equals(1);}).count();

        //统计其平方大于16的个数
        long count2 = integers.parallelStream().filter( i -> {return i * i > 16;}).count();
    }

    @Test
    public void testReduce() {
        List<Integer> integers = Arrays.asList(1, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        //求和的两种方式
        long sum1 = integers.stream().reduce(0, (a,b)->(a+b));
        long sum2 = integers.stream().reduce(0, Integer::sum);

    }

    @Test
    public void testCollectors() {
        /**
         * 返回包含学生id和其BMI系数的map
         */
        Map<Integer, Double> result1
                = studentBOList.stream()
                .filter( bo -> bo.getId() != null)
                .collect(Collectors.toMap(StudentBO::getId,StudentBO::getStudentBMI));

        /**
         * 返回包含学生id和其对象的map
         */
        Map<Integer, StudentBO> result2 = studentBOList.stream()
                .filter( bo -> bo.getId() != null)
                .collect(Collectors.toMap(StudentBO::getId, bo -> {
            return bo;
        }));

        /**
         * 根据某个属性进行分类
         */
        Map<Integer, List<StudentBO>> result3 = studentBOList.stream()
                .filter(bo -> bo.getId() != null)
                .collect(Collectors.groupingBy(StudentBO::getScore));

        /**
         * 根据某个条件进行区分，
         * 条件为true or false
         */
        Map<Boolean, List<StudentBO>> result4 = studentBOList.stream()
                .filter(bo -> bo.getId() != null)
                .collect(Collectors.partitioningBy(bo -> {
                    return bo.getScore() > 60;
                }));

        //计算所有学生的分数之和
        Integer sum1 = studentBOList.stream()
                .filter(bo -> bo.getId() != null)
                .collect(Collectors.summingInt(StudentBO::getScore));

        /**
         * 将学生按照班级分类
         */
        Map<Integer, List<StudentBO>> classStudentMap = studentBOList.stream()
                .filter(bo -> bo.getId() != null )
                .collect(Collectors
                        .groupingBy(
                                StudentBO::getClassId,
                                TreeMap::new,
                                Collectors.toList()
                        )
                );
        //{1=[StudentBO{id=1, name='Tom', age=20, score=100}], 2=[StudentBO{id=2, name='Ken', age=19, score=60}, StudentBO{id=3, name='Bob', age=19, score=70}]}
        System.out.println(classStudentMap);
    }

    @Test
    public void testForeach() {
        List<Integer> integers = Arrays.asList(1, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        //打印所有的元素
        integers.stream().forEach(System.out::print);
    }



}
