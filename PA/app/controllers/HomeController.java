package controllers;


import actions.ActionAuthenticator;
import com.avaje.ebean.Ebean;
import models.*;
//import play.api.i18n.Messages;
import play.data.DynamicForm;
import play.mvc.*;
import play.data.Form;
import views.html.*;
import play.i18n.Messages;

import javax.persistence.DiscriminatorValue;

import static play.data.Form.form;


import java.io.File;
import java.util.*;


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

    @Security.Authenticated(ActionAuthenticator.class)
    public Result home(){
        String id = session().get("id");
//        System.out.println("Id is :   "+id);
        Person person = Person.find.where().eq("id", Integer.parseInt(id)).findUnique();

        String type = person.getClass().getAnnotation(DiscriminatorValue.class).value();
//        System.out.println(val.value());
//        ArrayList<ProvidedCourse> courses = person.
        List<ProvidedCourse> courseList = null;
        if(type.equals("Student")){
            Student std = Student.find.byId(Integer.parseInt(id));
            if(std != null) {
                courseList = std.getCourses();
            }
            System.out.println(courseList);
            System.out.println(type);
            return ok(main.render(std, courseList));
        }
        if(type.equals("Teacher")){
            Teacher teacher = Teacher.find.byId(Integer.parseInt(id));
            if(teacher != null) {
                courseList = teacher.getCourses();
            }
            System.out.println(courseList);
            System.out.println(type);
            return ok(main.render(teacher, courseList));
        }
        return null;
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
    public Result courseProfile(int id){
        String userid = session().get("id");
        Person person = Person.find.where().eq("id", Integer.parseInt(userid)).findUnique();
        String type = person.getClass().getAnnotation(DiscriminatorValue.class).value();

        ProvidedCourse pc = ProvidedCourse.find.byId(id);
        return ok(course_profile.render(pc,type));
    }
    public Result setRes(int courseid){
        ProvidedCourse pc = ProvidedCourse.find.byId(courseid);
        String userid = session().get("id");
        Person person = Person.find.where().eq("id", Integer.parseInt(userid)).findUnique();
        String type = person.getClass().getAnnotation(DiscriminatorValue.class).value();
        Syllabes syll = pc.getSyllabes();
        return ok(resources.render(pc, type, syll.getSources()));

    }
    public Result submitRes(int courseid){
        ProvidedCourse pc = ProvidedCourse.find.byId(courseid);
        DynamicForm requestData = form().bindFromRequest();
        Map<String, String> data = requestData.data();
        Source nsource = new Source(data.get("source"), data.get("link"));
        if(pc.getSyllabes() != null) {
            pc.getSyllabes().getSources().add(nsource);
        }
        else{
            Syllabes s = new Syllabes();
            s.getSources().add(nsource);
            pc.setSyllabes(s);
        }
        pc.save();
        return redirect(routes.HomeController.setRes(pc.id));

    }
    public Result duplicate(int id){
        ProvidedCourse pc = ProvidedCourse.find.byId(id);
        List<ProvidedCourse> courses = ProvidedCourse.getPrevCourses(pc.getCourse());
        return ok(duplicate_description.render(courses));
    }
    public Result submitDuplicate(int id){
        ProvidedCourse pc = ProvidedCourse.find.byId(id);
        DynamicForm requestData = form().bindFromRequest();
        Map<String, String> data = requestData.data();
        System.out.println(data);
        String desc = data.get("description");
        Topic topic = new Topic(desc);
        data.remove("description");
        ArrayList<Source> sources = new ArrayList<Source>();

        for(Map.Entry<String, String> item: data.entrySet()){
            System.out.println(item.getKey());
            System.out.println(item.getValue());
            System.out.println("===========================|||||||||||||||||");
            Source nSource = Source.find.byId(Integer.parseInt(item.getKey()));
            sources.add(nSource);
        }
        Syllabes syllabes = new Syllabes();
        syllabes.setTopic(topic);
        syllabes.setSources(sources);
        pc.setSyllabes(syllabes);
        pc.save();
        return ok();
    }


public static class Login{
    public int id;
    public String password;

    public String validate(){
        System.out.println("====================2======================");
        Person person = null;
        person = Person.authenticate(id, password);
        if (person == null){
            return Messages.get("invalid.user.or.password");
        }
        else
            return null;
    }
}
    public Result authenticate() {
        System.out.println("==========================================");
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
//        Form<Register> registerForm = form(Register.class);
        if (loginForm.hasErrors()) {
            return null;//badRequest(index.render(registerForm, loginForm));
        } else {
            session("id", loginForm.get().id + "");
            return redirect(routes.HomeController.home());
        }
    }
    public Result logout(){
        session().clear();
        return ok(login.render(form(Login.class)));
    }

}

