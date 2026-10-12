package com.example.studentmanagement;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.StudentService;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository.clear();
    }

    @Test
    void addingValidStudentSucceeds() {
        Student saved = studentService.addStudent(new Student(null, "Hemagiri Raj", "CSE"));

        assertNotNull(saved.getId());
        assertEquals("Hemagiri Raj", saved.getName());
        assertEquals("CSE", saved.getDepartment());
    }

    @Test
    void addingBlankNameIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> studentService.addStudent(new Student(null, "   ", "CSE")));
    }

    @Test
    void retrievingMissingStudentReturnsEmpty() {
        Optional<Student> result = studentService.getStudentById(9999L);

        assertEquals(Optional.empty(), result);
    }
}
