package com.company.localApp.mugshots;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MugshotRepo extends JpaRepository<Mugshot, Long> {
    static Optional<Mugshot> findMugshotById(Long id) {
return null;
    }
//sample query: SELECT * FROM student WHERE email = ?
    //    @Query("SELECT s FROM Student s WHERE s.email=?1") //Student is our student class
    //    Optional<Student> findStudentByEmail(String email);}
}
