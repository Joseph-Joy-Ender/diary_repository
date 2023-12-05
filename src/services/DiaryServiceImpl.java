package services;

import data.models.Diary;
import data.models.Entry;
import data.repositories.DiaryRepository;
import data.repositories.DiaryRepositoryImplementation;
import exceptions.InvalidDetailsException;
import exceptions.UserExistException;

import java.util.ArrayList;
import java.util.List;

public class DiaryServiceImpl  implements  DiaryService{
    private DiaryRepository repository = new DiaryRepositoryImplementation();
    private EntryService entryService = new EntryServiceImpl();

    @Override
    public void register(String username, String password) {
        if(userExist(username)) throw new UserExistException(username + " already exist");
        Diary newDiary = new Diary();
        newDiary.setUsername(username);
        newDiary.setPassword(password);
        repository.save(newDiary);
    }
    private boolean userExist(String username){
        Diary foundDiary = repository.findByUsername(username);
        return foundDiary != null;
    }
    @Override
    public void login(String username, String password) {
      Diary foundDiary = repository.findByUsername(username);
      if(!userExist(username)) throw new InvalidDetailsException();
      if(!foundDiary.getPassword().equals(password)) throw new InvalidDetailsException();
      foundDiary.setLocked(false);
      repository.save(foundDiary);
    }

    @Override
    public Diary findDiaryBelongingTo(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void writeOn(String username, String title, String body) {
        Diary findDiary = repository.findByUsername(username);
        entryService.create(title, body, findDiary.getId());
    }

    @Override
    public List<Entry> findEntriesBelongingTo(String username) {
        Diary findDiary = repository.findByUsername(username);
       List<Entry> entries = new ArrayList<>();
       for (Entry entry: entryService.findAll()){
           if(entry.getDiaryId() == findDiary.getId()) entries.add(entry);
       }
       return entries;
    }
}
