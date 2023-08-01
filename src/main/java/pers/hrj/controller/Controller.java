package pers.hrj.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hrj.model.entity.User;
import pers.hrj.service.UserService;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/setData")
    public void setDate() throws Exception {
        final File file = new File("C:\\Users\\Administrator\\Downloads\\toolscat-data.json");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }
        List<User> users = JSONArray.parseArray(stringBuilder.toString(), User.class);
//        System.out.println(users);
        userService.saveBatch(users);
    }
}
