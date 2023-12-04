package data.repositories;

import data.models.Diary;

import java.util.List;

public interface DiaryRepository {
    Diary save(Diary diary);
    List<Diary> findAll();
    Diary findById(int id);
    Diary findByEntryIdAndDiaryId(int entryId, int diaryId);
    void delete(int id);
    void delete(Diary diary);
    long count();
    void clear();

}
