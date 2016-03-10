package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@DiscriminatorValue("Student")
public class Student extends Person{

    private ArrayList<ProvidedCourse> currentCourses;
    private ArrayList<ProvidedCourse> allCourses;

    public Student(int id, String password, String name, Date birthDate){

        super(id, password, name, birthDate);
        this.currentCourses = new ArrayList<>();
        this.allCourses = new ArrayList<>();
        System.err.println("*****");
        System.err.println("student constructor" + currentCourses.size());
    }

    public ArrayList<ProvidedCourse> getAllCourses(){
        return allCourses;
    }

    public ArrayList<ProvidedCourse> getcurrentCourses(){
        return currentCourses;
    }

    public void addCurrentCourse(ProvidedCourse providedCourse){

        this.currentCourses.add(providedCourse);
        this.allCourses.add(providedCourse);
    }

//    public static Finder<Integer,Student> find = new Finder<Integer,Student>(
//            Integer.class, Student.class
//    );

//    public static Student authenticate(int id, String password) {
//        return find.where().eq("id", id)
//                .eq("password", password).findUnique();
//    }
}
