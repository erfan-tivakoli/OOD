package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grade extends Model {
    @Id
    @GeneratedValue
    public int id;

    public int studentID;
    public double grade;

    public Grade(int studentID, double grade){
        this.studentID = studentID;
        this.grade = grade;
    }
}
