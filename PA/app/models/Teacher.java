package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;


@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends Person {
    public enum Level {
        Professor
    }

    private Level level = Level.Professor;

    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<ProvidedCourse> currentCourses=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<ProvidedCourse> allCourses=new ArrayList<>();


    public Teacher(int id, String password, String name, Date birthDate) {
        super(id, password, name, birthDate);
    }


    //TODO: to check this course was not present in the list
    public void addCurrentCourse(ProvidedCourse currentCourse) {
        System.err.println(this.currentCourses.size());
        this.currentCourses.add(currentCourse);
        this.allCourses.add(currentCourse);
        System.err.println(this.currentCourses.size());
    }



    public ArrayList<ProvidedCourse> getCurrentCourses() {
        return currentCourses;
    }

    public ArrayList<ProvidedCourse> getAllCourses() {
        return allCourses;
    }

}
