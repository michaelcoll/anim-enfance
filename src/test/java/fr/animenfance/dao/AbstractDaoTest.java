package fr.animenfance.dao;

import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import fr.animenfance.config.TestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
  classes = {MybatisAutoConfiguration.class, TestConfiguration.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
  FlywayTestExecutionListener.class })
@TestPropertySource("/test.properties")
class AbstractDaoTest {

}
