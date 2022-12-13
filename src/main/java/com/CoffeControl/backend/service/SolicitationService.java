package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.SolicitationDto;
import com.CoffeControl.backend.form.SolicitationPostForm;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface SolicitationService {
    Page<SolicitationDto> list(Integer page, Integer limit);

    ResponseEntity<SolicitationDto> register(SolicitationPostForm form, UriComponentsBuilder uriBuilder) throws Exception;
}
