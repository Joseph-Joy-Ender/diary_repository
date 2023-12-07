package africa.semicolon.gossipVille.controllers;

import africa.semicolon.gossipVille.dtos.requests.LoginRequest;
import africa.semicolon.gossipVille.exceptions.DiaryAppException;

import africa.semicolon.gossipVille.services.DiaryService;
import africa.semicolon.gossipVille.services.DiaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest loginRequest){
        try {
            diaryService.register(loginRequest);
            return "Account created";
        }
        catch (DiaryAppException ex){
            return ex.getMessage();
        }
    }
    @PostMapping("/login/{username}/{password}")
    public String login(@PathVariable("username") String username,@PathVariable("password") String password){
        try {
            diaryService.login(username, password);
            return "You don login!!!";
        }
        catch (DiaryAppException ex){
            return ex.getMessage();
        }
    }
    @PostMapping("/entry")
    public String createEntry(@RequestParam("username") String username,@RequestParam("title") String title,@RequestParam("body") String body){
        try {
            diaryService.writeOn(username,title, body);
            return "We don write am";
        }
        catch (DiaryAppException ex){
            return ex.getMessage();
        }

    }
    @GetMapping("/entry/{username}")
    public Object findEntryBelongingTo(@PathVariable("username") String username){
        try {
            return diaryService.findEntriesBelongingTo(username);
        }
        catch (DiaryAppException ex){
            return ex.getMessage();
        }
    }
}
