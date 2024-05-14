package com.spring.calculator.repository;

import com.spring.calculator.model.Number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NumberRepository extends JpaRepository<Number, Integer> {
    @Query("SELECT n FROM Number n WHERE n.id = :id")
    Number findById(int id);

    @Query("SELECT n FROM Number n LEFT JOIN n.user u WHERE u.id = :id")
    List<Number> findUserOperationsById(int id);
}
