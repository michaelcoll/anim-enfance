package fr.animenfance.creche.dao;

import fr.animenfance.bean.creche.Creche;
import fr.animenfance.common.dao.AbstractDaoTest;
import fr.animenfance.creche.config.TestCrecheConfiguration;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {TestCrecheConfiguration.class})
@FlywayTest(locationsForMigrate = {"sql"})
public class CrecheDaoTest extends AbstractDaoTest {

  @Autowired
  private CrecheDao dao;

  @Test
  public void test_create() {
    int insertCount = dao.create(new Creche(42, "test4"));

    assertThat(insertCount).isEqualTo(1);
  }

  @Test
  public void test_getById() {
    Creche creche = dao.getById(1);

    assertThat(creche).isNotNull();
    assertThat(creche.getId()).isEqualByComparingTo(1);
    assertThat(creche.getName()).isEqualTo("test1");
  }

  @Test
  public void test_list() {
    List<Creche> creches = dao.list();

    assertThat(creches).hasSize(3);
  }

  @Test
  public void test_delete() {
    int insertCount = dao.deleteById(1);

    assertThat(insertCount).isEqualTo(1);
  }
}
