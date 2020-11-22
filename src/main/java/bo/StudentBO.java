package bo;

import java.util.Objects;

/**
 * @author tuhao
 * @date 2020/11/15 9:56 下午
 * @desc
 */
public class StudentBO {

    Integer id;

    String name;

    Integer age;

    Integer score;

    /**身高 cm **/
    Integer height;

    /** 体重 kg**/
    Integer weight;
    /**
     * 获取学生的身体质量指数
     * @return
     */
    public Double getStudentBMI() {
        return (weight * 10000.0 / (height * height));
    }

    public static int compare(StudentBO s1, StudentBO s2) {
        return (s1.getId().compareTo(s2.id));
    }
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }







    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentBO studentBO = (StudentBO) o;
        return Objects.equals(id, studentBO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, score);
    }
}
