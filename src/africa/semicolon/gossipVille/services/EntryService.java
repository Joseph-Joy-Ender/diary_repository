package africa.semicolon.gossipVille.services;

import africa.semicolon.gossipVille.data.models.Entry;

import java.util.List;

public interface EntryService {

    void create(String title, String body, int DiaryId);
    List<Entry> findAll();
}
