package me.albedim.taskmanager.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util
{
    public static final String TODO = "todo";
    public static final String RESOURCE_DOESNT_EXIST = "The resource you are trying to get, doesn't exist";
    public static final String URL = "/api/v_1_0_5";
    public static final String NOT_ENOUGH_PERMISSIONS = "You don't have enough permissions to do this";
    public static final String RESOURCE_ALREADY_EXISTS = "The resource you are trying to add, already exists";

    public static HashMap createResponse(Boolean success, String param)
    {
        HashMap response = new HashMap<>();
        response.put("date", String.valueOf(LocalDateTime.now()));
        response.put("success", success);
        response.put("param", param);
        response.put("code", 200);
        return response;
    }

    public static HashMap createResponse(Boolean success, String error, Integer code)
    {
        HashMap response = new HashMap<>();
        response.put("date", String.valueOf(LocalDateTime.now()));
        response.put("success", success);
        response.put("error", error);
        response.put("code", code);
        return response;
    }
}
