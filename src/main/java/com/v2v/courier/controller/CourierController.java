package com.v2v.courier.controller;

import com.v2v.courier.model.Courier;
import com.v2v.courier.service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/couriers")
public class CourierController {

  private final CourierService courierService;

  public CourierController(CourierService courierService){
    this.courierService = courierService;
  }

  @GetMapping
  public ResponseEntity<List<Courier>> getAllCouriers(){
    return ResponseEntity.ok(courierService.getAllCouriers());
  }

  @GetMapping("{id}")
  public ResponseEntity<Courier> getCourierById(@PathVariable Integer id){
    Courier courier = courierService.getCourierById(id);
    if ( courier == null ) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(courierService.getCourierById(id));
  }

  @PostMapping
  public ResponseEntity<String> createCourier(@RequestBody Courier courier){
    courierService.createCourier(courier);
    return ResponseEntity.ok("Courier added successfully!!!");
  }

  @PutMapping("{id}")
  public ResponseEntity<String> updateCourier(@PathVariable Integer id, @RequestBody Courier update){
    boolean isUpdated = courierService.updateCourier(id, update);
    if ( isUpdated ) {
      return ResponseEntity.ok("Courier updated successfully!!!");
    }
    return new ResponseEntity<>("Courier not found with id : " + id, HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteCourier(@PathVariable Integer id){
    boolean isDeleted = courierService.deleteCourier(id);
    if ( isDeleted ) {
      return ResponseEntity.ok("Courier Deleted successfully!!!");
    }
    return new ResponseEntity<>("Courier not found with id : " + id, HttpStatus.NOT_FOUND);
  }

  @PutMapping("{courierId}/users/{userId}")
  public ResponseEntity<String> assignCourierToUser(@PathVariable Integer courierId, @PathVariable Integer userId){
    boolean isAssigned = courierService.assignCourierToUser(courierId, userId);
    if ( isAssigned ) {
      return ResponseEntity.ok("Courier assigned to user successfully!!!");
    }
    return new ResponseEntity<>("Courier not found with id : " + courierId, HttpStatus.NOT_FOUND);
  }
}
