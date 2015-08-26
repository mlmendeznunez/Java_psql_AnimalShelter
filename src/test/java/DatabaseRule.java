import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteOwnersQuery = "DELETE FROM owners *;";
      String deleteAnimalsQuery = "DELETE FROM animals *;";
      String deleteBreedsQuery = "DELETE FROM animal_breeds *;";
      con.createQuery(deleteOwnersQuery).executeUpdate();
      con.createQuery(deleteAnimalsQuery).executeUpdate();
      con.createQuery(deleteBreedsQuery).executeUpdate();

    }
  }
}
