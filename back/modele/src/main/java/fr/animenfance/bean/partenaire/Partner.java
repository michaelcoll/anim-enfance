package fr.animenfance.bean.partenaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partner {

  @Id
  private String id;

  @NotBlank
  private String name;

  private List<Show> shows;
}
