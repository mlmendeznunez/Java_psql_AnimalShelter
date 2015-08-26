import java.util.List;
import org.sql2o.*;
import java.util.Arrays;


public class Animal {
  private int id;
  private String animalname;
  private String type;
  private String breed;
  private String gender;
  private String date_admit;
  private int ownerId;

      //property = defines a property, breed is a property of dog
      public String getId() {
        return id;
      }

      public String getAnimalName() {
        return animalname;
      }

      public String getType() {
        return type;
      }

      public String getBreed() {
        return breed;
      }

      public String getGender() {
        return gender;
      }

      public String getDateAdmit(){
        return date_admit;
      }

  public Animal(int id, String name, String type, String breed, String gender, String admit, int ownerid) { //Add breed?
    this.id = id;
    this.animalname = name;
    this.type = type;
    this.breed = breed
    this.gender = gender;
    this.date_admit = admit;
    this.ownerId = ownerid;
  }//end of Animal constructor

    public static List<Animal> all() {
      String sql = "SELECT animal_id, animal_name, gender, date_admit, type, breed, owner_id FROM animals";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Animal.class);
      }
    }

    // @Override
    // public boolean equals(Object otherAnimal) {
    //     if (!(otherAnimal instanceof Animal)){
    //       return false;
    //     } else {
    //       Animal newAnimal = (Animal) otherAnimal;
    //       return this.getDateAdmit().equals(newAnimal.getDateAdmit()) &&
    //              this.getId() == newAnimal.getId();
    //     }
    //   }

    //@Override needs to be used during AnimalTest to override .equals method that is implicit in all objects on Java
    //Override will be used for each member variable, property (  private int id;)
    //You can include multiple variables in the Override equals method

    public void save() {
      try (Connection con= DB.sql2o.open()) {
        // String sql = "INSERT INTO animals (gender, breed, owner_id) VALUES (:gender, :breed, :owner_id)";//rows from database must EACH be input into connection
        // //:gender  = parameter
        // //Sql links the java below to the database in the consul
        String sql = "INSERT INTO animals (animal_id, animal_name, gender, date_admit, type, owner_id)
        VALUES (:animal_id, :animal_name, :gender, :date_admit, :type, :owner_id)"

        this.id = (int) con.createQuery(sql, true)
        .addParameter(":animal_id", this.is)
        .addParameter(":animal_name", this.animalname)//Replaces parameter in database, with animal_name parameter in the java file
        .addParameter(":gender", this.gender)
        .addParameter(":date_admit", this.date_admit)
        .addParameter(":type", this.type)
        .addParameter(":owner_id", this.ownerId)
        .executeUpdate()
        getKey()
      }
    }

    //breed constructor to query multiple rows in your database line
    public static List<Animal> findByBreed(String breed){
      try(Connection con = DB.sql2o.open()){
        String sql = "SELECT * FROM Animals where breed =:breed";
        return con.createQuery(sql).addParameter(":breed", breed).executeAndFetch(Animal.class);
      }
    }

    //type constructor to query multiple rows in your database line
    public static List<Animal> findByType(String type){
      try(Connection con = DB.sql2o.open()){
        String sql = "SELECT * FROM Animals where type =:type";
        return con.createQuery(sql).addParameter(":type", type).executeAndFetch(Animal.class);
      }
    }

    //name constructor to query multiple rows in your database line
    public static List<Animal> findByType(String animalname){ //name or animalname?
      try(Connection con = DB.sql2o.open()){
        String sql = "SELECT * FROM Animals where name =:animal_name";
        return con.createQuery(sql).addParameter(":animal_name", name).executeAndFetch(Animal.class);
      }
    }

    //We need to link Animals to PotentialOwners
    public List<PotentialOwner> getPotentialOwner() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM PotentialOwner where categoryId=:id";
        return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(PotentialOwner.class);
      }
    }

      //  // Animal.findByGender("female")
      //   //constructor to query/access multiple rows in your database line id.
      //   public static List<Animal> findByGender(String gender) {     //psql2o uses just lists... similar to Java lists
      //     //if not finding by Id, but by anything else, use a descriptive name
      //     try(Connection con = DB.sql2o.open()){
      //       String sql = "SELECT * FROM Animals where gender=:gender";
      //       //psql look for all data in Animals table where gender colums is equal to the objects I am passing in the :gender paramater/variable
      //       return con.createQuery(sql).addParameter (":gender", gender).executeAndFetch(Animal.class); // , gender is the Java object from the method :gender is in the database
      //     }
      //   }
        // 
        // //constructor to query/access just one single row in your database line id.
        // public static Animal find(int id) { //find is just by Id
        //   try(Connection con = DB.sql2o.open()){
        //     String sql = "SELECT * FROM Categories where id=:id";
        //     Animal animal = con.createQuery(sql)
        //     .addParameter ("id", id)
        //
        //     .executeAndFetchFirst(Animal.class);
        //     return category;
        //   }
        // }

  }//end of Animal class
