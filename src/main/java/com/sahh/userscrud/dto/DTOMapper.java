package com.sahh.userscrud.dto;

import org.springframework.stereotype.Component;

import com.sahh.userscrud.appusers.AppUsers;

@Component
public class DTOMapper {
    public AppUserDTO toDto(AppUsers user) {
        return AppUserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getAppUserRole())
                .delFlag(user.getDelFlag())
                .build();
    }
}
