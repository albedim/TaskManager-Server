package me.albedim.taskmanager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: albedim <dimaio.albe@gmail.com>
 * Created on: 06/11/22
 * Created at: 14:54
 * Version: 1.0.0
 * Description: This is the class for the configuration
 */

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter
{
    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**");
    }
}
