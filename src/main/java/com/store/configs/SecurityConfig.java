package com.store.configs;


import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


/*
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {


 */
    @Autowired
    private UserService userService;



/*

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*");
    }


 */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth").authenticated()
                .antMatchers().permitAll()
                .anyRequest().permitAll()
               // .authenticated()
                .and()
                .formLogin()
                .and()
                /*
                .formLogin().loginPage("http://localhost:4200/category").permitAll()
                .defaultSuccessUrl("http://localhost:4200/")

                 */
        ;
    }



/*

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll();
    }



 */

/*
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*");
    }


 */





        /*

        http.authorizeRequests()
                .antMatchers("/auth").authenticated()
                .anyRequest().permitAll()
                //.antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
                //.antMatchers("/profile/**").hasAuthority()
                .and()
               //.httpBasic()
                //для того чтобы пользователи заходили на сайт представляем стандартную форму
                .formLogin()
                .and()
                //После logout попадают в "/"
                .logout().logoutSuccessUrl("/")
                .and() .csrf().disable();



         */




/*
        http.csrf().
                disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

 */

//    }

    /*              !!!     In Memory Method    !!!!!!
    @Bean
    public UserDetailsService users(){
        UserDetails user = User.builder()
                .username("Tema")
                .password("{bcrypt}$2a$12$qD2GD3pu7BZju28bsG6c1eWg9uUp/183rJpRCMmQtEUy5QlbwTekW")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("Admin")
                .password("{bcrypt}$2a$12$qD2GD3pu7BZju28bsG6c1eWg9uUp/183rJpRCMmQtEUy5QlbwTekW")
                .roles("ADMIN" , "USER")
                .build();
        //Положили просто в память
        return new InMemoryUserDetailsManager(user,admin);
    }



     */

    //преобразует пароль в хэш

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }





}
