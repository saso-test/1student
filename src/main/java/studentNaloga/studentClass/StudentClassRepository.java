package studentNaloga.studentClass;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentClassRepository extends CrudRepository<StudentClass, Integer> {

    @Query (value = "SELECT class_id, class_name, lecturer_name, student_id, time, semester FROM Student_class WHERE class_name = :studentClassName", nativeQuery = true)

    List<StudentClass> findStudentClassesByName(String studentClassName);

}
