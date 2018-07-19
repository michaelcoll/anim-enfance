package fr.animenfance.bean.partenaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.DayOfWeek;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDate {

  @Id
  private String id;
  private DayOfWeek weekOfDay;
  private Date date;

}
