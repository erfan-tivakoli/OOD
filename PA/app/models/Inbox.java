package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Inbox extends Model {

    @Id
    public int id;

    @Column(unique = true)
    public String emailAddress;

    @OneToMany(cascade = CascadeType.ALL)
    ArrayList<Message> messages;

    public Inbox(String emailAddress){
        this.emailAddress = emailAddress;
        messages = new ArrayList<>();
    }


    public void addToInbox(Message m){
        messages.add(m);
    }
    public ArrayList<Message> getMessages(){
        return messages;
    }
}
