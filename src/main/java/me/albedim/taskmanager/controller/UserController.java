package me.albedim.taskmanager.controller;


import me.albedim.taskmanager.model.entity.User;
import me.albedim.taskmanager.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 04/11/22
 * Created at: 22:08
 * Version: 1.0.0
 * Description: This is the class for the user controller
 */

@RestController
@RequestMapping("/api/user")
public class UserController
{
    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/signin")
    @CrossOrigin
    public HashMap signin(@RequestBody @NotNull HashMap request)
    {
        return this.userService.signIn(
                request.get("email").toString(),
                request.get("image").toString(),
                request.get("nickname").toString(),
                request.get("password").toString()
        );
    }

    @PostMapping("/login")
    @CrossOrigin
    public HashMap login(@RequestBody HashMap request)
    {
        return this.userService.login(
                request.get("email").toString(),
                request.get("password").toString()
        );
    }

    @GetMapping("/contributors")
    @CrossOrigin
    public List<User> getContributorsOfTask(@RequestParam("taskId") Long taskId)
    {
        return this.userService.getContributorsOfTask(taskId);
    }

    @GetMapping("/get")
    @CrossOrigin
    public User getData(@RequestParam("id") Long id)
    {
        return this.userService.getData(id);
    }

}
