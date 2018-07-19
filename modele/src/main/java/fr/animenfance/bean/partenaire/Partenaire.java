package fr.animenfance.bean.partenaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partenaire {
  private int id;
  private String name;
  private String fromHost;
}
