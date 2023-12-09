package africa.semicolon.gossipVille.data.repositories;

import africa.semicolon.gossipVille.data.models.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DiaryRepositoryImplementationTest {

    @Autowired
    private DiaryRepository diaryRepository;


    @Test
    public void saveOneDiary_countIsOne() {
        diaryRepository.deleteAll();
        Diary diary = new Diary();
        Diary savedDiary = diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
        assertNotNull(savedDiary.getId());
    }

}