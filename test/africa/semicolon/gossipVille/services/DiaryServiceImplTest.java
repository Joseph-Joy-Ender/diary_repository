package africa.semicolon.gossipVille.services;

import africa.semicolon.gossipVille.data.repositories.DiaryRepository;
import africa.semicolon.gossipVille.data.repositories.EntryRepository;
import africa.semicolon.gossipVille.dtos.requests.EntryRequest;
import africa.semicolon.gossipVille.dtos.requests.LoginRequest;
import africa.semicolon.gossipVille.dtos.requests.RegisterRequest;
import africa.semicolon.gossipVille.exceptions.InvalidDetailsException;
import africa.semicolon.gossipVille.exceptions.UserExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiaryServiceImplTest {
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private EntryRepository entryRepository;

    @AfterEach
    public void doThisAfterEachTest(){
        diaryRepository.deleteAll();
        entryRepository.deleteAll();
    }

    @Test
    public void registerPhilip_registerPhilipAgain_throwsExceptionTest(){
        RegisterRequest request = new RegisterRequest();
        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);
        assertThrows(UserExistException.class, ()-> diaryService.register(request));
    }

    @Test
    public void registerUser_loginWithWrongPassword_throwsExceptionTest(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);
        loginRequest.setPassword("wrongPassword");
        loginRequest.setUsername("philip");
        assertThrows(InvalidDetailsException.class,
                () -> diaryService.login(loginRequest));
    }
    @Test
    public void registerUser_loginWithWrongUsername_throwsExceptionTest(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);
        loginRequest.setUsername("wrongUsername");
        loginRequest.setPassword("password");
        assertThrows(InvalidDetailsException.class,
                ()->diaryService.login(loginRequest));
    }

    @Test
    public void registerUser_loginWithRightDetails_foundDiaryIsUnlockedTest(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);
        boolean isLocked = diaryService.findDiaryBelongingTo("philip").isLocked();
        assertTrue(isLocked);

        loginRequest.setUsername("philip");
        loginRequest.setPassword("password");
        diaryService.login(loginRequest);
        isLocked = diaryService.findDiaryBelongingTo("philip").isLocked();
        assertFalse(isLocked);
    }

    @Test
    public void registerUser_loginWithRightDetails_createEntry_viewAllEntrySizeIsOneTest(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        EntryRequest entryRequest = new EntryRequest();
        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);

        loginRequest.setPassword("password");
        loginRequest.setUsername("philip");
        diaryService.login(loginRequest);

        entryRequest.setUsername("philip");
        entryRequest.setBody("body");
        entryRequest.setTitle("title");
        diaryService.writeOn(entryRequest);
        assertEquals(1, diaryService.findEntriesBelongingTo("philip").size());
    }

    @Test
    public void registerUser_loginWithRightDetails_createTwoEntry_viewAllEntrySizeIsTwoTest(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        EntryRequest entryRequest = new EntryRequest();

        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);

        loginRequest.setUsername("philip");
        loginRequest.setPassword("password");
        diaryService.login(loginRequest);

        entryRequest.setTitle("title");
        entryRequest.setBody("body");
        entryRequest.setUsername("philip");
        diaryService.writeOn(entryRequest);
        entryRequest.setTitle("Title2");
        entryRequest.setUsername("philip");
        entryRequest.setBody("Lollll");
        diaryService.writeOn(entryRequest);
        System.out.println(diaryService.findEntriesBelongingTo("philip"));

        assertEquals(2, diaryService.findEntriesBelongingTo("philip").size());
        assertThat(diaryService.findEntriesBelongingTo("philip").size(), is(2));
    }

}