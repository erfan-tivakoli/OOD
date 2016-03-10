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

//    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<ProvidedCourse> currentCourses;
//    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<ProvidedCourse> allCourses;


    public Teacher(int id, String password, String name, Date birthDate) {
        super(id, password, name, birthDate);
        this.currentCourses = new ArrayList<>();
        this.allCourses = new ArrayList<>();
    }


    //TODO: to check this course was not present in the list
    public void addCurrentCourse(ProvidedCourse currentCourse) {
        System.err.println(this.currentCourses.size());
        this.currentCourses.add(currentCourse);
        this.allCourses.add(currentCourse);
        System.err.println(this.currentCourses.size());
    }

//    public static Finder<String, Teacher> find = new Finder<String, Teacher>(
//            String.class, Teacher.class
//    );

//    public static Finder<Integer, Teacher> find = new Finder<Integer, Teacher>(
//            Integer.class, Teacher.class
//    );

//    public static Teacher authenticate(int id, String password) {
//        return find.where().eq("id", id)
//                .eq("password", password).findUnique();
//    }


    public ArrayList<ProvidedCourse> getCurrentCourses() {
        return currentCourses;
    }

    public ArrayList<ProvidedCourse> getAllCourses() {
        return allCourses;
    }

}
