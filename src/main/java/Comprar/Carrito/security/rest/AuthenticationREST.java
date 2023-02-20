package Comprar.Carrito.security.rest;

import Comprar.Carrito.security.models.AuthRequest;
import Comprar.Carrito.security.models.AuthResponse;
import Comprar.Carrito.security.repository.UserRepository;
import Comprar.Carrito.securityHandle.JWTUtil;
import Comprar.Carrito.securityHandle.PBKDF2Encoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuthenticationREST {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/resource/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok("Hello admin");
    }

    @GetMapping("/resource/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> user() {
        return ResponseEntity.ok("Hello user");
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userRepository.findByUsername(ar.getUsername())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}
