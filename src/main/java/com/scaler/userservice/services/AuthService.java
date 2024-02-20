package com.scaler.userservice.services;

import com.scaler.userservice.dtos.UserDto;
import com.scaler.userservice.models.Session;
import com.scaler.userservice.models.SessionStatus;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositeries.SessionRepository;
import com.scaler.userservice.repositeries.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecretKey secretKey;

    @Autowired
    public AuthService(UserRepository userRepository,
                       SessionRepository sessionRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.secretKey = Jwts.SIG.HS256.key().build();
    }

    public UserDto signup(String email,String password) {
        // Check if user with the given email already exists
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            return null;
        }
        User user = new User();
        user.setEmail(email);

        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }

    // Note: This method should return a custom object containing token, headers, etc
    // For now, to avoid creating an object, directly returning ResponseEntity from here
    public ResponseEntity<UserDto> login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            return null; // Replace this with an appropriate exception
        }

        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Password and User name does not match."); // Replace this with an appropriate exception
        }
//        String token= RandomStringUtils.randomAlphanumeric(30);

        Map<String,Object> jwtData = new HashMap<>();
        jwtData.put("email",email);
        jwtData.put("createdAt",new Date());
        jwtData.put("expiryAt",new Date(LocalDate.now().plusDays(3).toEpochDay()));
        String token= Jwts
                .builder()
                .signWith(secretKey)
                .claims(jwtData)
                .compact();

        Session session = new Session();
        session.setUser(user);
        session.setToken(token);
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);

        UserDto userDto = UserDto.from(user);

        MultiValueMap<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:"+token);
        return new ResponseEntity<UserDto>(userDto, headers, HttpStatus.OK);

    }

    public void logout(String token, Long userId) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token, userId);
        if(optionalSession.isEmpty()){
            return; // Replace this with an appropriate exception
        }
        Session session = optionalSession.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
    }

    public SessionStatus validateToken(String token, Long userId) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token, userId);
        if(optionalSession.isEmpty()){
            return SessionStatus.ENDED;
        }
        Session session=optionalSession.get();

        //Write logic to verify the JWT token with the secret key
        //If the token is valid, return ACTIVE
        //If the token is expired, return ENDED
        Jws<Claims> claimsJws= Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
        String email=(String) claimsJws.getPayload().get("email");
        Integer expiryAt= (Integer) claimsJws.getPayload().get("expiryAt");

        if(!session.getUser().getEmail().equals(email)){
            return SessionStatus.ENDED; // Replace this with custom exception
        }

        if(expiryAt<LocalDate.now().toEpochDay()){
            return SessionStatus.ENDED;
        }

        return session.getSessionStatus();
    }
}
