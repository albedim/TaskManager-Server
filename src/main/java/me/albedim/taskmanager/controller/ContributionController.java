package me.albedim.taskmanager.controller;


import me.albedim.taskmanager.model.entity.Contribution;
import me.albedim.taskmanager.model.entity.Task;
import me.albedim.taskmanager.service.ContributionService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import me.albedim.taskmanager.utils.Util;
import java.util.List;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 05/11/22
 * Created at: 00:54
 * Version: 1.0.0
 * Description: This is the class for the contributor controller
 */

@RestController
@RequestMapping(Util.URL + "/contribution")
public class ContributionController
{
    private ContributionService contributionService;

    public ContributionController(ContributionService contributionService)
    {
        this.contributionService = contributionService;
    }

    @PostMapping("/add")
    @CrossOrigin
    public HashMap addContribution(@RequestBody HashMap request)
    {
        return this.contributionService.addContribution(
                request.get("userNickname").toString(),
                Long.parseLong(request.get("taskId").toString())
        );
    }

    @DeleteMapping("/delete")
    @CrossOrigin
    public HashMap deleteContribution(@RequestParam("userId") Long userId, @RequestParam("taskId") Long taskId)
    {
        return this.contributionService.deleteContribution(userId, taskId);
    }

}
