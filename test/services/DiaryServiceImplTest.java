package services;

import data.models.Diary;
import exceptions.InvalidDetailsException;
import exceptions.UserExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class DiaryServiceImplTest {
    private DiaryService diaryService;

    @BeforeEach
    public void startWithThis(){
        diaryService = new DiaryServiceImpl();
    }
    @Test
    public void registerPhilip_registerPhilipAgain_throwsExceptionTest(){
        diaryService.register("philip", "password");
        assertThrows(UserExistException.class, ()-> diaryService.register("philip", "password"));
    }

    @Test
    public void registerUser_loginWithWrongPassword_throwsExceptionTest(){
        diaryService.register("philip", "password");
        assertThrows(InvalidDetailsException.class,
                ()->diaryService.login("philip", "wrongPassword"));
    }
    @Test
    public void registerUser_loginWithWrongUsername_throwsExceptionTest(){
        diaryService.register("philip", "password");
        assertThrows(InvalidDetailsException.class,
                ()->diaryService.login("wrongUsername", "password"));
    }
    @Test
    public void registerUser_loginWithRightDetails_foundDiaryIsUnlockedTest(){
        diaryService.register("philip", "password");
        boolean isLocked = diaryService.findDiaryBelongingTo("philip").isLocked();
        assertTrue(isLocked);
        diaryService.login("philip", "password");
        isLocked = diaryService.findDiaryBelongingTo("philip").isLocked();
        assertFalse(isLocked);
    }
    @Test
    public void registerUser_loginWithRightDetails_createEntry_viewAllEntrySizeIsOneTest(){
        diaryService.register("philip", "password");
        diaryService.login("philip", "password");
        diaryService.writeOn("philip", "title", "body");
        assertEquals(1, diaryService.findEntriesBelongingTo("philip").size());
    }
    @Test
    public void registerUser_loginWithRightDetails_create_two_entry_viewAllEntrySizeIsTwoTest(){
        diaryService.register("philip", "password");
        diaryService.login("philip", "password");
        diaryService.writeOn("philip", "title", "body");
        diaryService.writeOn("philip", "title", "body");
        assertEquals(2, diaryService.findEntriesBelongingTo("philip").size());
    }

}