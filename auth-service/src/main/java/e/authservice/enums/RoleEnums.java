package e.authservice.enums;

public enum RoleEnums {

    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    STAFF("STAFF");

    private final String code;

    RoleEnums(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static RoleEnums fromCode(String code) {
        for (RoleEnums role : RoleEnums.values()) {
            if (role.code.equalsIgnoreCase(code)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy role với code: " + code);
    }
}
