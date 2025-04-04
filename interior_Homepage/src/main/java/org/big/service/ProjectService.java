package org.big.service;

import java.util.List;

import org.big.dto.ProjectDto;
import org.big.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    // 전체 시공 사례 개수 조회
    public int getTotalProjectCount() {
        return projectMapper.getTotalProjectCount();
    }

    // 페이징된 시공 사례 목록 조회
    public List<ProjectDto> getProjects(int offset, int limit) {
        return projectMapper.getProjects(offset, limit);
    }
}
