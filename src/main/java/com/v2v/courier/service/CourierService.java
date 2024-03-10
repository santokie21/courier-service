package com.v2v.courier.service;

import com.v2v.courier.model.Courier;
import com.v2v.courier.model.User;
import com.v2v.courier.repository.CourierRepository;
import com.v2v.courier.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {

  private final CourierRepository courierRepository;
  private final UserRepository userRepository;

  public CourierService(CourierRepository courierRepository, UserRepository userRepository){
    this.courierRepository = courierRepository;
    this.userRepository = userRepository;
  }

  public List<Courier> getAllCouriers(){
    return courierRepository.findAll();
  }

  public Courier getCourierById(Integer id){
    return courierRepository.findById(id).orElse(null);
  }

  public void createCourier(Courier courier){
    courierRepository.save(courier);
  }

  public boolean updateCourier(Integer id, Courier update){
    Courier courier = getCourierById(id);
    if ( courier != null ) {
      if ( update.getName() != null ) {
        courier.setName(update.getName());
      }
      if ( update.getPhone() != null ) {
        courier.setPhone(update.getPhone());
      }
      if ( update.getAddress() != null ) {
        courier.setAddress(update.getAddress());
      }
      if ( update.getStatus() != null ) {
        courier.setStatus(update.getStatus());
      }
      if ( update.getLocation() != null ) {
        courier.setLocation(update.getLocation());
      }
      if ( update.getRating() != 0 ) {
        courier.setRating(update.getRating());
      }
      courierRepository.save(courier);
      return true;
    }
    return false;
  }

  public boolean deleteCourier(Integer id){
    if ( courierRepository.existsById(id) ) {
      courierRepository.deleteById(id);
      return true;
    }
    return false;
  }

  public boolean assignCourierToUser(Integer courierId, Integer userId){
    User user = userRepository.findById(userId).get();
    Courier courier = getCourierById(courierId);
    if ( user != null && courier != null ) {
      courier.setUser(user);
      courierRepository.save(courier);
      return true;
    }
    return false;
  }
}
