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

    Grade g1 = new Grade(-2,3);
    @OneToMany(cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<Grade>(){{
        add(g1);
    }};


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    private float baggage;
    private String title;

    public Gradable(String title, float baggage, String date){
        this.baggage = baggage;
        this.title = title;
        this.date = date;

    }

    public void setGrades(ArrayList<Grade> grades){
        this.grades = grades;

    }


}
