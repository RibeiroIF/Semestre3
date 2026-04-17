package br.edu.ifsc.fln.vendas.estoqueapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Configuração das Regras de Bloqueio
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita proteção CSRF (necessário para APIs REST)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Exige autenticação para QUALQUER rota
                )
                .httpBasic(Customizer.withDefaults()); // Habilita autenticação básica via Header

        return http.build();
    }

    // 2. Criação dos Usuários em Memória (Hardcoded)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123") // {noop} avisa o Spring para não exigir criptografia
                .roles("ADMIN")
                .build();

        UserDetails usuarioComum = User.withUsername("aluno")
                .password("{noop}aluno123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, usuarioComum);
    }
}
