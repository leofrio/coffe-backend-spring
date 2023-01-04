package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.SolicitationDetailedDto;
import com.CoffeControl.backend.dto.SolicitationDto;
import com.CoffeControl.backend.form.SolicitationPostForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface SolicitationService {
    Page<SolicitationDto> list(Integer page,Integer limit,String name, String description,Boolean enabled, String password, String productName,  Integer askedAmount,  String username);
    SolicitationDto register(SolicitationPostForm form) throws Exception;

    Boolean checkIfFinished(Integer solicitationId) throws Exception;
    SolicitationDetailedDto getSpecificSolicitation(Integer id) throws Exception;
}
