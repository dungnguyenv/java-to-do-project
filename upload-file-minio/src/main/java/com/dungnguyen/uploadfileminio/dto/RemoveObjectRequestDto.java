package com.dungnguyen.uploadfileminio.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RemoveObjectRequestDto {
    private List<String> objectNameList;
}
