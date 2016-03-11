package models;

import com.avaje.ebean.Model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Topic extends Model {
    @Id
    @GeneratedValue
    public int id;

    public void setDescription(String description) {
        this.description = description;
    }

    private String description = "sample description " + id;

    public Topic(String description){
        this.description = description;
    }

    public Topic(){

    }

    public String getDescription(){
        return description;
    }
}
