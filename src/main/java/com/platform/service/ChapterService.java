package com.platform.service;

import com.platform.common.dto.ResponseWithBody;
import com.platform.common.dto.chapter.ChapterCreateDTO;
import com.platform.common.dto.chapter.ChapterDTO;
import com.platform.common.dto.request.PageableRequest;
import com.platform.common.dto.response.PageResponse;

public interface ChapterService {
    ResponseWithBody<ChapterDTO> save(ChapterCreateDTO chapterCreateDTO);
    ResponseWithBody<PageResponse<ChapterDTO>> paginate(PageableRequest pageableRequest);
}
