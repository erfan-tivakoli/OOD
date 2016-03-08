package models;


import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;

@Entity
public class Person extends Model {

    @Id
    public int id;

    @Column(unique = true)
    public String userName;

    @Constraints.Required
    public String password;
    public String name;
    public String familyName;
    public String emailAddress;

    @OneToOne(cascade = CascadeType.ALL)
    public Inbox inbox;


    public Person(String userName, String password, String name, String familyName, String emailAddress) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.familyName = familyName;
        this.emailAddress = emailAddress;
        this.inbox = new Inbox(this.emailAddress);

    }
    public static Finder<String,Person> find = new Finder<String,Person>(
            String.class, Person.class
    );

    public static Person authenticate(String userName, String password) {
        return find.where().eq("email", userName)
                .eq("password", password).findUnique();
    }

}

