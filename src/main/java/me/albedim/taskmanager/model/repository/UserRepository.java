package me.albedim.taskmanager.model.repository;

import me.albedim.taskmanager.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 04/11/22
 * Created at: 22:08
 * Version: 1.0.0
 * Description: This is the class for the user repository
 */

public interface UserRepository extends CrudRepository<User, Long>
{
    @Override
    <S extends User> S save(S entity);

    @Query(value = "SELECT COUNT(*) AS total FROM users WHERE email = ?", nativeQuery = true)
    Integer existsByEmail(String email);

    @Query(value = "SELECT COUNT(*) AS total FROM users WHERE nickname = ?", nativeQuery = true)
    Integer existsByNickname(String nickname);

    @Query(value = "SELECT * FROM users WHERE email = ? AND password = ?", nativeQuery = true)
    User login(String email, String password);

    @Query(value = "SELECT * FROM users WHERE nickname = ?", nativeQuery = true)
    Long getIdByName(String name);

    @Query(value = "SELECT * FROM users JOIN contributions ON users.id = contributions.user_id WHERE contributions.task_id = ?", nativeQuery = true)
    List<User> getContributorsOfTask(Long taskId);

    @Query(value = "SELECT * FROM users WHERE id = ?", nativeQuery = true)
    User getData(Long id);

}
