package fr.animenfance.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.animenfance.bean.Partenaire;
import fr.animenfance.exception.PartenaireNotFoundException;
import fr.animenfance.service.PartenaireService;

@RunWith(MockitoJUnitRunner.class)
public class PartenaireControllerTest {

  @Mock private PartenaireService service;
  @InjectMocks private PartenaireController controller;

  @Test
  public void testGetById() throws Exception {
    //given
    Partenaire partenaire = new Partenaire(1, "name");

    //when
    when(service.getById(1)).thenReturn(partenaire);

    //then
    assertThat(controller.getById(1).call().getBody().getName()).isEqualTo("name");
  }

  @Test
  public void testGetById_not_found() throws Exception {
    //when
    when(service.getById(1)).thenThrow(new PartenaireNotFoundException());

    //then
    assertThat(controller.getById(1).call().getStatusCode().value()).isEqualTo(404);
  }
}
