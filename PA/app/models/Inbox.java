package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.EntityConcurrencyMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Inbox extends Model {

    @Id
    public int id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messages=new ArrayList<>();

    public Inbox(int id){

        this.id = id;

    }


    public void addToInbox(Message m){
        messages.add(m);
    }
    public List<Message> getMessages(){
        return messages;
    }
}
