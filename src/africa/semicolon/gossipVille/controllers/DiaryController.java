package africa.semicolon.gossipVille.controllers;

import africa.semicolon.gossipVille.dtos.requests.EntryRequest;
import africa.semicolon.gossipVille.dtos.requests.LoginRequest;
import africa.semicolon.gossipVille.dtos.requests.RegisterRequest;
import africa.semicolon.gossipVille.dtos.responses.ApiResponse;
import africa.semicolon.gossipVille.dtos.responses.EntryResponse;
import africa.semicolon.gossipVille.dtos.responses.LoginResponse;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = new LoginResponse();
        try {
            diaryService.login(loginRequest);
            loginResponse.setMessage("You don login!!!");
            return new ResponseEntity<>(new ApiResponse(true, loginResponse), HttpStatus.OK);
        }
        catch (DiaryAppException ex){
            loginResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, loginResponse), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/entry")
    public ResponseEntity<?> createEntry(@RequestBody EntryRequest entryRequest){
        EntryResponse entryResponse = new EntryResponse();
        try {
            diaryService.writeOn(entryRequest);
            entryResponse.setMessage("We don write am");
            return new ResponseEntity<>(new ApiResponse(true, entryResponse), HttpStatus.CREATED);
        }
        catch (DiaryAppException ex){
            entryResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, entryResponse), HttpStatus.BAD_REQUEST);
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
