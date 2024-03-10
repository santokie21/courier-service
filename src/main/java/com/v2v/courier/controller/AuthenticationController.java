package com.v2v.courier.controller;

import com.v2v.courier.model.User;
import com.v2v.courier.response.AuthenticationResponse;
import com.v2v.courier.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService){
    this.authenticationService = authenticationService;
  }

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) {
    return ResponseEntity.ok(authenticationService.register(user));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {
    return ResponseEntity.ok(authenticationService.authenticate(user));
  }
}
