package com.syntaxnow.mock.repository;

import com.syntaxnow.mock.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
//@RepositoryRestResource(path = "projects", collectionResourceRel = "projects")
public interface ProjectRepo extends JpaRepository<Project, String> {
      List<Project>findByServiceStatusOrderById(String ServiceStatus);

    Page<Project> findAll(Pageable pageable);

}
