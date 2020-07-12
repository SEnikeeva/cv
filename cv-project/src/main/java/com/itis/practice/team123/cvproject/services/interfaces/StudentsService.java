package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.models.Student;

import java.util.List;

public interface StudentsService {

    Student getStudentById(Long id);
    // TODO сделать метод для получения студентов по фильтрам
    List<Student> getStudentsByTag(List<String> tags_name);
}
