/*package bd2.projecto.bibliotecaVirtual.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/api/public/**").permitAll()  // caminhos liberados
                        .requestMatchers("/api/analytics").authenticated()       // exige login
                        .anyRequest().denyAll()
                )
                .formLogin(login -> login
                        .loginPage("/login")      // seu próprio endpoint de login
                        .defaultSuccessUrl("/api/analytics", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/static/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/",
                                "/index.html"
                        ).permitAll() // ✅ acesso livre
                        .requestMatchers("/admin.html").authenticated() // ✅ protegido
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/admin.html", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("joel")
                .password("{noop}1234") // {noop} = sem encoder, apenas para testes
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
*/
