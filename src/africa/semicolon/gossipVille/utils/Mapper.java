package africa.semicolon.gossipVille.utils;

import africa.semicolon.gossipVille.data.models.Diary;
import africa.semicolon.gossipVille.dtos.requests.RegisterRequest;

public class Mapper {
    public static Diary map(RegisterRequest registerRequest){
        Diary newDiary = new Diary();

        newDiary.setUsername(registerRequest.getUsername());
        newDiary.setPassword(registerRequest.getPassword());
        return newDiary;
    }
}
