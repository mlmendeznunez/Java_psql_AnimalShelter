import java.util.ArrayList;
import org.sql2o.*;
import java.util.List;

public class PotentialOwner {
  private int ownerId;
  private String owner_name;
  private String phone;
  private String breedPreference;
  private String typePreference;

  //private int animal_id;

  public PotentialOwner(int id, String name, String phone, String breed, String type) { //variable names do not need to be identical to those in the database
    this.ownerId = id;
    this.owner_name = name;
    this.phone = phone;
    this.breedPreference = breed;
    this.typePreference = type;
  }

  public String getOwner_Name() {
    return owner_name;
  }

  public String getPhone() {
    return phone;
  }

  public String getBreedPreference() {
    return breedPreference;
  }

  public String getTypePreference() {
    return typePreference;
  }

  public static List<PotentialOwner> allOwners() {
    String sql = "SELECT owner_id, owner_name, phone, breed_preference, type_preference FROM owners";//names must be identical to those in the database
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(PotentialOwner.class);
    }
  }
  //ensures that  entries with the same string content and object are equal
  @Override
  public boolean equals(Object otherOwner) {
    if (!(otherPotentialOwner instanceof PotentialOwner)) {
      return false;
    } else {
      PotentialOwner newOwner= (PotentialOwner) otherOwner;
      return this.getOwner_Name().equals(newPotentialOwner.getOwner_Name());
    }
  }

  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO animal_breed (breed_type) VALUES (:breedType)";
  //     this.breedId = (int) con.createQuery(sql,true)
  //       .addParameter("breedType", this.breedType)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }
  //
  // public static PotentialOwner find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM animal_breed WHERE breed_id =:id";
  //     PotentialOwner breedType = con.createQuery(sql)
  //       .addParameter("id",id)
  //       .executeAndFetchFirst(PotentialOwner.class);
  //       return breedType;
  //   }
  // }
  //
  // public List<Animal> getAnimals() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM animal WHERE breed_id = :id";
  //     return con.createQuery(sql)
  //       .addParameter("id", breedId)
  //       .executeAndFetch(Animal.class);
  //   }
  // }

}
