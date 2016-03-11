package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends Person {
    public enum Level {
        Professor
    }

    private Level level = Level.Professor;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProvidedCourse> allCourses=new ArrayList<>();


    public Teacher(int id, String password, String name, Date birthDate) {
        super(id, password, name, birthDate);
    }


    //TODO: to check this course was not present in the list
    public void addCourse(ProvidedCourse currentCourse) {

        if (!allCourses.contains(currentCourse)){
            this.allCourses.add(currentCourse);
        }
    }



    public static Finder<Integer,Teacher> find = new Finder<Integer,Teacher>(
            Integer.class, Teacher.class
    );

    public List<ProvidedCourse> getCourses() {
        return allCourses;
    }
    public static Teacher authenticate(int id, String password){
        return find.where().eq("id", id).eq("password",password).findUnique();
    }

}
