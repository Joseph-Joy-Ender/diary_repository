package africa.semicolon.gossipVille.services;

import africa.semicolon.gossipVille.data.models.Diary;
import africa.semicolon.gossipVille.data.models.Entry;
import africa.semicolon.gossipVille.dtos.requests.EntryRequest;
import africa.semicolon.gossipVille.dtos.requests.LoginRequest;
import africa.semicolon.gossipVille.dtos.requests.RegisterRequest;

import java.util.List;

public interface DiaryService {
    void register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

    Diary findDiaryBelongingTo(String username);

    void writeOn(EntryRequest entryRequest);

    List<Entry> findEntriesBelongingTo(String username);
}
