package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    private Syllabes syllabes = new Syllabes();


    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    private String semester;

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    private int groupID;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    private String room;

    //TODO: change the format of time to a better class, and also semester
    //TODO: to check wether it's better to add the teacher here or not
    public ProvidedCourse(int id, int courseNo, String title, String time, Date finalExamTime, int teacherID, String semester,
                          int groupID, String room, int credits)
    {

        Course course = Course.find.byId(courseNo);
        //If we don't have this course already we will create it
        if (course == null) {
            course = new Course(courseNo, title, credits);
            this.course = course;
            Ebean.save(course);
        }
        this.course = course;

        Teacher teacher =  (Teacher)Person.find.byId(teacherID);

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
        this.id = id;

    }

    public static Finder<Integer,ProvidedCourse> find = new Finder<Integer,ProvidedCourse>(
            Integer.class, ProvidedCourse.class
    );

    public Course getCourse(){
        return this.course;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void addStudent(Student student) {
        if (!(this.students.contains(student))) {
            this.students.add(student);
        }
    }


    public Syllabes getSyllabes(){
        return syllabes;
    }
    public void setSyllabes(Syllabes syllabes){
        this.syllabes = syllabes;
    }
    public Teacher getTeacher(){
        return this.teacher;
    }
    public static List<ProvidedCourse> getPrevCourses(Course course){
        List<ProvidedCourse> courses = ProvidedCourse.find.where().eq("course", course).findList();
        return courses;
    }
}
