package com.yumi.learn.service.param;

import com.yumi.learn.common.validator.value.Role;
import com.yumi.learn.controller.vo.validated.group.UserGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserParam {

    @NotBlank(message = "id不能为空", groups = UserGroup.UpdateGroup.class)
    private String id;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @Size(min=11,max=11,message = "手机号长度不符合要求")
    private String phone;
    @NotNull(message = "性别不能为空")
    private Integer sex;
    @NotNull(message = "角色信息不能为空")
    @Valid
    private Role role;
}
