package e.infra.common;

public final class HttpStatusCodes {

    private HttpStatusCodes() {
        // Không cho khởi tạo instance class này
    }

    // 2xx: Thành công
    public static final int OK = 200;                         // Yêu cầu thành công
    public static final int CREATED = 201;                    // Đã tạo thành công tài nguyên mới
    public static final int NO_CONTENT = 204;                 // Thành công, không có nội dung trả về

    // 4xx: Lỗi phía client
    public static final int BAD_REQUEST = 400;                // Yêu cầu không hợp lệ
    public static final int UNAUTHORIZED = 401;               // Chưa xác thực (chưa đăng nhập hoặc token không hợp lệ)
    public static final int FORBIDDEN = 403;                  // Đã xác thực nhưng không có quyền truy cập
    public static final int NOT_FOUND = 404;                   // Không tìm thấy tài nguyên
    public static final int CONFLICT = 409;                    // Xung đột dữ liệu (ví dụ: đăng ký trùng)

    // 5xx: Lỗi phía server
    public static final int INTERNAL_SERVER_ERROR = 500;      // Lỗi máy chủ, xử lý thất bại
    public static final int SERVICE_UNAVAILABLE = 503;        // Dịch vụ không sẵn sàng, thường do bảo trì hoặc quá tải

}
