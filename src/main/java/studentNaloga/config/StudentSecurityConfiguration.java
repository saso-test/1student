package studentNaloga.config;

import studentNaloga.student.StudentRepository;
import studentNaloga.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity(debug=true)
public class StudentSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/students/*").hasRole("USER").anyRequest().authenticated()
                .antMatchers("/studentClass/*").hasRole("USER").anyRequest().authenticated()
                .antMatchers("/studentClassName/*").hasRole("USER").anyRequest().authenticated();*/


        http
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}user")
                .roles("USER")

                .and()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN");*/

//        auth.userDetailsService(studentService);

        auth.userDetailsService(studentService).passwordEncoder(passwordEncoder());
    }

/*    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(8);
    }*/

    @SuppressWarnings("deprecation")
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
