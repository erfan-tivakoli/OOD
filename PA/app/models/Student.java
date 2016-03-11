package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Student")
public class Student extends Person{


    @ManyToMany(cascade = CascadeType.ALL)
    private List<ProvidedCourse> allCourses=new ArrayList<>();


    public Student(int id, String password, String name, Date birthDate){
        super(id, password, name, birthDate);
    }

    public List<ProvidedCourse> getCourses(){
        return allCourses;
    }


    public void addCourse(ProvidedCourse providedCourse){

        if (!allCourses.contains(providedCourse)){
            this.allCourses.add(providedCourse);
        }
        else{
            System.err.println("This is redundant");
        }
    }

    public static Finder<Integer,Student> find = new Finder<Integer,Student>(
            Integer.class, Student.class
    );



}
