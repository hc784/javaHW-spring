package board.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import board.dto.LoginRequest;
import board.dto.SignupRequest;
import board.entity.User;
import board.service.UserService;

@RestController @RequestMapping("/api/users") @RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid SignupRequest req) {
        userService.signup(req);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public Long login(@RequestBody @Valid LoginRequest req) {
        User user = userService.login(req);
        return user.getId();    // 간단히 userId 반환 → Swing 쪽에서 세션처럼 사용
    }
}
