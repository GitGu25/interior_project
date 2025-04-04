package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.dto.ProjectDto;

@Mapper
public interface ProjectMapper {

    // 전체 시공 사례 개수 조회
    int getTotalProjectCount();

    // 페이징된 시공 사례 목록 조회
    List<ProjectDto> getProjects(@Param("offset") int offset, @Param("limit") int limit);
}
