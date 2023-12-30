package africa.semicolon.gossipVille.services;

import africa.semicolon.gossipVille.data.models.Diary;
import africa.semicolon.gossipVille.data.models.Entry;
import africa.semicolon.gossipVille.data.repositories.DiaryRepository;
import africa.semicolon.gossipVille.dtos.requests.EntryRequest;
import africa.semicolon.gossipVille.dtos.requests.LoginRequest;
import africa.semicolon.gossipVille.dtos.requests.RegisterRequest;
import africa.semicolon.gossipVille.exceptions.InvalidDetailsException;
import africa.semicolon.gossipVille.exceptions.UserExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static africa.semicolon.gossipVille.utils.Mapper.map;

@Service
@AllArgsConstructor
public class DiaryServiceImpl  implements  DiaryService{
    private final DiaryRepository repository ;
    private final EntryService entryService ;

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
    public void login(LoginRequest loginRequest) {
      Diary foundDiary = repository.findDiaryByUsername(loginRequest.getUsername());
      if(!userExist(loginRequest.getUsername())) throw new InvalidDetailsException();
      if(!foundDiary.getPassword().equals(loginRequest.getPassword())) throw new InvalidDetailsException();
      foundDiary.setLocked(false);
      repository.save(foundDiary);
    }

    @Override
    public Diary findDiaryBelongingTo(String username) {
        return repository.findDiaryByUsername(username);
    }

    @Override
    public void writeOn(EntryRequest entryRequest) {
        Diary findDiary = repository.findDiaryByUsername(entryRequest.getUsername());
        entryService.create(entryRequest.getTitle(), entryRequest.getBody(), findDiary.getId());
    }

    @Override
    public List<Entry> findEntriesBelongingTo(String username) {
        Diary findDiary = repository.findDiaryByUsername(username);
       List<Entry> entries = new ArrayList<>();
       for (Entry entry: entryService.findAll()){
           if(entry.getDiaryId().equals( findDiary.getId())) entries.add(entry);
       }
       return entries;
    }
}
