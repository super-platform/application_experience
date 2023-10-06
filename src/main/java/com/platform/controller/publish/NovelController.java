package com.platform.controller.publish;

import com.platform.common.dto.ResponseWithBody;
import com.platform.common.dto.chapter.ChapterDTO;
import com.platform.common.dto.novel.NovelCreateDTO;
import com.platform.common.dto.novel.NovelDTO;
import com.platform.common.dto.request.PageableRequest;
import com.platform.common.dto.response.PageResponse;
import com.platform.common.utils.ResponseUtil;
import com.platform.service.NovelService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.platform.common.constant.CommonConstants.TRACE_ID;

@RestController
@RequestMapping("api/v1/novels")
@Tag(name = "Novel", description = "Novel Resources API")
@Slf4j
@RequiredArgsConstructor
public class NovelController {
    private final NovelService novelService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicates that the request was executed successfully"),
            @ApiResponse(responseCode = "500", description = "Indicates that there is a server error occurs during executing the request")})
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseWithBody<NovelDTO> findById(@PathVariable Long id){
        return novelService.findById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicates that the request was executed successfully"),
            @ApiResponse(responseCode = "500", description = "Indicates that there is a server error occurs during executing the request")})
    @PostMapping(value = "/create", consumes = "application/json",produces = "application/json")
    public ResponseWithBody<NovelDTO> create(@RequestBody NovelCreateDTO novelCreateDTO){
        return novelService.save(novelCreateDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicates that the request was executed successfully"),
            @ApiResponse(responseCode = "500", description = "Indicates that there is a server error occurs during executing the request")})
    @PostMapping(value = "/paginate", consumes = "application/json",produces = "application/json")
    public ResponseWithBody<PageResponse<NovelDTO>> paginate(@RequestBody PageableRequest pageableRequest){

        return novelService.paginate(pageableRequest);
    }
}
