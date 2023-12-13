package africa.semicolon.gossipVille.controllers;

import africa.semicolon.gossipVille.dtos.requests.RegisterRequest;
import africa.semicolon.gossipVille.dtos.responses.ApiResponse;
import africa.semicolon.gossipVille.dtos.responses.RegisterResponse;
import africa.semicolon.gossipVille.exceptions.DiaryAppException;

import africa.semicolon.gossipVille.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse = new RegisterResponse();

        try {
            diaryService.register(registerRequest);
            registerResponse.setMessage("Account created successfully");
            return new ResponseEntity<>(new ApiResponse(true, registerResponse), HttpStatus.CREATED);
        }
        catch (DiaryAppException ex){
            registerResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, registerResponse), HttpStatus.BAD_REQUEST);
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
