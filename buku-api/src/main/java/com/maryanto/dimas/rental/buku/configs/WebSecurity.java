package com.maryanto.dimas.rental.buku.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final String ng4EntriPoint = "NG4_ENTRY_POINT";
    @Autowired
    private Ng4BasicEntryPoint ng4BasicEntryPoint;

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic().realmName(ng4EntriPoint).authenticationEntryPoint(ng4BasicEntryPoint);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
        return manager;
    }

    @Component
    public class Ng4BasicEntryPoint extends BasicAuthenticationEntryPoint {
        @Override
        public void afterPropertiesSet() throws Exception {
            setRealmName(ng4EntriPoint);
            super.afterPropertiesSet();
        }

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//            super.commence(request, response, authException);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

            PrintWriter writer = response.getWriter();
            writer.println("HTTP Status 401 : " + authException.getMessage());
        }
    }

}
