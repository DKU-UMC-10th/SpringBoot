package umc.mission.web.dto;

import java.util.List;
import org.springframework.data.domain.Page;

public record OffsetPageResponse<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {

    public static <T> OffsetPageResponse<T> from(Page<T> page) {
        return new OffsetPageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }
}
