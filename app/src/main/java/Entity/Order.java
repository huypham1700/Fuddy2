package Entity;

import org.json.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Order {
    private int id;
    private String imageURL;
    private int status;
    private double total;
    private int quantity;
    private String reason;
    private String createAt;
    private String description;

    public static Order generateOrderFromJSON(JSONObject jsonObject) {
        try {
            return Order.builder()
                    .id(jsonObject.getInt("id"))
                    .imageURL(jsonObject.getJSONObject("Image_model").getString("imageURL"))
                    .status(jsonObject.getInt("status"))
                    .quantity(jsonObject.getInt("quantity"))
                    .total(jsonObject.getDouble("total"))
                    .reason(jsonObject.getString("reason"))
                    .createAt(jsonObject.getString("createAt"))
                    .description(jsonObject.getString("description"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
