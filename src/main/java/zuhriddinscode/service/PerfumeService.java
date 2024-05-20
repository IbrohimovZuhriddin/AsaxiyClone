package zuhriddinscode.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zuhriddinscode.domain.dto.request.SearchRequest;
import zuhriddinscode.domain.model.Perfume;

import java.util.List;

public interface PerfumeService {

    Perfume getPerfumeById(Long perfumeId);

    List<Perfume> getPopularPerfumes();

    Page<Perfume> getPerfumesByFilterParams(SearchRequest searchRequest, Pageable pageable);

    Page<Perfume> searchPerfumes(SearchRequest searchRequest, Pageable pageable);
}
