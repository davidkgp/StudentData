package com.app.trigger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
public class Trigger {
    public static void main(String[] args) {
        SpringApplication.run(Trigger.class, args);
    }
}

@AllArgsConstructor
@Getter
final class Address {

    private final String postCode;
    private final int houseNumber;
    private final String city;

}

@AllArgsConstructor
@Getter
final class Student {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final Address address;
    private final List<Student> labPartners;

}

@RestController
class MyController {

    @Autowired
    MyRepo repo;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return repo.getStudents().size() > 0 ? new ResponseEntity<>(repo.getStudents(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        return repo.getStudent(id) != null ? new ResponseEntity<>(repo.getStudent(id), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}

@Repository
class MyRepo {

    Map<String, Student> studentMap = null;

    public MyRepo() {
        this.studentMap = new HashMap<>();

        this.studentMap.put("123", new Student("123", "Tom", "Jerry", 24, new Address("1126TY", 23, "Utrecht"), Collections.emptyList()));
        this.studentMap.put("421", new Student("421", "Jerry", "McDonald", 23, new Address("1167RT", 29, "Utrecht"), Collections.emptyList()));
        this.studentMap.put("345", new Student("345", "Rob", "Schmuck", 20, new Address("9908JK", 13, "Utrecht"), Collections.emptyList()));
        this.studentMap.put("671", new Student("671", "Roy", "Field", 24, new Address("3421WE", 37, "Utrecht"), Collections.emptyList()));
        this.studentMap.put("231", new Student("231", "Don", "Blue", 29, new Address("3428WE", 47, "Utrecht"), Collections.emptyList()));

    }

    public List<Student> getStudents() {
        return studentMap != null ? new ArrayList<Student>(studentMap.values()) : Collections.emptyList();
    }

    public Student getStudent(String id) {
        return studentMap != null && !studentMap.isEmpty() && studentMap.get(id) != null ? studentMap.get(id) : null;
    }

}
