package controllers;

import com.avaje.ebean.Ebean;
import models.Manager;
import models.Teacher;
import com.sun.java.util.jar.pack.*;
import play.api.mvc.MultipartFormData;
import play.data.Form;
import play.mvc.*;

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
        return ok(login.render());
    }

    public Result loginSubmit() {

        return ok(login.render());
    }

    public Result addSemesterInfoFile() {
        return ok(add_semester_info.render());
    }
    public Result home(){
        return ok(main.render());
    }
    public play.mvc.Result getSemesterFile() {
        play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
        play.mvc.Http.MultipartFormData.FilePart filePart = body.getFile("info");
        if (filePart != null) {
            String fileName = filePart.getFilename();
            String contentType = filePart.getContentType();
            java.io.File file = (File) filePart.getFile();
            return ok("File uploaded");
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
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

    public Result init(){
        Manager.initializeSemester("/Users/Rfun/Downloads/source.xlsx");
        return redirect(routes.HomeController.index());
    }

}
