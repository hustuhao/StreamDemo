package stream;

import bo.StudentBO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tuhao
 * @date 2020/11/15 9:54 下午
 * @desc
 */
public class StreamDemo {

    @Test
    public void testMapA() {
        List <Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> collect = list.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void testMapB() {
        StudentBO studentA = new StudentBO();
        StudentBO studentB = new StudentBO();
        StudentBO studentC = new StudentBO();
        StudentBO studentD = new StudentBO();
        studentA.setId(1);
        studentA.setAge(20);
        studentA.setName("mama");
        studentA.setScore(100);

        studentB.setId(2);
        studentB.setAge(21);
        studentB.setName("baba");
        studentB.setScore(60);

        studentC.setId(3);
        studentC.setAge(19);
        studentC.setName("me");
        studentC.setScore(70);

        List<StudentBO> studentBOList = new ArrayList();
        studentBOList.add(studentA);
        studentBOList.add(studentB);
        studentBOList.add(studentC);
        studentBOList.add(studentD);

        List<Integer> reuslt = studentBOList.stream().filter(studentBO -> studentBO.getId() != null).map(studentBO -> {
            Integer res = studentBO.getScore() * studentBO.getScore();
            return res;
        }).collect(Collectors.toList());
        System.out.println(reuslt);
    }

    @Test
    public void testDistinct() {
        List <Integer> list = Arrays.asList(1, 2, 2, 3, 4, 5, 5);
        List<Integer> result = list.stream().distinct().collect(Collectors.toList());
        System.out.println(result);
    }
}
