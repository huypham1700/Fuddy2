package Repository;

import Entity.Shop;

public interface IStoreDetailRepository {

    void getStoreDetail(Shop shop, Exception error);
}
