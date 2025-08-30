package D.Co.Harussak.user.service;

import D.Co.Harussak.entity.User;
import D.Co.Harussak.user.repository.UserRepository;
import D.Co.Harussak.security.JwtToken;
import D.Co.Harussak.security.JwtTokenProvider;
import D.Co.Harussak.user.dto.SignUpRequest;
import D.Co.Harussak.user.dto.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtToken login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new IllegalArgumentException("이미 사용중인 이메일 입니다.");
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        User user = userRepository.save(signUpRequest.toUser(encodedPassword));
        return new SignUpResponse(user.getEmail(), user.getRoles());

    }
}
