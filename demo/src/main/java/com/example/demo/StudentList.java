package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentList implements interfameStudent {
    private static final Map<Integer,Student> studentMap;
    static {
        studentMap = new HashMap<>();
        studentMap.put(1, new Student(1, "Thang", "Thang@codegym.vn", 85322270,23));
        studentMap.put(2, new Student(2, "Son", "Son@codegym.vn", 123456789,19));
        studentMap.put(4, new Student(4, "Anh", "Anh@codegym.vn", 987654321,20));
        studentMap.put(5, new Student(5, "Tu", "Tu@codegym.vn", 189568541,16));
        studentMap.put(6, new Student(6, "Dat", "Dat@codegym.vn",654298526,24));
    }
    public Student findById(int id) {
        return studentMap.get(id);
    }
    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values());
    }
}
