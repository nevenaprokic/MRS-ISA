package model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RegistrationRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;
	private String personType;
	private String firstName;
	private String lastName;
	private String password;
	private String phoneNumber;
	private String email;
	private Boolean deleted;
}
