package models;


import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;

import java.util.Date;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "Role")
public abstract class Person extends Model {
    @Id
    private int id;

    @Constraints.Required
    private String password;
    private String name;
    private String birthDate;



    @OneToOne(cascade = CascadeType.ALL)
    public Inbox inbox;


    public Person(int id, String password, String name, String birthDate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.inbox = new Inbox(id);
        //TODO:why we should we call this
        this.inbox.save();
    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public static Person authenticate(int id, String password){
        return find.where().eq("id", id).eq("password",password).findUnique();
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public Inbox getInbox() {
        return inbox;
    }


    public static Finder<Integer,Person> find = new Finder<Integer,Person>(
        Integer.class, Person.class
    );



}

