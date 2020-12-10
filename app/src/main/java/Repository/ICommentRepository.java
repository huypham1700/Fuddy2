package Repository;

import java.util.ArrayList;
import java.util.List;

import Entity.Comment;

public interface ICommentRepository {

    public void getCommentLimit(List<Comment> arrayListComment);

    public void getCommentData(ArrayList<Comment> arrayListComment, Exception error);
}
