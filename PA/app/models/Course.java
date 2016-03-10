package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course extends Model {
    @Id
    public int courseNo;


    public String title;
    public int credits;

    public Course(int courseNo , String title, int credits){
        this.title = title;
        this.courseNo = courseNo;
        this.credits = credits;
    }

    public static Finder<Integer,Course> find = new Finder<Integer, Course>(
            Integer.class, Course.class
    );

}
