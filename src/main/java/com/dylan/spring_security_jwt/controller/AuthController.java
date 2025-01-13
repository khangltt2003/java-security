package com.dylan.spring_security_jwt.controller;

import com.dylan.spring_security_jwt.dto.LoginDTO;
import com.dylan.spring_security_jwt.dto.LoginResponseDTO;
import com.dylan.spring_security_jwt.dto.RegisterDTO;
import com.dylan.spring_security_jwt.dto.ResponseDTO;
import com.dylan.spring_security_jwt.entity.User;
import com.dylan.spring_security_jwt.repo.UserRepository;
import com.dylan.spring_security_jwt.service.AuthService;
import com.dylan.spring_security_jwt.service.JwtService;
import com.dylan.spring_security_jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterDTO registerDTO){
        ResponseDTO response = new ResponseDTO();
        User user  = authService.register(registerDTO);
        response.setStatusCode(201);
        response.setMessage("success");
        response.setData(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO){
        User user  = authService.login(loginDTO);
        String jwt = jwtService.generateJwt(user);
        LoginResponseDTO response = new LoginResponseDTO(jwt, jwtService.getExpiration());
        return ResponseEntity.status(200).body(response);
    }

}
