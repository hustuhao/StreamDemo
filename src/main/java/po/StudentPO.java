package po;

import java.util.Objects;

/**
 * @author tuhao
 * @date 2020/11/15 10:00 下午
 * @desc
 */
public class StudentPO {

    Integer studentId;

    String name;

    Integer age;

    Integer rank;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "StudentPO{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", rank=" + rank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentPO studentPO = (StudentPO) o;
        return Objects.equals(studentId, studentPO.studentId) &&
                Objects.equals(name, studentPO.name) &&
                Objects.equals(age, studentPO.age) &&
                Objects.equals(rank, studentPO.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, age, rank);
    }
}
