package me.albedim.taskmanager.model.repository;

import me.albedim.taskmanager.model.entity.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 05/11/22
 * Created at: 00:18
 * Version: 1.0.0
 * Description: This is the class for the task repository
 */

public interface TaskRepository extends CrudRepository<Task, Long>
{
    @Override
    <S extends Task> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Query(value = "SELECT * FROM tasks WHERE owner_id = ? AND status = ?", nativeQuery = true)
    List<Task> getTasks(Long id, String status);

    @Query(value = "SELECT tasks.id, tasks.completed_date, tasks.created_at, tasks.name, tasks.status, tasks.owner_id FROM tasks JOIN contributions on tasks.id = contributions.task_id WHERE contributions.user_id = ? AND tasks.status = ?", nativeQuery = true)
    List<Task> getContributorTasks(Long userId, String status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tasks SET status = ?, completed_date = ? WHERE id = ?", nativeQuery = true)
    void changeData(String status, LocalDate completedDate, Long id);

    @Query(value = "SELECT COUNT(*) AS total FROM tasks WHERE owner_id = ? AND id = ?", nativeQuery = true)
    Integer isOwnTask(Long ownerId, Long id);

}

