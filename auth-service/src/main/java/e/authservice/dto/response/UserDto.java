package e.authservice.dto.response;

import e.authservice.entity.User;
import lombok.Data;

@Data
public class UserDto {

    private String username;

    private String phoneNumber;

    private String address;

    private String email;

    private Integer status;

    private String fullName;

    private Boolean gender;

    private String role;

    public static UserDto fromEntity(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setStatus(user.getStatus());
        userDto.setFullName(user.getFullName());
        userDto.setGender(user.getGender());
        if (user.getRole() != null) {
            userDto.setRole(user.getRole().getRole());
        } else {
            userDto.setRole(null);
        }
        return userDto;
    }

}
