package fr.animenfance.common.service;

import com.google.common.collect.ImmutableList;
import fr.animenfance.common.dao.CRUDDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AbstractCRUDServiceTest {

  @Mock private TestDao dao;
  @Captor private ArgumentCaptor<TestBean> beanArgumentCaptor;
  @Captor private ArgumentCaptor<Integer> idArgumentCaptor;
  private Scheduler scheduler = Schedulers.fromExecutor(Executors.newSingleThreadExecutor());
  private TestService service;

  @Before
  public void setUp() {
    service = new TestService(dao, scheduler);
  }

  @Test
  public void test_getById() {
    //given
    TestBean bean = new TestBean(1, "name");

    //when
    when(dao.getById(1)).thenReturn(bean);
    TestBean actual = service.getById(1).block();

    //then
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isEqualTo(1);
    assertThat(actual.getName()).isEqualTo("name");
  }

  @Test
  public void test_list() {
    //given
    TestBean bean1 = new TestBean(1, "name");
    TestBean bean2 = new TestBean(2, "name2");

    //when
    when(dao.list()).thenReturn(ImmutableList.of(bean1, bean2));

    //then
    TestBean actual = service.list().blockFirst();
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isEqualTo(1);
    assertThat(actual.getName()).isEqualTo("name");

    actual = service.list().blockLast();
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isEqualTo(2);
    assertThat(actual.getName()).isEqualTo("name2");
  }

  @Test
  public void test_create() {
    //given
    TestBean bean = new TestBean(1, "name");

    //when
    when(dao.create(beanArgumentCaptor.capture())).thenReturn(1);
    Integer actual = service.create(bean).block();

    //then
    assertThat(actual).isNotNull().isEqualTo(1);

    assertThat(beanArgumentCaptor.getValue().getId()).isEqualTo(1);
    assertThat(beanArgumentCaptor.getValue().getName()).isEqualTo("name");
  }

  @Test
  public void test_deleteById() {
    //when
    when(dao.deleteById(idArgumentCaptor.capture())).thenReturn(1);
    Integer actual = service.deleteById(1).block();

    //then
    assertThat(actual).isNotNull().isEqualTo(1);

    assertThat(idArgumentCaptor.getValue()).isEqualTo(1);
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class TestBean {
    private int id;
    private String name;
  }

  private interface TestDao extends CRUDDao<TestBean, Integer> {
  }

  private static class TestService extends AbstractCRUDService<TestBean, Integer> {
    TestService(TestDao dao, Scheduler scheduler) {
      super(dao, scheduler);
    }
  }
}
