package ru.indeece.userservice.controller;

import ru.indeece.userservice.dto.LoginRequest;
import ru.indeece.userservice.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.indeece.userservice.service.MailSenderService;
import ru.indeece.userservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final MailSenderService mailSenderService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        mailSenderService.sendMail(request.getEmail(),
                "Welcome to Indeece! Your registration is complete",
                "Thanks for signing up for Indeece!\n" +
                        "\n" +
                        "Your account is ready, and you can now start ordering fresh groceries with ease.");
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}