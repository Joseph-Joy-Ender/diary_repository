package africa.semicolon.gossipVille.data.repositories;

import africa.semicolon.gossipVille.data.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DiaryRepository extends MongoRepository<Diary, String> {
    Diary findDiaryByUsername(String username);

}
