package controllers;

import com.avaje.ebean.Ebean;
import models.Person;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import java.util.Map;

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
        return ok(index.render());
    }

    public Result addPerson() {
          Map datas = Form.form(Person.class).bindFromRequest().data();
          Person user = new Person(datas.get("userName").toString(),datas.get("password").toString(),
                datas.get("name").toString(), datas.get("familyName").toString(), datas.get("emailAddress").toString());

          Ebean.save(user);

        return redirect(routes.HomeController.index());
    }

//    public Result addManager() {
//
//    }

}
