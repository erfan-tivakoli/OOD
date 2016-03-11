package models;

import javax.persistence.*;

import com.avaje.ebean.Model;

import java.util.Date;

@Entity
public class Message extends Model{

    @Id
    @GeneratedValue
    private int id;

    private String body;
    private Person sender;
    private Date date;

    public Message(String body, Person sender, Date date){
        this.body = body;
        this.sender = sender;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Person getSender() {
        return sender;
    }

    public Date getDate() {
        return date;
    }
}
