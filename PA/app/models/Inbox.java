package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.EntityConcurrencyMode;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Inbox extends Model {

    @Id
    public int id;

    ArrayList<Message> messages;

    public Inbox(int id){

        this.messages = new ArrayList<>();
        this.id = id;

    }


    public void addToInbox(Message m){
        messages.add(m);
    }
    public ArrayList<Message> getMessages(){
        return messages;
    }
}
