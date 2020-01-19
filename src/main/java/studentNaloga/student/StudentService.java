package studentNaloga.student;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import studentNaloga.studentClass.StudentClass;
import studentNaloga.studentClass.StudentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

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

    public String enrollStudentToClass(Integer studentId, Integer classId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Student tempStudent = studentRepository.findById(studentId).get();
        if(tempStudent.getUserName() != auth.getName()) return "You are not authorized for enroll other students.";

        Set<StudentClass> std = studentRepository.findById(studentId).get().getEnrolledClassID();;
        for (StudentClass s : std) {
            if (s.getClassId() == (classId)) {
              return studentRepository.findById(studentId).get().getStudentName() +" is allready enrolled to a class: " + studentClassRepository.findById(classId).get().getClassName();
            }
        }

        studentRepository.findById(studentId).map(student -> {student.getEnrolledClassID().add(studentClassRepository.findById(classId).get());
            return studentRepository.save(student);
        });
        return studentRepository.findById(studentId).get().getStudentName() +" enrolled to class: " + studentClassRepository.findById(classId).get().getClassName();
    }

    public String deleteEnrolledStudentToClass(Integer studentId, Integer classId) {
        Boolean studentIsPresent = studentRepository.findById(studentId).isPresent();
        Boolean classIsPresent = studentClassRepository.findById(classId).isPresent();

        if (!studentIsPresent) return "Wrong student or not exists.";
        if (!classIsPresent) return "Wrong studentClass or not exists.";

        if (studentRepository.findById(studentId).get().getEnrolledClassID().contains(classId) ) {
            studentRepository.findById(studentId).map(student -> {student.getEnrolledClassID().remove(studentClassRepository.findById(classId).get());
                return studentRepository.save(student);
            });
            return studentRepository.findById(studentId).get().getStudentName() +" canceled class: " + studentClassRepository.findById(classId).get().getClassName();
        } else {
            return "You are not enrolled to this class.";
        }

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
