package by.bsu.tutor.config;

import by.bsu.tutor.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getShaPasswordEncoder());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("1").password("1").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                .antMatchers("/administration/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll();

        http.csrf().disable();
    }

    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder() {
        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
        passwordEncoder.setEncodeHashAsBase64(true);
        return passwordEncoder;
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public FilterRegistrationBean getSpringSecurityFilterChainBindedToError(
            @Qualifier("springSecurityFilterChain") Filter springSecurityFilterChain) {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(springSecurityFilterChain);
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }

}
