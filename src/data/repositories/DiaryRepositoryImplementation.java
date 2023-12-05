package data.repositories;

import data.models.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImplementation implements DiaryRepository{
    private int count;
    private final List<Diary> diaries = new ArrayList<>();
    @Override
    public Diary save(Diary diary) {
        if (isNew(diary)) createNew(diary);
        else update(diary);

        return diary;
    }


    private void update(Diary updatedDiary) {
        Diary oldEntry = findById(updatedDiary.getId());
        diaries.remove(oldEntry);
        diaries.add(updatedDiary);
    }

    private void createNew(Diary diary) {
        count++;
        diary.setId(generateNewId());
        diaries.add(diary);
    }

    private int generateNewId() {
        return count;
    }

    private boolean isNew(Diary diary) {
        return diary.getId() == 0;
    }

    @Override
    public List<Diary> findAll() {
        return diaries;
    }

    @Override
    public Diary findById(int id) {
        for (Diary diary: diaries) {
            if (diary.getId() == id) return diary;

        }
        return null;

    }

    @Override
    public Diary findByEntryIdAndDiaryId(int entryId, int diaryId) {
        Diary diary = findById(diaryId);
        if (diary.getId() == entryId) return diary;
        return null;
    }

    @Override
    public void delete(int id) {
       Diary diary = findById(id);
        diaries.remove(diary);
        count--;

    }

    @Override
    public void delete(Diary diary) {
        diaries.remove(diary);
        count--;

    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void clear() {
        diaries.clear();
    }

    @Override
    public Diary findByUsername(String username) {
        for(Diary diary : diaries) if(diary.getUsername().equalsIgnoreCase(username)) return diary;
        return null;
    }
}
