package africa.semicolon.gossipVille.services;

import africa.semicolon.gossipVille.data.models.Diary;
import africa.semicolon.gossipVille.data.models.Entry;
import africa.semicolon.gossipVille.dtos.requests.LoginRequest;

import java.util.List;

public interface DiaryService {
    void register(LoginRequest loginRequest);

    void login(String username, String password);

    Diary findDiaryBelongingTo(String username);

    void writeOn(String username, String title, String body);

    List<Entry> findEntriesBelongingTo(String username);
}