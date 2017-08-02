package com.kaishengit.hibernate;

import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.Teacher;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ManyToManyTest extends BaseTest {

    @Test
    public void save() {
        Student student = new Student();
        student.setName("张学生");
        Student student1 = new Student();
        student1.setName("李学生");
        Teacher teacher = new Teacher();
        teacher.setName("张老师");
        Teacher teacher1 = new Teacher();
        teacher1.setName("李老师");
        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.add(teacher);
        teachers.add(teacher1);
        Set<Student> students = new HashSet<Student>();
        students.add(student);
        students.add(student1);
        student.setTeacherSet(teachers);
        student1.setTeacherSet(teachers);
        teacher.setStudentSet(students);
        teacher1.setStudentSet(students);
        session.save(teacher);
        session.save(teacher1);
        session.save(student);
        session.save(student1);
    }

}
