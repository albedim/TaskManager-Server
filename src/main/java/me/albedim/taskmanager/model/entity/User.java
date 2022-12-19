package me.albedim.taskmanager.model.entity;

import javax.persistence.*;
import java.util.HashMap;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 04/11/22
 * Created at: 22:08
 * Version: 1.0.0
 * Description: This is the class for the user entity
 */

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private User() { }

    public User(String image, String nickname, String email, String password)
    {
        setImage(image);
        setNickname(nickname);
        setEmail(email);
        setPassword(password);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public HashMap toJson()
    {
        HashMap object = new HashMap();
        object.put("id", this.getId());
        object.put("image", this.getImage());
        object.put("nickname", this.getNickname());
        object.put("email", this.getEmail());
        object.put("password", this.getPassword());
        return object;
    }

}

