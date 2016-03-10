package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.EntityConcurrencyMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Inbox extends Model {

    @Id
    public int id;

//    @OneToMany(mappedBy = "inbox", cascade = CascadeType.ALL)
    private ArrayList<Message> messages;

    public Inbox(int id){

        this.messages = new ArrayList<>();
        this.id = id;
        Message welcome = new Message("Dear "+this.id+"! Welcome from Kuchksara  and Rfun!", null, new Date());
        Ebean.save(welcome);
        this.messages.add(welcome);
        System.err.println(this.messages.get(0).getContent());

    }


    public void addToInbox(Message m){
        messages.add(m);
    }
    public ArrayList<Message> getMessages(){
        return messages;
    }
}
