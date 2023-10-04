package com.platform.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.common.dto.ResponseWithBody;
import com.platform.common.dto.chapter.ChapterCreateDTO;
import com.platform.common.dto.chapter.ChapterDTO;
import com.platform.common.dto.request.PageableRequest;
import com.platform.common.dto.response.PageResponse;
import com.platform.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@Slf4j
public class ChapterServiceImpl extends BaseServiceImpl implements ChapterService {

    @Value(value = "${url.novel-backend-service.chapter.create}")
    private String createUrl;

    @Value(value = "${url.novel-backend-service.chapter.paginate}")
    private String paginateUrl;

    public ChapterServiceImpl(RestTemplate restTemplateAPI, ObjectMapper objectMapper) {
        super(restTemplateAPI, objectMapper);
    }


    @Override
    public ResponseWithBody<ChapterDTO> save(ChapterCreateDTO chapterCreateDTO) {
        return getResultCall(chapterCreateDTO, createUrl, HttpMethod.POST, new HashMap<>(), ChapterDTO.class);
    }

    @Override
    public ResponseWithBody<PageResponse<ChapterDTO>> paginate(PageableRequest pageableRequest) {
        return getResultCall(pageableRequest, paginateUrl, HttpMethod.POST, new HashMap<>(), PageResponse.class);
    }
}
