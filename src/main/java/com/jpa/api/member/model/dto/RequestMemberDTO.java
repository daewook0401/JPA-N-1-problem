package com.jpa.api.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestMemberDTO {

    @NotBlank(message = "username은 필수입니다.")
    private String username;

    @NotBlank(message = "email은 필수입니다.")
    private String email;

    @NotBlank(message = "password는 필수입니다.")
    private String password;

    @NotBlank(message = "name은 필수입니다.")
    private String name;

    @NotBlank(message = "phoneNumber는 필수입니다.")
    private String phoneNumber;

}
