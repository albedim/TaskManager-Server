package me.albedim.taskmanager.service;

import me.albedim.taskmanager.model.entity.Task;
import me.albedim.taskmanager.model.entity.User;
import me.albedim.taskmanager.model.repository.TaskRepository;
import me.albedim.taskmanager.utils.Util;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 05/11/22
 * Created at: 00:18
 * Version: 1.0.0
 * Description: This is the class for the task service
 */


@Service
public class TaskService
{
    private TaskRepository taskRepository;

    private ContributionService contributionService;

    private UserService userService;


    public TaskService(TaskRepository taskRepository, @Lazy ContributionService contributionService, UserService userService)
    {
        this.userService = userService;
        this.contributionService = contributionService;
        this.taskRepository = taskRepository;
    }

    public HashMap createTask(Long userId, String name, LocalDate completedDate)
    {
        Task task = new Task(userId, name, completedDate);
        this.taskRepository.save(task);
        return Util.createResponse(true, null);
    }

    public boolean isOwnTask(Long userId, Long id) { return this.taskRepository.isOwnTask(userId, id) > 0; }

    public HashMap removeTask(Long id)
    {
        this.contributionService.deleteTaskContributions(id);
        this.taskRepository.deleteById(id);
        return Util.createResponse(true, null);
    }

    public HashMap changeData(String status, LocalDate completedDate, Long id)
    {
        this.taskRepository.changeData(status, completedDate, id);
        return Util.createResponse(true, null);
    }

    // This gets all the tasks of a user, the own tasks and the contributors
    // ones
    public List<HashMap> getAllTasks(Long userId, String status)
    {
        // Getter ownTasks
        List<Task> ownTasks = this.taskRepository.getTasks(userId, status);
        // Getter contributorsTask
        List<Task> contributorTasks = this.taskRepository.getContributorTasks(userId, status);
        // This is a simple array which will contain the ownTasks and the contributorTasks
        List<HashMap> allTasks = new ArrayList<>();
        // This for puts in the allTasks array the ownTasks
        for (Task ownTask : ownTasks) allTasks.add(ownTask.toJson());
        // This for puts the contributorTasks in the allTasks
        // but it also add a param
        for(Task contributorTask : contributorTasks){
            HashMap newContributorTask = contributorTask.toJson();
            newContributorTask.put("ownerNickname",
                    this.userService.getData(contributorTask.getOwnerId()).getNickname());
            allTasks.add(newContributorTask);
        }
        return allTasks;
    }

}
