package umc.mission.global;

public record ApiResponse<T>(
        boolean isSuccess,
        String code,
        String message,
        T result
) {

    public static <T> ApiResponse<T> onFailure(String code, String message, T result) {
        return new ApiResponse<>(false, code, message, result);
    }
}
