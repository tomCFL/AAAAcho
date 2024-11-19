package com.example.choma2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .oauth2Login()
                .clientRegistrationRepository(clientRegistrationRepository())
                .and()
                .authorizeRequests()
                .antMatchers("/tasks/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(

                ClientRegistration.withRegistrationId("example")
                        .clientId("your-client-id")
                        .clientSecret("your-client-secret")
                        .scope("openid", "profile", "email")
                        .authorizationUri("https://authorization-server.com/oauth/authorize")
                        .tokenUri("https://authorization-server.com/oauth/token")
                        .userInfoUri("https://authorization-server.com/userinfo")
                        .clientName("OAuth2 Example")
                        .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                        .build()
        );
    }
}
