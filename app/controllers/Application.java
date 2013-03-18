package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.*;
import models.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render(Project.find.all(), Task.find.all()));
    }

}
