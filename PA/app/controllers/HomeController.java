package controllers;

import com.avaje.ebean.Ebean;
import models.Manager;
import models.Person;
import models.Teacher;
import com.sun.java.util.jar.pack.*;
import org.h2.engine.User;
//import play.api.i18n.Messages;
import play.i18n.Messages;
import play.api.mvc.MultipartFormData;
//import play.data.Form.form;
import static play.data.Form.form;
import play.mvc.*;
import play.data.Form;
import views.html.*;

import java.io.File;
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
        return ok(login.render(form(Login.class)));
    }

    public Result addSemesterInfoFile() {
        return ok(add_semester_info.render());
    }
    public Result home(){
        return ok(main.render());
    }
//    public Result addPerson() {
//        Map datas = Form.form(Person.class).bindFromRequest().data();
//        Person user = new Person(datas.get("userName").toString(), datas.get("password").toString(),
//                datas.get("name").toString(), datas.get("familyName").toString(), datas.get("emailAddress").toString());
//        Ebean.save(user);
//        return redirect(routes.HomeController.index());
//    }

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

    public play.mvc.Result init(){
        play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
        play.mvc.Http.MultipartFormData.FilePart filePart = body.getFile("info");
        if (filePart != null) {
            String fileName = filePart.getFilename();
            String contentType = filePart.getContentType();
            java.io.File file = (File) filePart.getFile();
            Manager.initializeSemester(file);
            return redirect(routes.HomeController.index());
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

public static class Login{
    public int id;
    public String password;

    public String validate(){
        System.out.println("====================2======================");

        Person person = null;
        person = Person.authenticate(id, password);
        if (person == null)
            return Messages.get("invalid.user.or.password");

        else
            return null;

    }
}
    public Result authenticate() {
        System.out.println("==========================================");
        Form<Login> loginForm = form(Login.class).bindFromRequest();
//        Form<Register> registerForm = form(Register.class);
        if (loginForm.hasErrors()) {
            return null;//badRequest(index.render(registerForm, loginForm));
        } else {
            session("id", loginForm.get().id + "");
            return home();
        }
    }

}

