package com.wipro.vehiclerental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wipro.vehiclerental.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, String> {

    // WHERE
    @Query(value = "SELECT * FROM branch WHERE city = :city",
           nativeQuery = true)
    List<Branch> findBranchesByCity(@Param("city") String city);

    // LIKE  is used for pattern matching.
    @Query(value = "SELECT * FROM branch WHERE branch_name LIKE :name",
           nativeQuery = true)
    List<Branch> findBranchesByName(@Param("name") String name);

    // ORDER BY according to branch name By default, ORDER BY means ascending order (ASC).
    @Query(value = "SELECT * FROM branch ORDER BY branch_name",
           nativeQuery = true)
    List<Branch> findBranchesOrderByName();

    // GROUP BY
    @Query(value = "SELECT city, COUNT(*) FROM branch GROUP BY city",
           nativeQuery = true)
    List<Object[]> countBranchesByCity();

    // Aggregate
    @Query(value = "SELECT COUNT(*) FROM branch",
           nativeQuery = true)
    Long countBranches();
}