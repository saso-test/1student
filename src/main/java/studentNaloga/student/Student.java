package studentNaloga.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import studentNaloga.studentClass.StudentClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Student")
public class Student implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="student_id")
    private Integer studentId;
    @Column (name="student_name")
    private String studentName;
    @Column (name="username")
    private String userName;
    @Column (name="password")
    private String password;
    @Column (name="role")
    private String role;
    @OneToMany(targetEntity=Student.class, fetch=FetchType.EAGER)
    Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Student_enrolled_to_class", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
    private Set<StudentClass> enrolledClassId = new HashSet<>();;

    public Student() {
    };

    public Student(Integer studentId, String studentName, String userName, Set<StudentClass> enrolledClassId) {
        super();
        this.studentId = studentId;
        this.studentName = studentName;
        this.userName = userName;
        this.enrolledClassId = enrolledClassId;
    }

    public Student(Student studentByUserName) {
        this.userName = studentByUserName.getUserName();
        this.password = studentByUserName.getPassword();
    }

    public Integer getStudentId(){
        return studentId;
    }
    public void setStudentId(Integer studentId){
        this.studentId = studentId;
    }

    public String getStudentName(){
        return studentName;
    }
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){ this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Set<StudentClass> getEnrolledClassID(){
        return enrolledClassId;
    }
    public void setEnrolledClassID(Set<StudentClass> enrolledClassId){
        this.enrolledClassId = enrolledClassId;
    }

    @Override
    public String toString() {
        return String.format(
                "Student [studentId=%s, studentName=%s, userName=%s, password=%s, role=%s, enrolledClassId=%s]",
                studentId, studentName, userName, password, role, enrolledClassId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
