package com.verma.sandeep.hospital.mate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.entity.PaymentDetail;

@Repository
public interface PaymentDeatilRepository extends JpaRepository<PaymentDetail, Long> {

}
