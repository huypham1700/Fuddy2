package Entity;

import org.json.JSONObject;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Rating {
    private int id;
    private int starNumber;
    private int voteCount;

    public static Rating generateRatingCount(int starNumber, JSONObject jsonObject) {
        try {
            return Rating.builder()
                    .starNumber(starNumber)
                    .voteCount(Integer.parseInt(jsonObject.getString("count")))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
