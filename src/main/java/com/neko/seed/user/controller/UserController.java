package com.neko.seed.user.controller;

import com.neko.seed.auth.annotation.Auth;
import com.neko.seed.auth.annotation.resolver.AuthMethodArgumentResolver;
import com.neko.seed.base.entity.Result;
import com.neko.seed.user.data.SignInData;
import com.neko.seed.user.data.SignUpData;
import com.neko.seed.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthMethodArgumentResolver tokenResolver;

    /**
     * 登陆接口
     */
    @PostMapping("/login")
    public Result signIn(@RequestBody @Validated SignInData data) {
        // 使用SpringValidation校验数据
        return new Result().success(userService.signIn(data));
    }

    /**
     * 页面权限接口
     */
    @GetMapping("/permission")
    public Result getPermission(@Auth(required = true) Object userId) {
        log.info(String.valueOf(userId));
        // 获取页面权限
        return new Result().success(userService.getPermission(Long.valueOf(String.valueOf(userId))));
    }

    /**
     * 注册接口
     */
    @PostMapping("/signUp")
    public Result signUp(@RequestBody @Validated SignUpData data) {
        // 使用SpringValidation校验数据
        userService.signUp(data);
        return new Result().success();
    }

    /**
     * 查看当前用户的Id
     */
    @GetMapping
    public Result get(@Auth(required = false) Long userId) {
        // 该接口可以不登陆，未登陆时返回的结果为空，登陆时会返回当前登陆用户的Id
        return new Result().success(userId);
    }

    /**
     * 根据Id查看单个用户的信息
     */
    @GetMapping("/{userId}")
    public Result get(@PathVariable("userId") long userId) {
        return new Result().success(userService.getById(userId));
    }

    /**
     * 查看用户列表
     */
    @GetMapping("/list")
    public Result list(@Auth Long userId) {
        // 该接口需要检测用户是否登陆
        return new Result().success(userService.list());
    }
}
