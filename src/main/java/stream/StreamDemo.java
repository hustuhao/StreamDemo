package stream;

import bo.StudentBO;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tuhao
 * @date 2020/11/15 9:54 下午
 * @desc 流的一些实例
 */
public class StreamDemo {

    private List<StudentBO> studentBOList = new ArrayList();

    @Before
    public void initTest() {
        StudentBO studentA = new StudentBO();
        StudentBO studentB = new StudentBO();
        StudentBO studentC = new StudentBO();
        StudentBO studentD = new StudentBO();

        studentA.setId(1);
        studentA.setAge(20);
        studentA.setName("mama");
        studentA.setScore(100);
        studentA.setHeight(175);
        studentA.setWeight(60);

        studentB.setId(2);
        studentB.setAge(19);
        studentB.setName("baba");
        studentB.setScore(60);
        studentB.setHeight(180);
        studentB.setWeight(60);

        studentC.setId(3);
        studentC.setAge(19);
        studentC.setName("me");
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

        //2、Arrays
    }

    @Test
    public void testMapA() {
        List <Integer> list = Arrays.asList(1,2,3,4,5);
        // n = n * n
        List<Integer> resutl = list.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(resutl);
    }

    @Test
    public void testMapB() {

        //筛选id不为空的元素
        List<Integer> reuslt1 = studentBOList.stream().filter(studentBO -> studentBO.getId() != null).map(studentBO -> {
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
        List <Integer> list = Arrays.asList(1, 2, 2, 3, 4, 5, 5);
        List<Integer> result = list.stream().sorted().distinct().collect(Collectors.toList());
        System.out.println(result);

        StudentBO studentE = new StudentBO();
        studentE.setId(3);
        studentE.setAge(19);
        studentE.setName("me");
        studentE.setScore(70);
        studentBOList.add(studentE);
        List<StudentBO> list1 = studentBOList.stream().distinct().collect(Collectors.toList());

    }

    @Test
    public void testSorted() {
        // 年龄排序
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
        StudentBO max
                = studentBOList
                .stream()
                .filter( bo -> bo.getId() != null)
                .max(StudentBO::compare).get();

        StudentBO min
                = studentBOList
                .stream()
                .filter( bo -> bo.getId() != null)
                .min(StudentBO::compare).get();

        StudentBO max2
                = studentBOList
                .stream()
                .filter( bo -> bo.getId() != null)
                .max(Comparator.comparing(StudentBO::getAge)).get();

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

        Map<Integer, Double> result2
                = studentBOList.stream().filter( bo -> bo.getId() != null).collect(Collectors.toMap(StudentBO::getId,StudentBO::getStudentBMI));

        Map<Integer, StudentBO> res = studentBOList.stream().filter( bo -> bo.getId() != null).collect(Collectors.toMap(StudentBO::getId, bo -> {
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
    }

    @Test
    public void testSummingInt() {

        Integer sum1 = studentBOList.stream()
                .filter(bo -> bo.getId() != null)
                .collect(Collectors.summingInt(StudentBO::getScore));
    }


    @Test
    public void testGroupingBy() {

    }


}
