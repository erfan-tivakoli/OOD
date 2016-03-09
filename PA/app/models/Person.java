package models;


import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;

import java.util.Date;




@MappedSuperclass
public abstract class Person extends Model {
    @Id
    public int id;

    @Constraints.Required
    public String password;
    public String name;
    public Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    public Inbox inbox;


    public Person(int id, String password, String name, Date birthDate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.inbox = new Inbox(id);

    }


}

