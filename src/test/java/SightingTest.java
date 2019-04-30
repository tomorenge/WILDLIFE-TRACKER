import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    Animal testAnimal = new Animal("Leopard");
    testAnimal.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), "zone A", "Ranger Andere");
    assertEquals(true, testSighting instanceof Sighting);
  }

  @Test
  public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
    Animal testAnimal = new Animal("Leopard");
    testAnimal.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), "zone A", "Ranger James");
    Sighting anotherSighting = new Sighting(testAnimal.getId(), "zone A", "Ranger James");
    assertTrue(testSighting.equals(anotherSighting));
  }

  @Test
  public void save_insertsObjectIntoDatabase_Sighting() {
    Animal testAnimal = new Animal("Leopard");
    testAnimal.save();
    Sighting testSighting = new Sighting (testAnimal.getId(), "zone A", "Ranger Andere");
    testSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    Animal testAnimal = new Animal("Leopard");
    testAnimal.save();
    Sighting testSighting = new Sighting (testAnimal.getId(), "zone A", "Ranger Andere");
    testSighting.save();
    Animal secondTestAnimal = new Animal("Panther");
    secondTestAnimal.save();
    Sighting secondTestSighting = new Sighting (testAnimal.getId(), "zone A", "RRanger James");
    secondTestSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
    assertEquals(true, Sighting.all().get(1).equals(secondTestSighting));
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    Animal testAnimal = new Animal("Leopard");
    testAnimal.save();
    Sighting testSighting = new Sighting (testAnimal.getId(), "zone A", "Ranger Andere");
    testSighting.save();
    Animal secondTestAnimal = new Animal("Panther");
    secondTestAnimal.save();
    Sighting secondTestSighting = new Sighting (testAnimal.getId(), "zone A", "RRanger James");
    secondTestSighting.save();
    assertEquals(Sighting.find(secondTestSighting.getId()), secondTestSighting);
  }


}
