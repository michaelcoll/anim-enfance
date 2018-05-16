package fr.animenfance.partenaire.service;

import fr.animenfance.bean.partenaire.Partenaire;
import fr.animenfance.partenaire.dao.PartenaireDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartenaireServiceTest {

  @Mock private PartenaireDao dao;
  private Scheduler scheduler = Schedulers.fromExecutor(Executors.newSingleThreadExecutor());
  private PartenaireService service;

  @Before
  public void setUp() {
    service = new PartenaireService(dao, scheduler);
  }

  @Test
  public void test_getById() {
    //given
    Partenaire partenaire = new Partenaire(1, "name");

    //when
    when(dao.getById(1)).thenReturn(partenaire);

    //then
    service.getById(1).subscribe(actual -> {
      assertThat(actual).isNotNull();
      assertThat(actual.getId()).isEqualTo(1);
      assertThat(actual.getName()).isEqualTo("name");
    });

  }
}
