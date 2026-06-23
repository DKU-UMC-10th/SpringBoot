package umc.mission.web.dto;

import java.util.List;

public record CursorPageResponse<T>(
        List<T> content,
        boolean hasNext,
        String nextCursor,
        int size
) {
}
