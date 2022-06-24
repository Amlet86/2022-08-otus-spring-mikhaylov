package ru.amlet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/books/create", "/books/edit", "/books/delete").hasAuthority("ROLE_ADMIN")
                .antMatchers("/authors/create", "/authors/edit", "/authors/delete").hasAuthority("ROLE_ADMIN")
                .antMatchers("/books", "/books/**", "/authors", "/authors/**", "/genres", "/genres/**").authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
