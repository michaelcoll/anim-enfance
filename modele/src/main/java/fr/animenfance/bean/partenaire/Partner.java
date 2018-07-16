package fr.animenfance.bean.partenaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partner {

  @Id
  private String id;
  private String name;
}
