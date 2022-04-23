package com.example.savetogpay.repository;

import com.example.savetogpay.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateDao extends JpaRepository<TemplateEntity, Long> {
    List<TemplateEntity> findAllByApiKey(String apiKey);
}
