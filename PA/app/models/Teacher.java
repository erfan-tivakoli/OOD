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
    private List<ProvidedCourse> currentCourses=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProvidedCourse> allCourses=new ArrayList<>();


    public Teacher(int id, String password, String name, Date birthDate) {
        super(id, password, name, birthDate);
    }


    //TODO: to check this course was not present in the list
    public void addCurrentCourse(ProvidedCourse currentCourse) {

        if (!allCourses.contains(currentCourse)){
            this.currentCourses.add(currentCourse);
            this.allCourses.add(currentCourse);
        }
    }




    public List<ProvidedCourse> getCurrentCourses() {
        return currentCourses;
    }

    public List<ProvidedCourse> getAllCourses() {
        return allCourses;
    }

}
