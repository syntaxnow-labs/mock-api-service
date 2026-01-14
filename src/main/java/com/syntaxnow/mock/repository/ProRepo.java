package com.syntaxnow.mock.repository;

import com.syntaxnow.mock.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProRepo extends JpaRepository<Profile,Long> {
}
