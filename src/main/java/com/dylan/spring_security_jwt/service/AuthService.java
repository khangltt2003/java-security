package com.dylan.spring_security_jwt.service;

import com.dylan.spring_security_jwt.exception.MyException;
import com.dylan.spring_security_jwt.Utils.AuthUtils;
import com.dylan.spring_security_jwt.dto.LoginDTO;
import com.dylan.spring_security_jwt.dto.RegisterDTO;
import com.dylan.spring_security_jwt.dto.VerifyDTO;
import com.dylan.spring_security_jwt.entity.User;
import com.dylan.spring_security_jwt.repo.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    public User register(RegisterDTO registerDTO) {
        User newUser = new User(registerDTO.getUsername(), passwordEncoder.encode(registerDTO.getPassword()), registerDTO.getEmail());
        String verificationCode = AuthUtils.generateVerificationCode();
        newUser.setEnable(false);
        newUser.setVerificationCode(verificationCode);
        newUser.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));

        emailService.sendVerificationEmail(registerDTO.getEmail(), verificationCode);
        return userRepository.save(newUser);
    }

    public User login(LoginDTO loginDTO){
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        User foundUser = userRepository.findByEmail(email).orElseThrow(()-> new MyException("cannot find user"));

        if(!foundUser.isEnable()){
            throw new MyException("account is not verified");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        return foundUser;
    }

    public void verify(VerifyDTO verifyDTO){
        String email = verifyDTO.getEmail();
        String verificationCode = verifyDTO.getVerificationCode();
        User foundUser = userRepository.findByEmail(email).orElseThrow(()-> new MyException("cannot find user"));

        if(!foundUser.getVerificationCode().equals(verificationCode)){
            throw  new MyException("invalid verification code");
        }
        foundUser.setEnable(true);
        foundUser.setVerificationCode(null);
        foundUser.setVerificationCodeExpiresAt(null);
        userRepository.save(foundUser);
    }

    public void resendVerificationCode(String email){
        User foundUser = userRepository.findByEmail(email).orElseThrow(()-> new MyException("cannot find user"));
        if(foundUser.isEnabled()){
            throw new MyException("account is already verified");
        }

        foundUser.setVerificationCode(AuthUtils.generateVerificationCode());
        foundUser.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));
        userRepository.save(foundUser);
    }



}
