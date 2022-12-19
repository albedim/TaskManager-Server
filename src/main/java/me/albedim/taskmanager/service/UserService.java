package me.albedim.taskmanager.service;

import me.albedim.taskmanager.model.entity.User;
import me.albedim.taskmanager.model.repository.UserRepository;
import me.albedim.taskmanager.utils.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 04/11/22
 * Created at: 22:08
 * Version: 1.0.0
 * Description: This is the class for the user service
 */

@Service
public class UserService
{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public HashMap signIn(String email, String image, String nickname, String password) {
        if (this.userRepository.existsByEmail(email) < 1 &&
                this.userRepository.existsByNickname(nickname) < 1){
            User user = new User(image, nickname, email, password);
            this.userRepository.save(user);
            return Util.createResponse(true, null, 200);
        }else return Util.createResponse(false, Util.RESOURCE_ALREADY_EXISTS, 403);
    }

    public List<User> getContributorsOfTask(Long taskId)
    {
        return this.userRepository.getContributorsOfTask(taskId);
    }
    public Long getIdByName(String nickname)
    {
        return this.userRepository.getIdByName(nickname);
    }

    public HashMap login(String email, String password)
    {
        User user = this.userRepository.login(email, password);
        if(user != null)
            return Util.createResponse(true, String.valueOf(user.getId()));
        else return Util.createResponse(false, Util.RESOURCE_DOESNT_EXIST, 404);
    }

    public User getData(Long id) { return this.userRepository.getData(id); }

}
