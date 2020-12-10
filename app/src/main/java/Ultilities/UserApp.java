package Ultilities;

import lombok.Builder;

@Builder

public class UserApp {
    public static final Entity.User user =
            Entity.User.builder().build();
}
