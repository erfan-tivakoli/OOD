package controllers;

import com.avaje.ebean.Ebean;
import models.Manager;
import models.Teacher;
import play.mvc.*;

import views.html.*;

import java.util.Date;
import java.util.Random;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

//        Teacher user = new Teacher((int)(Math.random()*10000),"1", "a", new Date());
//        Ebean.save(user);
        return ok(index.render());

    }

    public Result addPerson() {
//          Map datas = Form.form(Person.class).bindFromRequest().data();
//          Person user = new Person(datas.get("userName").toString(),datas.get("password").toString(),
//                datas.get("name").toString(), datas.get("familyName").toString(), datas.get("emailAddress").toString());

//          Ebean.save(user);

        return redirect(routes.HomeController.index());
    }

    public Result addManager() {
//        Date d = new Date();
//        Manager manager = new Manager(123, "Ghasem!1374", "Ghasem", d);
//        System.out.println(manager.id);
//        System.out.println(manager.inbox.id);
//        System.out.println(manager.birthDate);
//        System.out.println(manager.name);
//        System.out.println(manager.password);
//        Ebean.save(manager);

        return redirect(routes.HomeController.index());
    }

    public Result init(){
        Manager.initializeSemester("/Users/Rfun/Downloads/source.xlsx");
        return redirect(routes.HomeController.index());
    }

}
