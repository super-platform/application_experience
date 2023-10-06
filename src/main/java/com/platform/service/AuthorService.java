package com.platform.service;

import com.platform.common.dto.ResponseWithBody;
import com.platform.common.dto.author.AuthorCreateDTO;
import com.platform.common.dto.author.AuthorDTO;
import com.platform.common.dto.request.PageableRequest;
import com.platform.common.dto.response.PageResponse;

public interface AuthorService {
    ResponseWithBody<AuthorDTO> findById(Long id);
    ResponseWithBody<AuthorDTO> save(AuthorCreateDTO authorCreateDTO);
    ResponseWithBody<PageResponse<AuthorDTO>> paginate(PageableRequest pageableRequest);
}
