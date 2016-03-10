/**
 * Created by makan on 3/10/16.
 */

package actions;
import models.Person;
import play.libs.F;
import play.mvc.*;

public class ActionAuthenticator extends Security.Authenticator {
    @Override
    public String getUsername(Http.Context ctx) {
        String id = ctx.session().get("id");
        if (id != null) {
            Person person = Person.find.where().eq("id", Integer.parseInt(id)).findUnique();
            if (person != null) {
                return person.getId()+"";
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return super.onUnauthorized(context);
    }
}
//public class SecuredAction extends Action.Simple {
//
//
//
//    public Result call(Http.Context ctx){
//
////        System.out.println("context is ::::: "+ctx.session());
////        return null;
//        String id = ctx.session().get("id");
//        if (id != null) {
//            Person person = Person.find.where().eq("id", Integer.parseInt(id)).findUnique();
//            if (person != null) {
//                ctx.request().setUsername(person.getId()+"");
//                Result authorized = Results.unauthorized("authorized");
//                return delegate.call(ctx);
//            }
//        }
//        Result unauthorized = Results.unauthorized("unauthorized");
//        return unauthorized;
//    }
//}