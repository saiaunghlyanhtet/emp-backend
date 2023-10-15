package com.sahh.userscrud.dto;

import com.sahh.userscrud.appusers.AppUserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private AppUserRole role;
    private int delFlag;
}
