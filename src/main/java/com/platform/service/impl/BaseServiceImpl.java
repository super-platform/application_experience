package com.platform.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.common.dto.ResponseStatus;
import com.platform.common.dto.ResponseWithBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class BaseServiceImpl {
    private final RestTemplate restTemplateAPI;
    private final ObjectMapper objectMapper;

    private String getResultCall(Object body, String url, HttpMethod method){
        return null;
    }

    private String getResultCall(Object body, String url, HttpMethod method, Map<String, ?> uriVariables){
        ResponseEntity<String> resultString = null;
        try {
            // Header
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            // Call
            resultString = restTemplateAPI.exchange(url,method,new HttpEntity<>(body, headers), String.class, uriVariables);

        } catch (Exception e) {
            log.error("Error getResultCall - url {} - method {} , body {} - uriVariables {} - message {}", url, method, body, uriVariables, e.getMessage());
        }

        return resultString.getBody();
    }

    protected Map<String, Object> getMapResultCall(Object body, String url, HttpMethod method, Map<String, ?> uriVariables){
        ResponseEntity<Map> resultMap= null;
        try {
            // Header
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            // Call
            resultMap = restTemplateAPI.exchange(url,method,new HttpEntity<>(body, headers), Map.class, uriVariables);

        } catch (Exception e) {
            log.error("Error getResultCall - url {} - method {} , body {} - uriVariables {} - message {}", url, method, body, uriVariables, e.getMessage());
        }

        return resultMap.getBody();
    }

    protected ResponseWithBody getResultCall(Object body, String url, HttpMethod method, Map<String, ?> uriVariables, Class<?> clazz){
        ResponseWithBody result = new ResponseWithBody<>();

        Map<String, Object> responseMap = getMapResultCall(body,url,method,uriVariables);

        result.setStatus(objectMapper.convertValue(responseMap.get("status"), ResponseStatus.class));
        if (ObjectUtils.isNotEmpty(responseMap.get("body"))){
            result.setBody(
                    objectMapper.convertValue(responseMap.get("body"), clazz)
            );
        }

        return result;
    }
}
