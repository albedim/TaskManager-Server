package me.albedim.taskmanager.controller;


import me.albedim.taskmanager.model.entity.Contribution;
import me.albedim.taskmanager.model.entity.Task;
import me.albedim.taskmanager.service.ContributionService;
import me.albedim.taskmanager.service.TaskService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 05/11/22
 * Created at: 00:18
 * Version: 1.0.0
 * Description: This is the class for the task controller
 */

@RestController
@RequestMapping("/api/task")
public class TaskController
{
    private TaskService taskService;

    private ContributionService contributionService;

    public TaskController(TaskService taskService, ContributionService contributionService)
    {
        this.contributionService = contributionService;
        this.taskService = taskService;
    }

    @PostMapping("/add")
    @CrossOrigin
    public HashMap addTask(@RequestBody @NotNull HashMap request)
    {
        return this.taskService.createTask(
                Long.parseLong(request.get("userId").toString()),
                request.get("name").toString(),
                LocalDate.parse(request.get("completedDate").toString())
        );
    }

    @DeleteMapping("/delete")
    @CrossOrigin
    public HashMap removeTask(@RequestParam("id") Long id)
    {
        return this.taskService.removeTask(id);
    }

    @PutMapping("/change")
    @CrossOrigin
    public HashMap changeData(@RequestBody HashMap request)
    {
        return this.taskService.changeData(
                request.get("status").toString(),
                LocalDate.parse(request.get("completedDate").toString()),
                Long.parseLong(request.get("id").toString())
        );
    }

    @GetMapping("get")
    @CrossOrigin
    public List<HashMap> getTasks(@RequestParam("id") Long id, @RequestParam("status") String status)
    {
        return this.taskService.getAllTasks(id, status);
    }

}
