package Entity;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Shop implements Serializable {
    @Builder.Default
    private int ID = 1;
    @Builder.Default
    private String name = "";
    @Builder.Default
    private String address = "";
    @Builder.Default
    private float rating = 0;
    @Builder.Default
    private String phoneNumber = "";
    @Builder.Default
    private String imageURL = "";
    @Builder.Default
    private String description = "";

    @Override
    public String toString() {
        return name + "-" + address + "-" + rating + "-" + phoneNumber + "-" + imageURL;
    }

    public static Shop generateShopFromJSON(JSONObject jsonObject) {
        try {
            return Shop.builder()
                    .ID(jsonObject.getInt("id"))
                    .name(jsonObject.getString("name"))
                    .address(jsonObject.getString("address"))
                    .phoneNumber(jsonObject.getString("phoneNumber"))
                    .imageURL(jsonObject.getJSONObject("Image_model").getString("imageURL"))
                    .description(jsonObject.getString("description"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    public static Shop generateShopFromJSON(JSONArray jsonArray) {

        try {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return Shop.builder()
                    .ID(jsonObject.getInt("id"))
                    .name(jsonObject.getString("name"))
                    .address(jsonObject.getString("address"))
                    .phoneNumber(jsonObject.getString("phoneNumber"))
                    .imageURL(jsonObject.getJSONObject("Image_model").getString("imageURL"))
                    .description(jsonObject.getString("description"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
