package com.syntaxnow.mock.controller;

import com.syntaxnow.mock.model.Employee;
import com.syntaxnow.mock.repository.EmpRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employees")
@AllArgsConstructor
public class EmpController {

    private final EmpRepo repository;

    @GetMapping
    public List<Employee> getAllEmp() {
        return  repository.findAll();
    }

//    @GetMapping("/{id}")
//     public Optional<Employee> getEmpById(@PathVariable Long id){
//        return repository.findById(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmpId(@PathVariable Long id){
        System.out.println(repository.findById(id));
        return repository.findById(id).map(ResponseEntity :: ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping
//  public List<Employee> create(@RequestBody Employee empolyee){
//         repository.save(empolyee);
//         return null;
//    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {

        System.out.println(employee);

//        if(employee.getProfile() != null){
//            employee.getProfile().setEmployee(employee);
//        }

        repository.save(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id , @RequestBody Employee employee){
        employee.setId(id);
        repository.save(employee);
                return ResponseEntity.ok(employee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Long id){
        repository.deleteById(id);
       return null ;
    }



}
