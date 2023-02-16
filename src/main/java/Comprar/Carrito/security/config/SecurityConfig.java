package Comprar.Carrito.security.config;


//  import Comprar.Carrito.security.usecases.UserDetailsServiceImpl;
//  import org.springframework.beans.factory.annotation.Autowired;
//  import org.springframework.context.annotation.Bean;
//  import org.springframework.context.annotation.Configuration;
//  import org.springframework.security.authentication.AuthenticationManager;
//  import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//  import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//  import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//  import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//  import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//  import org.springframework.security.config.web.server.ServerHttpSecurity;
//  import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//  import org.springframework.security.crypto.password.PasswordEncoder;
//  import org.springframework.security.web.SecurityFilterChain;
//  import org.springframework.security.web.server.SecurityWebFilterChain;
//
//
//  @Configuration
//  @EnableWebSecurity
//  public class SecurityConfig {
//      @Bean
//      public PasswordEncoder passwordEncoder() {
//          return new BCryptPasswordEncoder();
//      }
//      @Autowired
//      private UserDetailsServiceImpl userDetailsService;
//      @Bean
//      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//          http.authorizeRequests()
//                  .antMatchers("/createUser").permitAll()
//                  .antMatchers("/", "/home").access("hasRole('USER')")
//                  .antMatchers("/admin/**").hasRole("ADMIN")
//                  .and()
//                  // some more method calls
//                  .formLogin();
//          return http.build();
//      }
//
//
//
//  }







//
//     @Bean
//      public BCryptPasswordEncoder passwordEncoder(){
//          BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
//         return bCryptPasswordEncoder;
//
// }
// }
  // @Autowired
   // private UserDetailsService userDetailsService;
   //
   // @Autowired
   // private BCryptPasswordEncoder bCrypt;
   // @Bean
   // public BCryptPasswordEncoder passwordEncoder(){
   //     BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
   //     return bCryptPasswordEncoder;
   //
   //
   // }
   // protected void Configure(AuthenticationManagerBuilder auth)
   //     throws Exception{
   //     auth
   //             .userDetailsService(userDetailsService).passwordEncoder(bCrypt);
   // }
   // @Override
   // protected void configure(HttpSecurity http) throws Exception {
   //     http
   //             .authorizeRequests()
   //             .antMatchers("/createUser").permitAll()
   //             .antMatchers("/private/**").authenticated()
   //             .and()
   //             .formLogin()
   //             .loginPage("/login")
   //             .permitAll()
   //             .and()
   //             .logout()
   //             .logoutSuccessUrl("/")
   //             .permitAll();
   // }




//}






