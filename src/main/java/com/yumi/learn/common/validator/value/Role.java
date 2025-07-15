package com.yumi.learn.common.validator.value;

import jakarta.validation.constraints.NotBlank;

public record Role(@NotBlank(message = "角色名称不能为空") String roleName) {
}
