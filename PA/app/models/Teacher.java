package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;


@Entity
public class Teacher extends Person{
    public enum Level{
        Professor
    }

    private Level level = Level.Professor;

    private ArrayList<ProvidedCourse> currentCourses;
    private ArrayList<ProvidedCourse> allCourses;


    public Teacher(int id, String password, String name, Date birthDate){
        super(id, password, name, birthDate);
        currentCourses = new ArrayList<>();
        allCourses = new ArrayList<>();
    }


    //TODO: to check this course was not present in the list
    public void addCurrentCourse(ProvidedCourse currentCourse){
        this.currentCourses.add(currentCourse);
        this.allCourses.add(currentCourse);
    }

    public static Finder<String,Teacher> find = new Finder<String,Teacher>(
            String.class, Teacher.class
    );

    public static Teacher authenticate(int id, String password) {
        return find.where().eq("id", id)
                .eq("password", password).findUnique();
    }


}
