package com.alkemy.java.security;
import com.alkemy.java.repository.RoleRepository;
import com.alkemy.java.security.filters.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RoleRepository roleRepository;

	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private static final String[] PUBLIC_URLS={ //ENDPOINTS SIN AUTENTICAR
            "/auth/login",
            "/auth/register",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/members",
            "/members/{id}",
            "/organization/public/{id}"
    };

    private static final String[] ADMIN_URLS={ //ENDPOINTS SOLO PARA ROL_ADMIN
            "/categories/**",
            "/comments/**",
            "/news",
            "/contacts",
            "/slides",
            "/testimonials/**",
            "/users/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.inMemoryAuthentication().withUser("testAlkemy").password("12345678Ss").roles("USER");
        auth.inMemoryAuthentication().withUser("test").password("12345678Ss").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                        .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/categories").permitAll()
                        .antMatchers(HttpMethod.GET, "/members").hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.DELETE, "/members/{id}").hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.PUT, "/organization/public/{id}").hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.POST,"/comments").authenticated()
                        .antMatchers(HttpMethod.DELETE,"/comments/{id}").authenticated()
                        .antMatchers(PUBLIC_URLS).permitAll()
                        .antMatchers(ADMIN_URLS).hasAuthority(ROLE_ADMIN)
                        .anyRequest().authenticated()
                        .and().sessionManagement().sessionCreationPolicy(STATELESS);
                         http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
