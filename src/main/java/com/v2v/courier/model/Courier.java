package com.v2v.courier.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courier")
public class Courier {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @Column(name = "phone_number")
  private String phone;
  private String address;
  private String status;
  private String location;
  private double rating;

  @JsonBackReference
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id",referencedColumnName = "id")
  private User user;

}
