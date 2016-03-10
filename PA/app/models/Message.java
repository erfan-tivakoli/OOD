package models;

import javax.persistence.*;

import com.avaje.ebean.Model;

import java.util.Date;

@Entity
public class Message extends Model{

    @Id
    @GeneratedValue
    public int id;

//    @ManyToOne
//    public Inbox inbox;

    String body;
    Person sender;
    Date date;

    public Message(String body, Person sender, Date date){
        this.body = body;
        this.sender = sender;
        this.date = date;
    }

    public String getContent(){
        return this.body;
    }
}
