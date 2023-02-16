package Comprar.Carrito.security.usecases;

 import Comprar.Carrito.security.repository.UserRepository;

 import org.springframework.security.core.authority.SimpleGrantedAuthority;
 import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
 import org.springframework.security.core.userdetails.User;
 import org.springframework.security.core.userdetails.UserDetails;

 import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.stereotype.Service;
 import reactor.core.publisher.Mono;

 import java.util.stream.Collectors;

 @Service
 public class UserDetailsServiceImpl implements ReactiveUserDetailsService {
     private final UserRepository userRepository;

     public UserDetailsServiceImpl(UserRepository userRepository) {
         this.userRepository = userRepository;
     }
     @Override
     public Mono<UserDetails> findByUsername(String username) {
         System.out.println(username);
         return userRepository.findByEmail(username)
                 .map(user ->
                 new User(user.getUsername(), user.getPassword(),
                         user.getRoles().stream()
                                 .map(role ->
                                 new SimpleGrantedAuthority(role))
                                 .collect(Collectors.toList())));

     }
 }











