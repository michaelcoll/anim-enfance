package fr.animenfance.partenaire.dao;

import fr.animenfance.bean.partenaire.Partenaire;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@FlywayTest(locationsForMigrate = {"sql"})
public class PartenaireDaoTest extends AbstractDaoTest {

  @Autowired
  private PartenaireDao dao;

  @Test
  public void test_create() {
    int insertCount = dao.create(new Partenaire(42, "test4"));

    assertThat(insertCount).isEqualTo(1);
  }

  @Test
  public void test_getById() {
    Partenaire partenaire = dao.getById(1);

    assertThat(partenaire).isNotNull();
    assertThat(partenaire.getId()).isEqualByComparingTo(1);
    assertThat(partenaire.getName()).isEqualTo("test1");
  }

  @Test
  public void test_list() {
    List<Partenaire> partenaires = dao.list();

    assertThat(partenaires).hasSize(3);
  }

  @Test
  public void test_delete() {
    int insertCount = dao.deleteById(1);

    assertThat(insertCount).isEqualTo(1);
  }
}
