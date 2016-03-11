package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Syllabes extends Model {
    @Id
    @GeneratedValue
    public int id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Source> sources = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Topic topic = new Topic("sdfsadfjasdkf ad ");
    //test
//    private Source s1 = new Source();
//    private Source s2 = new Source();
//    private Source s3 = new Source();
//
//


    public String getDescription(){
        return this.topic.getDescription();
    }

    public List<Source> getSources() {
//        ArrayList<Source> temp = new ArrayList<Source>();
//        temp.add(s1);
//        temp.add(s2);
//        temp.add(s3);
        return sources;
//        return temp;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public Topic getTopic() {
        return topic;
    }


    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
