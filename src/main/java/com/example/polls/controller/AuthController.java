package com.example.polls.controller;


import com.example.polls.exception.AppException;
import com.example.polls.model.*;
import com.example.polls.payload.*;
import com.example.polls.repository.RoleRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.security.JwtTokenProvider;
import com.example.polls.security.UserPrincipal;
import com.example.polls.service.PracticeService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

import org.springframework.mail.javamail.JavaMailSender;


/**
 * Created by nikola brodar on 20/9/2019
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Autowired
    JwtTokenProvider tokenProvider;

   /* TODO: IMPLEMENT THIS ENDPOINT
    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {

        User user = userRepository.findByUsernameOrEmail("", forgotPasswordRequest.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + forgotPasswordRequest.getEmail())
                );

        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(token, user));

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/login")
                .buildAndExpand(forgotPasswordRequest.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Reset password has been send"));
    }*/

    /* TODO: IMPLEMENT THIS ENDPOINT
    @PostMapping("/user/savePassword")
    public ResponseEntity<?> savePassword(@Valid @RequestBody SavePasswordRequest savePasswordRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/login")
                .buildAndExpand().toUri();

        return ResponseEntity.created(location).
                body(new ApiResponse(true, "New password has been saved successfully"));
    } */


    @PostMapping("/{userId}/userDataSplash/")
    public ResponseEntity<?> userDataSplashScreen( @PathVariable(value = "userId") Long userId ) {

        List<MasterClass> masterClassList = practiceService.getUserMasterClassDataSplashScreen(userId);
        List<SubMasterClass> subMasterClassList = practiceService.getUserSubMasterClassDataSplashScreen(userId);

        if( masterClassList.size() > 0 || subMasterClassList.size() > 0 ) {

            return ResponseEntity.ok(new ApiResponseLogin(true, "User has finished some excercise",
                    userId, masterClassList, subMasterClassList));
        }
        return ResponseEntity.ok(new ApiResponseLogin(true, "User has not finished any excecise",
                userId, masterClassList, subMasterClassList));
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<MasterClass> masterClassList = practiceService.getUserMasterClassData(user);
        List<SubMasterClass> subMasterClassList = practiceService.getUserSubMasterClassData(user);

        if( user != null ) {
            //ApiResponseLogin aaa = new ApiResponseLogin(true, "User logged in successfully",
            //         user.getId(), masterClassList, subMasterClassList);

            return ResponseEntity.ok(new ApiResponseLogin(true, "User logged in successfully",
                    user.getId(), masterClassList, subMasterClassList));
        }
        return new ResponseEntity(new ApiResponse(false, "Wrong username or password. "),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole  = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }


    @PostMapping("/solvedSubMasterClass")
    public ResponseEntity<?> solvedSubMasterClass(@Valid @RequestBody SolvedSubMasterClassRequest solvedSubMasterClassRequest) {

        // 1 NACIN DODAVANJA SUB_MASTER_CLASS U BAZU UZ POMOC PISANJA NATIVNIH QUERY
        int solvedSubMasterClass = userRepository.solvedSubMasterClass(solvedSubMasterClassRequest);

        //2 nacin VAZNOOOOOO samo treba napraviti repository koji bude extend -ao  "extends JpaRepository<SubMasterClass, Long>"
        // Creating submaster class
        //SubMasterClass subMasterClass = new SubMasterClass(solvedSubMasterClassRequest.getName(), solvedSubMasterClassRequest.getId_user(),
        //        solvedSubMasterClassRequest.getId_masterclass(), solvedSubMasterClassRequest.getSolved());
        // VAZNOOOOOO samo treba napraviti repository koji bude extend -ao  "extends JpaRepository<SubMasterClass, Long>"
        //userRepository.save(solvedSubMasterClassRequest);

        return ResponseEntity.ok().body(new ApiResponse(true, "User database with sub masterclass successfully updated"));
    }

}
