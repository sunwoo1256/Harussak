package D.Co.Harussak.security;

import static jakarta.servlet.DispatcherType.ERROR;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)     // HTTP 기본 인증 방식 비활성화(브라우저 팝업창 로그인 방지)
            .sessionManagement(session -> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))   // 세션을 사용하지 않도록 설정(JWT는 Stateless 방식이므로 세션 필요 없음)
            .authorizeHttpRequests(auth -> auth     // 요청에 대한 인가(Authorization) 설정
                .dispatcherTypeMatchers(ERROR).permitAll()
                .requestMatchers("/users/signup", "/users/login")
                .permitAll()  // 로그인과 회원가입은 인증 없이 접근 가능
                // requestMatchers를 추가해 API를 통한 필터링 가능
                .anyRequest().authenticated()   // 그 외 모든 요청은 인증 필요
            )
            //UsernamePasswordAuthenticationFilter 전에 JwtAuthenticationFilter를 실행하겠다
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
