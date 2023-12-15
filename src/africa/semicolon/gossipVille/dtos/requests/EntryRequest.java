package africa.semicolon.gossipVille.dtos.requests;

import lombok.Data;

@Data
public class EntryRequest {
    private String username;
    private String title;
    private String body;

}
