package e.infra.common;

public enum CommonCode {

    SUCCESS("000", "Thành công"),
    ERROR("001", "Lỗi không xác định"),
    VALIDATION_ERROR("002", "Lỗi validate dữ liệu đầu vào"),
    UNAUTHORIZED("003", "Không có quyền truy cập"),
    FORBIDDEN("004", "Bị từ chối quyền truy cập"),
    NOT_FOUND("005", "Không tìm thấy dữ liệu"),
    CONFLICT("006", "Xung đột dữ liệu"),
    INTERNAL_ERROR("007", "Lỗi hệ thống nội bộ"),
    BAD_REQUEST("008", "Yêu cầu không hợp lệ"),
    TIMEOUT("009", "Quá thời gian xử lý"),
    CREATED("010", "Tạo mới thành công"),
    UPDATED("011", "Cập nhật thành công"),
    DELETED("012", "Xóa thành công");

    private final String code;
    private final String message;

    CommonCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

