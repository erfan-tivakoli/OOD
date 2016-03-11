package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Gradable extends Model {
    @Id
    @GeneratedValue
    public int id;


    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<Grade>();


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    private float baggage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public Gradable(String title, float baggage, String date){
        this.baggage = baggage;
        this.title = title;
        this.date = date;

    }

    public void setGrades(ArrayList<Grade> grades){
        this.grades = grades;

    }
    public static Finder<Integer,Gradable> find = new Finder<Integer,Gradable>(
            Integer.class, Gradable.class
    );



}
