package africa.semicolon.gossipVille.services;

import africa.semicolon.gossipVille.data.models.Diary;
import africa.semicolon.gossipVille.data.models.Entry;
import africa.semicolon.gossipVille.data.repositories.DiaryRepository;
import africa.semicolon.gossipVille.dtos.requests.LoginRequest;
import africa.semicolon.gossipVille.exceptions.InvalidDetailsException;
import africa.semicolon.gossipVille.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DiaryServiceImpl  implements  DiaryService{
    @Autowired
    private DiaryRepository repository ;
    @Autowired
    private EntryService entryService ;

    @Override
    public void register(LoginRequest loginRequest) {
        if(userExist(loginRequest.getUsername())) throw new UserExistException(loginRequest.getUsername()+ " already exist");
        Diary newDiary = new Diary();
        newDiary.setUsername(loginRequest.getUsername());
        newDiary.setPassword(loginRequest.getPassword());
        repository.save(newDiary);
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
