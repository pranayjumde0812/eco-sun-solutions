package com.ecosun.controller;

import com.ecosun.dto.ApiResponse;
import com.ecosun.dto.UserDTO;
import com.ecosun.dto.request.UserUpdateRequestDto;
import com.ecosun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping
	public ResponseEntity<?> getAllUsers() {

		List<UserDTO> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable Long userId) {
		UserDTO userById = userService.getUserById(userId);
		return new ResponseEntity<>(userById, HttpStatus.OK);
	}

//	@PostMapping
//	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
//		UserDTO createUser = userService.createUser(userDTO);
//		return new ResponseEntity<>(new ApiResponse("User Created Successfully", true), HttpStatus.CREATED);
//	}

	@PreAuthorize("hasAuthority('CUSTOMER')")
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDto userDTO) {

		UserDTO updateUser = userService.updateUser(userId, userDTO);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}
	
//	@GetMapping("/findUserId/{email}")
//	public ResponseEntity<?> getUserIdByEmail(@PathVariable String email) {
//		Long userIdByEmail = userService.getUserIdByEmail(email);
//		return new ResponseEntity<>(userIdByEmail, HttpStatus.OK);
//	}
	
	@GetMapping("/findByEmail")
    public ResponseEntity<Long> getUserIdByEmail(@RequestParam String email) {
        Long userId = userService.findUserIdByEmail(email);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
