package Entity;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Image {
    private int ID;
    private String imageURL;
    private String type;

    public static ArrayList<Image> generateFakeImageArray() {
        ArrayList<Image> fakeList = new ArrayList<>();
        fakeList.add(Image.builder()
                .ID(1)
                .imageURL("https://cdn.becungshop.vn/images/blog/tro-thanh-fan-cung-cua-be-cung-shop-nhan-ngay-ma-giam-gia-20-9a5e157d.jpg")
                .type("aaa")
                .build());
        fakeList.add(Image.builder()
                .ID(2)
                .imageURL("https://cdn.tgdd.vn/2020/03/GameApp/unnamed-200x200-3.png")
                .type("aaa")
                .build());
        fakeList.add(Image.builder()
                .ID(3)
                .imageURL("https://elevate.bike/wp-content/uploads/2019/12/service-logo-200x200.png")
                .type("aaa")
                .build());
        fakeList.add(Image.builder()
                .ID(4)
                .imageURL("https://bluecowbarrydale.com/wp-content/uploads/2019/04/Bluecow-logo-200x200.png")
                .type("aaa")
                .build());
        fakeList.add(Image.builder()
                .ID(5)
                .imageURL("https://cdn.shopify.com/s/files/1/0055/7105/1602/files/hdlogotransparent_180x.jpg?v=1575486343")
                .type("aaa")
                .build());
        fakeList.add(Image.builder()
                .ID(6)
                .imageURL("https://www.ami.co.nz/images/Shop.png")
                .type("aaa")
                .build());
        return fakeList;
    }
}
