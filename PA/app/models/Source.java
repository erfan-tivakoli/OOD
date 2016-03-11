package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.EntityConcurrencyMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Rfun on 3/10/2016 AD.
 */
@Entity
public class Source extends Model {
    @Id
    @GeneratedValue
    public int id;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String source;
    private String link;

}
