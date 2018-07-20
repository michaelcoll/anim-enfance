package fr.animenfance.bean.partenaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {

  @NotBlank
  private String name;
  private String description;
  private Integer duration;
  private List<ShowDate> dates;

}
