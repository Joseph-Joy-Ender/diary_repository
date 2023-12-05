package services;

import data.models.Diary;
import data.models.Entry;
import java.util.Collection;
import java.util.List;

public interface DiaryService {
    void register(String username, String password);

    void login(String username, String password);

    Diary findDiaryBelongingTo(String username);

    void writeOn(String username, String title, String body);

    List<Entry> findEntriesBelongingTo(String username);
}
