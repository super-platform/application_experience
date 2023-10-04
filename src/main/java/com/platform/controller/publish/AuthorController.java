package com.platform.controller.publish;

import com.platform.common.dto.ResponseWithBody;
import com.platform.common.dto.author.AuthorCreateDTO;
import com.platform.common.dto.author.AuthorDTO;
import com.platform.common.dto.request.PageableRequest;
import com.platform.common.dto.response.PageResponse;
import com.platform.service.AuthorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authors")
@Tag(name = "Author", description = "Author Resources API")
@Slf4j
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicates that the request was executed successfully"),
            @ApiResponse(responseCode = "500", description = "Indicates that there is a server error occurs during executing the request")})
    @PostMapping(value = "/create", consumes = "application/json",produces = "application/json")
    public ResponseWithBody<AuthorDTO> create(@RequestBody AuthorCreateDTO authorCreateDTO){
        return authorService.save(authorCreateDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicates that the request was executed successfully"),
            @ApiResponse(responseCode = "500", description = "Indicates that there is a server error occurs during executing the request")})
    @PostMapping(value = "/paginate", consumes = "application/json",produces = "application/json")
    public ResponseWithBody<PageResponse<AuthorDTO>> paginate(@RequestBody PageableRequest pageableRequest){

        return authorService.paginate(pageableRequest);
    }
}