package com.serviceCenter.ServiceCenter.controllers;

import com.serviceCenter.ServiceCenter.models.ERole;
import com.serviceCenter.ServiceCenter.models.Role;
import com.serviceCenter.ServiceCenter.models.User;
import com.serviceCenter.ServiceCenter.payload.request.LoginRequest;
import com.serviceCenter.ServiceCenter.payload.request.SignupRequest;
import com.serviceCenter.ServiceCenter.payload.response.JwtResponse;
import com.serviceCenter.ServiceCenter.payload.response.MessageResponse;
import com.serviceCenter.ServiceCenter.repository.RoleRepository;
import com.serviceCenter.ServiceCenter.repository.UserRepository;
import com.serviceCenter.ServiceCenter.security.jwt.JwtUtils;
import com.serviceCenter.ServiceCenter.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/service")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/logIn")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(),
												 roles));
	}

	@PostMapping("/signUp")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Username is already taken, use another name!"));
		}

		User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			new RuntimeException("Error: Invalid Role.");
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "manager":
						Role adminRole = roleRepository.findByName(ERole.ROLE_MANAGER)
								.orElseThrow(() -> new RuntimeException("Error: Invalid role."));
						roles.add(adminRole);

						break;
					case "owner":
						Role modRole = roleRepository.findByName(ERole.ROLE_OWNER)
								.orElseThrow(() -> new RuntimeException("Error: Invalid role."));
						roles.add(modRole);

						break;
					default:
						new RuntimeException("Error: Invalid role.");

				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("successfully Registered"));
	}
}
