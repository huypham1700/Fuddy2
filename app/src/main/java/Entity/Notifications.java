package Entity;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Notifications implements Serializable {
    @Builder.Default
    private String title="";
    @Builder.Default
    private String imageUrl="";
    @Builder.Default
    private String content="";
    @Builder.Default
    private String storeId="";
    @Builder.Default
    private String foodId="";
    @Builder.Default
    private String idType="";

    public static Notifications createNotificationFromJSONObject(JSONObject jsonObject){
        try{
            return Notifications.builder()
                    .title(jsonObject.getString("title"))
                    .content(jsonObject.getString("content"))
                    .storeId(jsonObject.getString("storeId"))
                    .foodId((jsonObject.getString("foodId")))
                    .idType(jsonObject.getString("idType"))
                    .build();

        }catch (JSONException jsonException){
            return null;
        }
    }
}
