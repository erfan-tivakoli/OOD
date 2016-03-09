package models;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Student extends Person{

    private ArrayList<ProvidedCourse> currentCourses;
    private ArrayList<ProvidedCourse> allCourses;

    public Student(int id, String password, String name, Date birthDate){

        super(id, password, name, birthDate);
        currentCourses = new ArrayList<>();
        allCourses = new ArrayList<>();
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

    public static Finder<String,Student> find = new Finder<String,Student>(
            String.class, Student.class
    );

    public static Student authenticate(int id, String password) {
        return find.where().eq("id", id)
                .eq("password", password).findUnique();
    }
}
