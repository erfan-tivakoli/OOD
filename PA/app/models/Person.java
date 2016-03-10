package models;


import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;

import java.util.Date;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends Model {
    @Id
    private int id;

    @Constraints.Required
    private String password;
    private String name;


    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    public Inbox inbox;


    public Person(int id, String password, String name, Date birthDate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.inbox = new Inbox(id);
    }


    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public static Finder<Integer,Person> find = new Finder<Integer,Person>(
        Integer.class, Person.class
    );



}

