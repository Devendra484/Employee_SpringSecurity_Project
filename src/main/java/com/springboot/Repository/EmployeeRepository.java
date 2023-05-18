package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Modifying
	@Query("UPDATE Employee e SET e.id = e.id - 1 WHERE e.id > :idToDelete AND e.id <= :maxId")
	void updateIds(@Param("idToDelete") Long idToDelete, @Param("maxId") Long maxId);

	

}
