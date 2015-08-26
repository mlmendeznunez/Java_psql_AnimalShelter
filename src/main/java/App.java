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
  //Animals listed by Type for Customers
  get("/", (request, response) ->{
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("shelteranimalstype", Animal.allByType());
    model.put("template", "templates/index.vtl")
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine()); //end Homepage

  //Animals Listed by Breeds for Customers
  get("/breeds", (request, response) ->{
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("shelteranimalsbreed", Animal.allByBreed());
    model.put("template", "templates/listbybreeds.vtl")
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine()); //end Breeds

  //Animals Listed by Names for Customers
  get("/names", (request, response) ->{
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("shelteranimalsname", Animal.allByName());
    model.put("template", "templates/listbynames.vtl")
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine()); //end Names

  //Employee portal
  get("/shelter", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/employeeportal.vtl");

    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine()); //end portal

  //Employee portal, Register animal form
  get("/registeranimal", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/newanimal-form.vtl");

    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine()); //end register animal form

  post("/shelter/customerrecommendations", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    //.queryParams(" ") comes from newanimal-form.vtl template

    Animal newAnimal = new Animal(0,
    request.queryParams("name"),
    request.queryParams("type"),
    request.queryParams("breed"),
    request.queryParams("gender"),
    request.queryParams("admitdate"),
    0);
    newAnimal.save(); //.save() means that it is a function/method with no arguments
    //public Animal(int id, String name, String type, String breed, String gender, String admit, int ownerid) { //Add breed?
    model.put("animal", newAnimal);
    model.put("template", "templates/customerrecommendations.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine()); //end register animal form

  // post("/newanimal", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //
  //   Animal newAnimal = new Animal(request.queryParams("name"));
  //   newAnimal.save();
  //
  //   model.put("template", "templates/new-form.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());

  }//end if main brackets
}//App Object ends
