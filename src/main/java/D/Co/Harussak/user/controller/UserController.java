package D.Co.Harussak.user.controller;

import D.Co.Harussak.security.JwtToken;
import D.Co.Harussak.user.dto.LoginRequest;
import D.Co.Harussak.user.dto.SignUpRequest;
import D.Co.Harussak.user.dto.SignUpResponse;
import D.Co.Harussak.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "로그인", description = "사용자 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "이메일 또는 비밀번호가 일치하지 않습니다.")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            JwtToken jwtToken = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(jwtToken);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    @Operation(summary = "회원가입", description = "사용자 회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 회원입니다")
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        try{
            SignUpResponse signUpResponse = userService.signUp(signUpRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 회원입니다");
        }
    }

}