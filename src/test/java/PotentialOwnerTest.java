import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class PotentialOwner {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(PotentialOwner.all().size(), 0);
  }
}
