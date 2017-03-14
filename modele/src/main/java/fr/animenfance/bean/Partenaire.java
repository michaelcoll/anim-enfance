package fr.animenfance.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partenaire {
  private Integer id;
  private String name;
  private String fromHost;
}
