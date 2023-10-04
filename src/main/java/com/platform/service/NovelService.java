package com.platform.service;

import com.platform.common.dto.ResponseWithBody;
import com.platform.common.dto.novel.NovelCreateDTO;
import com.platform.common.dto.novel.NovelDTO;
import com.platform.common.dto.request.PageableRequest;
import com.platform.common.dto.response.PageResponse;

public interface NovelService {
    ResponseWithBody<NovelDTO> save(NovelCreateDTO novelCreateDTO);
    ResponseWithBody<PageResponse<NovelDTO>> paginate(PageableRequest pageableRequest);
}
