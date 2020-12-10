package Entity;

import org.json.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Food {
    @Builder.Default
    private int ID = 1;

    @Builder.Default
    private int shopID = 1;

    @Builder.Default
    private String name = "default name";

    @Builder.Default
    private String address = "unknown";

    @Builder.Default
    private String category = "unknown";

    @Builder.Default
    private float price = 0;

    @Builder.Default
    private String imageURL = "unknown";

    @Builder.Default
    private String description = "default description";

    @Builder.Default
    private int imageID = 1;

    public static Food generateFoodFromJSON(JSONObject jsonObject) {
        try {
            return Food.builder()
                    .ID(jsonObject.getInt("id"))
                    .imageID(jsonObject.getInt("imageId"))
                    .name(jsonObject.getString("name"))
                    .address(jsonObject.getJSONObject("Store_model").getString("address"))
                    .category(jsonObject.getJSONObject("Category_model").getString("name"))
                    .price(jsonObject.getInt("price"))
                    .imageURL(jsonObject.getJSONObject("Image_model").getString("imageURL"))
                    .description(jsonObject.getString("description"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
