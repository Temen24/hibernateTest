package dao;

import entity.UserProj;

import java.util.List;

public interface UserProjDAO {
    void add(UserProj userProj);

    List<UserProj> getAll();
    UserProj getByUserIdAndProjectId(int userId, int projId);

    void update(UserProj userProj);

    void delete(UserProj userProj);
}
