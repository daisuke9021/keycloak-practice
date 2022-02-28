package keyclock.practice.keycloakpracticeback.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        CustomJwtConverter customJwtConverter;
        JwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation("http://localhost:8180/auth/realms/user");

        @Override
        protected void configure(HttpSecurity http) throws Exception {

                http.cors();
                http.csrf().disable();

                http.authorizeRequests(requests -> requests
                                .antMatchers(HttpMethod.GET, "/admin/hello").hasRole("user")
                                .antMatchers(HttpMethod.GET, "/user/hello").permitAll());

                http.oauth2ResourceServer(
                                oauth2ResourceServerCustomizer -> oauth2ResourceServerCustomizer
                                                .jwt(jwtCustomizer -> jwtCustomizer
                                                                .jwtAuthenticationConverter(
                                                                                customJwtConverter)
                                                                .decoder(jwtDecoder)));
        }
}
