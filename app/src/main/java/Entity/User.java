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
public class User implements Serializable {
    @Builder.Default
    private int id=0;
    @Builder.Default
    private String email = "";
    @Builder.Default
    private String password = "";
    @Builder.Default
    private String hashedPassword = "";
    @Builder.Default
    private String name = "";
    @Builder.Default
    private int imageId=0;
    @Builder.Default
    private String imageURL = "";
    @Builder.Default
    private String phoneNumber = "";
    @Builder.Default
    private String address = "";
    @Builder.Default
    private int userType = 1;
    @Builder.Default
    private String tokenKey = "";
    @Builder.Default
    private String expireDate = "";

    public static User createUserFromJSONObject(JSONObject jsonObject) {
        try {
            return User.builder()
                    .id(jsonObject.getInt("id"))
                    .email(jsonObject.getString("email"))
                    .hashedPassword(jsonObject.getString("hashPassword"))
                    .name(jsonObject.getString("name"))
                    .imageId(jsonObject.getInt("imageId"))
                    .phoneNumber(jsonObject.getString("phoneNumber"))
                    .address(jsonObject.getString("address"))
                    .userType(jsonObject.getInt("userType"))
                    .tokenKey(jsonObject.getString("tokenKey"))
                    .expireDate(jsonObject.getString("expireDate"))
                    .build();

        } catch (JSONException e) {
            return null;
        }
    }
}
