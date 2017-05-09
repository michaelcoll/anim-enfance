package fr.animenfance.partenaire.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.animenfance.bean.Partenaire;
import fr.animenfance.dao.PartenaireDao;
import fr.animenfance.exception.PartenaireNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class PartenaireServiceTest {

  @Mock private PartenaireDao dao;
  @InjectMocks private PartenaireService service;

  @Test
  public void testGetById() throws PartenaireNotFoundException {
    //given
    Partenaire partenaire = new Partenaire(1, "name", "host");

    //when
    when(dao.getById(1)).thenReturn(partenaire);

    //then
    assertThat(service.getById(1)).isNotNull();
    assertThat(service.getById(1).getName()).isEqualTo("name");
  }
}
