package com.platform.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.common.dto.ResponseWithBody;
import com.platform.common.dto.author.AuthorCreateDTO;
import com.platform.common.dto.author.AuthorDTO;
import com.platform.common.dto.request.PageableRequest;
import com.platform.common.dto.response.PageResponse;
import com.platform.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@Slf4j
public class AuthorServiceImpl extends BaseServiceImpl implements AuthorService {

    @Value(value = "${url.novel-backend-service.author.findById}")
    private String findByIdUrl;

    @Value(value = "${url.novel-backend-service.author.create}")
    private String createUrl;

    @Value(value = "${url.novel-backend-service.author.paginate}")
    private String paginateUrl;

    public AuthorServiceImpl(RestTemplate restTemplateAPI, ObjectMapper objectMapper) {
        super(restTemplateAPI, objectMapper);
    }

    @Override
    public ResponseWithBody<AuthorDTO> findById(Long id) {
        return getResultCall(null, String.format(findByIdUrl, id), HttpMethod.GET, new HashMap<>(), AuthorDTO.class);
    }

    @Override
    public ResponseWithBody<AuthorDTO> save(AuthorCreateDTO authorCreateDTO) {
        return getResultCall(authorCreateDTO, createUrl, HttpMethod.POST, new HashMap<>(), AuthorDTO.class);
    }

    @Override
    public ResponseWithBody<PageResponse<AuthorDTO>> paginate(PageableRequest pageableRequest) {
        return getResultCall(pageableRequest, paginateUrl, HttpMethod.POST, new HashMap<>(), PageResponse.class);
    }
}
