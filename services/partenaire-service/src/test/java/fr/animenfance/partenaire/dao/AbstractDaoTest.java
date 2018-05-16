package fr.animenfance.partenaire.dao;

import fr.animenfance.partenaire.config.TestConfiguration;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
  classes = {MybatisAutoConfiguration.class, TestConfiguration.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
  FlywayTestExecutionListener.class })
class AbstractDaoTest {

}
