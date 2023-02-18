package Comprar.Carrito.security.usecases;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
@Service
public class JWTAuthenticationConverter implements ServerAuthenticationConverter {
    private final JWTUtil jwtUtils;

    public JWTAuthenticationConverter(JWTUtil jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        String token = extractTokenFromHeader(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
        if (token != null && jwtUtils.validateToken(token)) {
            String username = jwtUtils.getUsernameFromToken(token);
            List<GrantedAuthority> authorities = jwtUtils.getAuthoritiesFromToken(token);
            Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }

    private String extractTokenFromHeader(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
