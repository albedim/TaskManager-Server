package me.albedim.taskmanager.service;

import me.albedim.taskmanager.model.entity.Contribution;
import me.albedim.taskmanager.model.repository.ContributionRepository;
import me.albedim.taskmanager.utils.Util;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 05/11/22
 * Created at: 00:54
 * Version: 1.0.0
 * Description: This is the class for the contributor service
 */

@Service
public class ContributionService
{
    private ContributionRepository contributionRepository;
    private UserService userService;
    private TaskService taskService;

    public ContributionService(ContributionRepository contributionRepository, UserService userService, TaskService taskService)
    {
        this.userService = userService;
        this.taskService = taskService;
        this.contributionRepository = contributionRepository;
    }

    public HashMap addContribution(String contributor, Long taskId)
    {
        Long userId = this.userService.getIdByName(contributor);
        // I check if the userId == null, because if it does. It means that the user doesn't exist
        if(userId != null){
            // I check if the userId is the owner of the task
            if(this.taskService.isOwnTask(userId, taskId))
                return Util.createResponse(false, Util.NOT_ENOUGH_PERMISSIONS, 403);
            Contribution contribution = new Contribution(userId, taskId);
            this.contributionRepository.save(contribution);
            return Util.createResponse(true, null);
        }else return Util.createResponse(false, Util.RESOURCE_DOESNT_EXIST, 404);
    }

    public HashMap deleteContribution(Long userId, Long taskId)
    {
        this.contributionRepository.deleteContribution(userId, taskId);
        return Util.createResponse(true, null);
    }

    public HashMap deleteTaskContributions(Long taskId)
    {
        this.contributionRepository.deleteTaskContributions(taskId);
        return Util.createResponse(true, null);
    }

}
