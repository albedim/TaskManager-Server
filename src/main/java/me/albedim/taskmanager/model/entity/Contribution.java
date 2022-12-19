package me.albedim.taskmanager.model.entity;

import javax.persistence.*;
import java.util.HashMap;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 05/11/22
 * Created at: 00:54
 * Version: 1.0.0
 * Description: This is the class for the contributor entity
 */

@Entity
@Table(name = "contributions")
public class Contribution
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long taskId;

    @Column(nullable = false)
    private Long userId;

    private Contribution() { }

    public Contribution(Long userId, Long taskId)
    {
        setUserId(userId);
        setTaskId(taskId);
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

}
