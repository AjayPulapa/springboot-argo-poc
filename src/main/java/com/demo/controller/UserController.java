package com.demo.controller;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;
import com.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final static Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    // CREATE - Add a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // READ - Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/poc")
    public String getText() {
        return "<html>" +
                "<head>" +
                "<style>" +
                "body { display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f0f2f5; }" +
                "h1 { color: green; font-size: 24px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<h1>Application Deployed in an ArgoCD Container Running Now</h1>" +
                "</body>" +
                "</html>";
    }
    
    @GetMapping("/test")
    public String hello()
    {
    	return "hello";
    }

    // READ - Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // UPDATE - Update a user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DELETE - Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @Autowired
	private JavaMailSender mailSender;

//	@Autowired
//	private ShedlockRepository shedlockRepository;



//	@Scheduled(cron = "0 */5 * * * *") // Every 5 minutes
    @GetMapping("/cronfive")
	public String sendEmail5min() {
//		if (jobPod()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo("ajayajaypulapa143@gmail.com");
			message.setSubject("Spring Boot Application Running For Every 5min");
			message.setText("Hello, your Spring Boot application is running successfully.");
			mailSender.send(message);
			log.info("Email sent successfully.");
			return "Email sent successfully.";
//		} else {
//			return "Batch Not Scheduled";
//		}

	}
	@GetMapping("/cronten")
	public String sendEmail10min() {
//		if (jobPod()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo("ajayajaypulapa143@gmail.com");
			message.setSubject("Spring Boot Application Running For Every 10min");
			message.setText("Hello, your Spring Boot application is running successfully.");
			mailSender.send(message);
			log.info("Email sent successfully.");
			return "Email sent successfully.";
//		} else {
//			return "Batch Not Scheduled";
//		}

	}
}
