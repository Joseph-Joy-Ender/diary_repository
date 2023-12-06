package africa.semicolon.gossipVille.data.repositories;

import africa.semicolon.gossipVille.data.models.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryRepositoryImplementationTest {

    private DiaryRepository diaryRepository;
    @BeforeEach
    public void startAllWith() {
        diaryRepository = new DiaryRepositoryImplementation();
    }

    @Test
    public void saveOneDiary_countIsOne() {
        Diary diary = new Diary();
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void saveTwoDiary_countIsTwo() {
        Diary diary = new Diary();
        Diary secondDiary = new Diary();
        diaryRepository.save(diary);
        diaryRepository.save(secondDiary);
        assertEquals(2, diaryRepository.count());
    }

    @Test
    public void saveTwoDiary_findAllCountReturnsTwoTest() {
        Diary diary = new Diary();
        Diary secondDiary = new Diary();
        diaryRepository.save(diary);
        diaryRepository.save(secondDiary);
        assertEquals(2, diaryRepository.findAll().size());

    }

    @Test
    public void saveNewDiary_idIsOneTest() {
        Diary diary = new Diary();
        assertEquals(0, diary.getId());
        diaryRepository.save(diary);
        assertEquals(1, diary.getId());
    }

    @Test
    public void saveUpdatedDiary_doesNotInfluenceCountTest() {
        Diary diary = new Diary();
        diaryRepository.save(diary);

        Diary updatedDiary = new Diary();
        updatedDiary.setId(1);
        diaryRepository.save(updatedDiary);

        assertEquals(1, diaryRepository.count());
        assertEquals(updatedDiary, diaryRepository.findById(1));
    }

    @Test
    public void saveThreeDiary_deleteById(){
        Diary diary = new Diary();
        Diary secondDiary = new Diary();
        Diary thirdDiary = new Diary();
        diaryRepository.save(diary);
        diaryRepository.save(secondDiary);
        diaryRepository.save(thirdDiary);
        assertEquals(3, diaryRepository.count());
        diaryRepository.delete(1);
        assertEquals(2, diaryRepository.count());

    }

    @Test
    public void saveThreeDiary_deleteByDiary(){
        Diary diary = new Diary();
        Diary secondDiary = new Diary();
        Diary thirdDiary = new Diary();
        diaryRepository.save(diary);
        diaryRepository.save(secondDiary);
        diaryRepository.save(thirdDiary);
        assertEquals(3, diaryRepository.count());
        diaryRepository.delete(diary);
        assertEquals(2, diaryRepository.count());

    }
}