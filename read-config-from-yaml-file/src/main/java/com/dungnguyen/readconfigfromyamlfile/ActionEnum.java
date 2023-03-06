package com.dungnguyen.readconfigfromyamlfile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActionEnum {
    INSERT("1", "Thêm mới"),
    UPDATE("2", "Cập nhật"),
    DELETE("3", "Xóa");

    private final String id;
    private final String actionName;
}
