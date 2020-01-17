package studentNaloga.studentClass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import studentNaloga.student.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Student_class")
public class StudentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="class_id")
    private Integer classId;
    @Column(name="class_name")
    private String className;
    @Column(name="lecturer_name")
    private String lecturerName;
    @Column(name="student_id")
    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledClassId", fetch = FetchType.LAZY)
    private Set<Student> studentId = new HashSet<>();
    private String time;
    private String semester;

    public StudentClass(){}

    public StudentClass(Integer classId, String className, String lecturerName, Set<Student> studentId) {
        super();
        this.classId = classId;
        this.className = className;
        this.lecturerName = lecturerName;
        this.studentId = studentId;
    }


    public Integer getClassId(){
        return classId;
    }
    public void setClassId(Integer classId){
        this.classId = classId;
    }

    public String getClassName(){
        return className;
    }
    public void setClassName(String className){
        this.className = className;
    }

    public String getLecturerName(){
        return lecturerName;
    }
    public void setLecturerName(String lecturerName){
        this.lecturerName = lecturerName;
    }

    public Set<Student> getStudentId(){
        return studentId;
    }
    public void setStudentId(Set<Student> day){
        this.studentId = studentId;
    }

    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }

    public String getSemester(){
        return semester;
    }
    public void setSemester(String semester){
        this.semester = semester;
    }

}