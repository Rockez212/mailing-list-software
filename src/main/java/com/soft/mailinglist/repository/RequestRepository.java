package com.soft.mailinglist.repository;

import com.soft.mailinglist.entity.Request;
import com.soft.mailinglist.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

    @Query("SELECT r FROM Request r WHERE r.status = :status")
    List<Request> findByStatus(@Param("status") RequestStatus status);

}
