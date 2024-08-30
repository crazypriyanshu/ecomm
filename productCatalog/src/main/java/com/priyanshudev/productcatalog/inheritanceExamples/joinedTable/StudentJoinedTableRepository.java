package com.priyanshudev.productcatalog.inheritanceExamples.joinedTable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJoinedTableRepository extends JpaRepository<Student_jt, Integer> {
    Student_jt findStudent_jtById(int id);
    Student_jt save(Student_jt student_jt);
}
