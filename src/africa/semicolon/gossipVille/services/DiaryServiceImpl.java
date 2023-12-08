package africa.semicolon.gossipVille.services;

import africa.semicolon.gossipVille.data.models.Diary;
import africa.semicolon.gossipVille.data.models.Entry;
import africa.semicolon.gossipVille.data.repositories.DiaryRepository;
import africa.semicolon.gossipVille.dtos.requests.RegisterRequest;
import africa.semicolon.gossipVille.exceptions.InvalidDetailsException;
import africa.semicolon.gossipVille.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static africa.semicolon.gossipVille.utils.Mapper.map;

@Service
public class DiaryServiceImpl  implements  DiaryService{
    @Autowired
    private DiaryRepository repository ;
    @Autowired
    private EntryService entryService ;

    @Override
    public void register(RegisterRequest registerRequest) {
        if(userExist(registerRequest.getUsername())) throw new UserExistException(registerRequest.getUsername()+ " already exist");
       Diary diary = map(registerRequest);
        repository.save(diary);
    }

    private boolean userExist(String username){
        Diary foundDiary = repository.findDiaryByUsername(username);
        return foundDiary != null;
    }
    @Override
    public void login(String username, String password) {
      Diary foundDiary = repository.findDiaryByUsername(username);
      if(!userExist(username)) throw new InvalidDetailsException();
      if(!foundDiary.getPassword().equals(password)) throw new InvalidDetailsException();
      foundDiary.setLocked(false);
      repository.save(foundDiary);
    }

    @Override
    public Diary findDiaryBelongingTo(String username) {
        return repository.findDiaryByUsername(username);
    }

    @Override
    public void writeOn(String username, String title, String body) {
        Diary findDiary = repository.findDiaryByUsername(username);
        entryService.create(title, body, findDiary.getId());
    }

    @Override
    public List<Entry> findEntriesBelongingTo(String username) {
        Diary findDiary = repository.findDiaryByUsername(username);
       List<Entry> entries = new ArrayList<>();
       for (Entry entry: entryService.findAll()){
           if(entry.getDiaryId() == findDiary.getId()) entries.add(entry);
       }
       return entries;
    }
}
