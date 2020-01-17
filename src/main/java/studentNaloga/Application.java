package studentNaloga;

import studentNaloga.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, StudentRepository repo) throws Exception {
//        repo.save(new Student(51, "chalesale", "user", "user"));
        //builder.userDetailsService(userName -> new Student(repo.findStudentByUserName(userName)));
    }
}
