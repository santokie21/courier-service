package com.v2v.courier.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String email;
  @Column(unique = true)
  private String username;
  private String password;
  @Enumerated(value = EnumType.STRING)
  private Role role;

  @JsonManagedReference
  @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
  private List<Courier> couriers = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities(){
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword(){
    return this.password;
  }

  @Override
  public String getUsername(){
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired(){
    return true;
  }

  @Override
  public boolean isAccountNonLocked(){
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired(){
    return true;
  }

  @Override
  public boolean isEnabled(){
    return true;
  }
}
