package africa.semicolon.gossipVille.services;

import africa.semicolon.gossipVille.data.repositories.DiaryRepository;
import africa.semicolon.gossipVille.data.repositories.EntryRepository;
import africa.semicolon.gossipVille.dtos.requests.RegisterRequest;
import africa.semicolon.gossipVille.exceptions.InvalidDetailsException;
import africa.semicolon.gossipVille.exceptions.UserExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);
        assertThrows(InvalidDetailsException.class,
                ()->diaryService.login("philip", "wrongPassword"));
    }
    @Test
    public void registerUser_loginWithWrongUsername_throwsExceptionTest(){
        RegisterRequest request = new RegisterRequest();
        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);
        assertThrows(InvalidDetailsException.class,
                ()->diaryService.login("wrongUsername", "password"));
    }
    @Test
    public void registerUser_loginWithRightDetails_foundDiaryIsUnlockedTest(){
        RegisterRequest request = new RegisterRequest();
        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);
        boolean isLocked = diaryService.findDiaryBelongingTo("philip").isLocked();
        assertTrue(isLocked);
        diaryService.login("philip", "password");
        isLocked = diaryService.findDiaryBelongingTo("philip").isLocked();
        assertFalse(isLocked);
    }
    @Test
    public void registerUser_loginWithRightDetails_createEntry_viewAllEntrySizeIsOneTest(){
        RegisterRequest request = new RegisterRequest();
        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);
        diaryService.login("philip", "password");
        diaryService.writeOn("philip", "title", "body");
        assertEquals(1, diaryService.findEntriesBelongingTo("philip").size());
    }
    @Test
    public void registerUser_loginWithRightDetails_create_two_entry_viewAllEntrySizeIsTwoTest(){
        RegisterRequest request = new RegisterRequest();
        request.setPassword("password");
        request.setUsername("philip");
        diaryService.register(request);
        diaryService.login("philip", "password");
        diaryService.writeOn("philip", "title", "body");
        diaryService.writeOn("philip", "title", "body");
        assertEquals(2, diaryService.findEntriesBelongingTo("philip").size());
        assertThat(diaryService.findEntriesBelongingTo("philip").size(), is(2));
    }

}