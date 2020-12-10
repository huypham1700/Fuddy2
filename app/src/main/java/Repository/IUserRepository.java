package Repository;

import Entity.User;

public interface IUserRepository {
    void afterLogin(User user, Exception error);
    void afterRegister(User user, Exception error);
    void getSettingData(User user,Exception error);
}
