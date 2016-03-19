package fr.animenfance.service;

import fr.animenfance.bean.Partenaire;
import fr.animenfance.dao.PartenaireDao;
import fr.animenfance.exception.PartenaireNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartenaireServiceTest {

  @Mock private PartenaireDao dao;
  @InjectMocks private PartenaireService service;

  @Test
  public void testGetById() throws PartenaireNotFoundException {
    //given
    Partenaire partenaire = new Partenaire(1L, "name");

    //when
    when(dao.getById(1L)).thenReturn(partenaire);

    //then
    assertThat(service.getById(1L)).isNotNull();
    assertThat(service.getById(1L).getName()).isEqualTo("name");
  }
}
