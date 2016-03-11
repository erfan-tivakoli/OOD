package models;

import javax.persistence.*;

import com.avaje.ebean.Model;

import java.util.Date;

@Entity
public class Message extends Model{

    @Id
    @GeneratedValue
    private int id;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    Person sender;

    public void setDate(Date date) {
        this.date = date;
    }


    private String body;
    private Date date;

    public Message(String body, Person sender, Date date){
        this.body = body;
        this.sender = sender;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }
}
