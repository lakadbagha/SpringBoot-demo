package com.example.demo.Controller;

import com.example.demo.DTO.Login;
import com.example.demo.Model.User;
import com.example.demo.Service.ServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    ServiceImpl serviceImpl;

    @RequestMapping("/")
    public String homePage() {
        return "index";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@RequestBody Login login) {
        System.out.println("loginUser invoked");
        ResponseEntity<Map> responseEntity;
        Map<String, String> map = new HashMap();
        User user = serviceImpl.findAccountByEmail(login.getUserName());
        if (user == null) {
            map.put("result", "Cannot find User.");
        } else {
            if (user.getPassword().equals(login.getPassword())) {
                map.put("result", "success.");
            } else {
                map.put("result", "invalid creditials.");
            }
        }
        responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("register")
    public ResponseEntity registerUser(@RequestBody User user) {
        ResponseEntity<Map> responseEntity;
        serviceImpl.saveUser(user);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/homePage")
    public String dashboardPage() {
        return "homePage";
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUser() {
        ResponseEntity<ArrayList> responseEntity;
        List<User> users = serviceImpl.findAllAccount();
        responseEntity = new ResponseEntity(users, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/updateUser")
    public ResponseEntity updateUser(@RequestBody User user) {
        ResponseEntity<Map> responseEntity;
        serviceImpl.updateUser(user);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestBody User user) {
        ResponseEntity<Map> responseEntity;
        serviceImpl.deleteUser(user);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        return responseEntity;
    }
}
