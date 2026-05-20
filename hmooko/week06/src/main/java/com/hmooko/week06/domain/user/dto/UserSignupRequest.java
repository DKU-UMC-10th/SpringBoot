package com.hmooko.week06.domain.user.dto;

import com.hmooko.week06.domain.user.domain.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignupRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotNull(message = "성별은 필수입니다.")
    private Gender sex;

    @NotNull(message = "생년월일은 필수입니다.")
    private LocalDate birth;

    @NotBlank(message = "주소는 필수입니다.")
    private String address;
}
