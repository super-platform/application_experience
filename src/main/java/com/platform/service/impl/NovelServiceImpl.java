package com.platform.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.common.dto.ResponseWithBody;
import com.platform.common.dto.novel.NovelCreateDTO;
import com.platform.common.dto.novel.NovelDTO;
import com.platform.common.dto.request.PageableRequest;
import com.platform.common.dto.response.PageResponse;
import com.platform.service.NovelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@Slf4j
public class NovelServiceImpl extends BaseServiceImpl implements NovelService {

    @Value(value = "${url.novel-backend-service.novel.findById}")
    private String findByIdUrl;


    @Value(value = "${url.novel-backend-service.novel.create}")
    private String createUrl;

    @Value(value = "${url.novel-backend-service.novel.paginate}")
    private String paginateUrl;

    public NovelServiceImpl(RestTemplate restTemplateAPI, ObjectMapper objectMapper) {
        super(restTemplateAPI, objectMapper);
    }

    @Override
    public ResponseWithBody<NovelDTO> findById(Long id) {
        return getResultCall(null, String.format(findByIdUrl, id), HttpMethod.GET, new HashMap<>(), NovelDTO.class);
    }

    @Override
    public ResponseWithBody<NovelDTO> save(NovelCreateDTO novelCreateDTO) {
        return getResultCall(novelCreateDTO, createUrl, HttpMethod.POST, new HashMap<>(), NovelDTO.class);
    }

    @Override
    public ResponseWithBody<PageResponse<NovelDTO>> paginate(PageableRequest pageableRequest) {
        return getResultCall(pageableRequest, paginateUrl, HttpMethod.POST, new HashMap<>(), PageResponse.class);
    }
}
