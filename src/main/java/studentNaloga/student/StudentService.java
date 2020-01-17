package studentNaloga.student;

import studentNaloga.studentClass.StudentClass;
import studentNaloga.studentClass.StudentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;
    List<Student> students = new ArrayList<>();

    @Autowired
    StudentClassRepository studentClassRepository;
    List<StudentClass> studentClasses = new ArrayList<>();

    public List<Student> getAllStudents() {
        studentRepository.findAll().forEach(student -> students.add(student));
        return students;
    }

    public Student getStudentById(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.get();
    }

    public List<Student> enrollStudentToClass(Integer studentId, Integer classId) {
        studentRepository.findById(studentId).map(student -> {student.getEnrolledClassID().add(studentClassRepository.findById(classId).get());
                    return studentRepository.save(student);
                });
        return null;
    }

    public List<Student> deleteEnrolledStudentToClass(Integer studentId, Integer classId) {
        studentRepository.findById(studentId).map(student -> {student.getEnrolledClassID().remove(studentClassRepository.findById(classId).get());
            return studentRepository.save(student);
        });
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findStudentByUserName(username);
        if(student == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(student.getUsername(), student.getPassword(), getAuthority());

    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

}
