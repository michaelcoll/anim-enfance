package fr.animenfance.user.dao;

import fr.animenfance.bean.user.User;
import fr.animenfance.common.dao.AbstractDaoTest;
import fr.animenfance.user.config.TestUserConfiguration;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {TestUserConfiguration.class})
@FlywayTest(locationsForMigrate = {"sql"})
public class UserDaoTest extends AbstractDaoTest {

  @Autowired
  private UserDao dao;

  @Test
  public void test_create() {
    int insertCount = dao.create(new User(42, "test4"));

    assertThat(insertCount).isEqualTo(1);
  }

  @Test
  public void test_getById() {
    User user = dao.getById(1);

    assertThat(user).isNotNull();
    assertThat(user.getId()).isEqualByComparingTo(1);
    assertThat(user.getName()).isEqualTo("test1");
  }

  @Test
  public void test_list() {
    List<User> users = dao.list();

    assertThat(users).hasSize(3);
  }

  @Test
  public void test_delete() {
    int insertCount = dao.deleteById(1);

    assertThat(insertCount).isEqualTo(1);
  }
}
