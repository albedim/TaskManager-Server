package me.albedim.taskmanager.model.entity;

import me.albedim.taskmanager.utils.Util;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 05/11/22
 * Created at: 00:18
 * Version: 1.0.0
 * Description: This is the class for the task entity
 */

@Entity
@Table(name = "tasks")
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate completedDate;

    private Task() { }

    public Task(Long ownerId, String name, LocalDate completedDate)
    {
        setOwnerId(ownerId);
        setName(name);
        setStatus(Util.TODO);
        setCreatedAt(LocalDate.now());
        setCompletedDate(completedDate);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
    public LocalDate getCompletedDate() { return completedDate; }
    public void setCompletedDate(LocalDate completedDate) { this.completedDate = completedDate; }

    public HashMap toJson()
    {
        HashMap object = new HashMap();
        object.put("id", this.getId());
        object.put("ownerId", this.getOwnerId());
        object.put("name", this.getName());
        object.put("status", this.getStatus());
        object.put("createdAt", this.getCreatedAt());
        object.put("completedDate", this.getCompletedDate());
        return object;
    }


}
