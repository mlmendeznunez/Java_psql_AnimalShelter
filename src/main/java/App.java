import java.util.*;

import java.io.Console;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    public static void main(String[] args) {
      staticFileLocation("/public");
      String layout = "templates/layout.vtl";

  //homepage
  get("/", (request, response) ->{
    HashMap<String, Object> model = new HashMap<String, Object>();

    List<Animal> animals = Animal.all();//allows us to see all animals on homepage
    model.put("shelteranimals", animals);
    model.put("template", "templates/index.vtl")
  }//end Homepage

  //form to add new animals
  get("/registeranimal", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/newanimal-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/newanimal", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    Animal newAnimal = new Animal(request.queryParams("name"));
    newAnimal.save();


  })

  }//end if main brackets
}//App Object ends
