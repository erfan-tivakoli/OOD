package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class ProvidedCourse extends Model {
    @Id
    public int id;

    private String time;
    private Date finalExamTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    private String semester;
    private int groupID;
    private String room;

    //TODO: change the format of time to a better class, and also semester
    public ProvidedCourse(int id, int courseNo, String title, String time, Date finalExamTime, int teacherID, String semester,
                          int groupID, String room, int credits)
    {

        Course course = Course.find.where().eq("courseNo", courseNo).findUnique();
        //If we don't have this course already we will create it
        if (course == null) {
            course = new Course(courseNo, title, credits);
            this.course = course;
            Ebean.save(course);
        }
        this.course = course;

        Teacher teacher =  Teacher.find.where().eq("id", teacherID).findUnique();

        if (teacher == null) {
            System.err.println("There is no teacher with id number " + teacherID);
        }
        else{
            this.teacher=teacher;
        }
        this.time = time;
        this.finalExamTime = finalExamTime;
        this.semester = semester;
        this.groupID = groupID;
        this.room = room;
        //TODO:change this mess to auto generate id
        this.id = id;

    }

    public static Finder<String,ProvidedCourse> find = new Finder<String,ProvidedCourse>(
            String.class, ProvidedCourse.class
    );

    public Course getCourse(){
        return this.course;
    }
}
