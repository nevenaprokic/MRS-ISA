package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.booking.ISAbackend.model.MyUser;
import com.booking.ISAbackend.model.RegistrationRequest;

import dto.UserRequest;
import repository.UserRepository;

public interface UserService {
	
	MyUser findById(Integer id);
	MyUser findByEmail(String email);
    List<MyUser> findAll ();
    MyUser save(UserRequest userRequest);
}
