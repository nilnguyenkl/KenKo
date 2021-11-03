package com.example.kenko.Teacher.Manage;

import com.example.kenko.models.StudentModel;

public interface IClickItemStudentListener {
    void deleteItemStudent(StudentModel student);
    void detailItemStudent(StudentModel student);
}
