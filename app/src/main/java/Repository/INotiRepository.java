package Repository;

public interface INotiRepository {
    void getNotiMessage(String msg, Exception error);
    void getAllNotiList();
}