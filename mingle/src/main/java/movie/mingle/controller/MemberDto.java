package movie.mingle.controller;

import lombok.Data;

@Data
public class MemberDto {

    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}