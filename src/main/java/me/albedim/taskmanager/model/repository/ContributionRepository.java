package me.albedim.taskmanager.model.repository;

import me.albedim.taskmanager.model.entity.Contribution;
import me.albedim.taskmanager.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 05/11/22
 * Created at: 00:54
 * Version: 1.0.0
 * Description: This is the class for the contributor repository
 */

public interface ContributionRepository extends CrudRepository<Contribution, Long>
{
    @Override
    <S extends Contribution> S save(S entity);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM contributions WHERE user_id = ? AND task_id = ?", nativeQuery = true)
    void deleteContribution(Long userId, Long taskId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM contributions WHERE task_id = ?", nativeQuery = true)
    void deleteTaskContributions(Long taskId);

}
