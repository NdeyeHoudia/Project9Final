package com.openclassrooms.projectJava9.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="service-risque", url="http://localhost:8086/risquePatient")

public interface RisqueClient {

    @GetMapping("/{id}/status")
    public String statusPatient(@PathVariable Integer id);

}
