package com.projectAPIREST.projectREST.repository;

import com.projectAPIREST.projectREST.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
