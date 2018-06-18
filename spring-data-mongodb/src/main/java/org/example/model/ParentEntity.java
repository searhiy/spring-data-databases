package org.example.model;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ParentEntity {
  @Id
  private String id;

  private String name;

  private String surname;

  private String address;

  private String zip;
}
