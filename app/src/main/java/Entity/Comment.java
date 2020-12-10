package Entity;

import org.json.JSONObject;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Comment {
    /**
     *
     */
    private int id;
    /**
     *
     */
    private String userName;
    /**
     *
     */
    private String imgUserURL;
    /**
     *
     */
    private String Content;
    /**
     *
     */
    private String dateCreate;


    public static Comment generateCommentFromJSon(JSONObject jsonObject) {
        try {
            return Comment.builder()
                    .id(Integer.parseInt(jsonObject.getString("id")))
                    .userName(jsonObject.getJSONObject("User_model").getString("name"))
                    .imgUserURL(jsonObject.getJSONObject("User_model").getJSONObject("Image_model").getString("imageURL"))
                    .Content(jsonObject.getString("content"))
                    .dateCreate(jsonObject.getString("createAt"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
