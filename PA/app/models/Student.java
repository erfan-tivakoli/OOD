package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Student")
public class Student extends Person{

    @ManyToMany(cascade = CascadeType.ALL)
    private List<ProvidedCourse> currentCourses=new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    private List<ProvidedCourse> allCourses=new ArrayList<>();


    public Student(int id, String password, String name, Date birthDate){
        super(id, password, name, birthDate);
    }

    public List<ProvidedCourse> getAllCourses(){
        return allCourses;
    }

    public List<ProvidedCourse> getcurrentCourses(){
        return currentCourses;
    }

    public void addCurrentCourse(ProvidedCourse providedCourse){

        this.currentCourses.add(providedCourse);
        this.allCourses.add(providedCourse);
    }

}
