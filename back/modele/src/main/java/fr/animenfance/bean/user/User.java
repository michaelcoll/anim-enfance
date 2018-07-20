package fr.animenfance.bean.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

  @Id
  private String id;
  private String username;

  @JsonIgnore
  private String password;

  @Email
  private String email;

  @Builder.Default()
  private boolean active = true;

  @Builder.Default()
  private List<String> roles = new ArrayList<>();
}
