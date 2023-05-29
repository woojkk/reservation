package domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserVo {

    private Long getId;
    private String getEmail;
    private UserType userType;
}
