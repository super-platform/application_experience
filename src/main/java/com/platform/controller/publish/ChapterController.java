package com.platform.controller.publish;

import com.platform.common.dto.ResponseWithBody;
import com.platform.common.dto.chapter.ChapterCreateDTO;
import com.platform.common.dto.chapter.ChapterDTO;
import com.platform.common.dto.request.PageableRequest;
import com.platform.common.dto.response.PageResponse;
import com.platform.service.ChapterService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chapters")
@Tag(name = "Chapter", description = "Chapter Resources API")
@Slf4j
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterService chapterService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicates that the request was executed successfully"),
            @ApiResponse(responseCode = "500", description = "Indicates that there is a server error occurs during executing the request")})
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseWithBody<ChapterDTO> findById(@PathVariable Long id){
        return chapterService.findById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicates that the request was executed successfully"),
            @ApiResponse(responseCode = "500", description = "Indicates that there is a server error occurs during executing the request")})
    @PostMapping(value = "/create", consumes = "application/json",produces = "application/json")
    @Secured("ROLE_USER")
    public ResponseWithBody<ChapterDTO> create(@RequestBody ChapterCreateDTO chapterCreateDTO){
        return chapterService.save(chapterCreateDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicates that the request was executed successfully"),
            @ApiResponse(responseCode = "500", description = "Indicates that there is a server error occurs during executing the request")})
    @PostMapping(value = "/paginate", consumes = "application/json",produces = "application/json")
    public ResponseWithBody<PageResponse<ChapterDTO>> paginate(@RequestBody PageableRequest pageableRequest){

        return chapterService.paginate(pageableRequest);
    }
}
