package entity;

public class UserProj {

    private int userID;
    private int projID;

    public UserProj(){

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProj userProj = (UserProj) o;

        if (userID != userProj.userID) return false;
        return projID == userProj.projID;
    }

    @Override
    public int hashCode() {
        int result = userID;
        result = 31 * result + projID;
        return result;
    }

    @Override
    public String toString() {
        return "UserProj{" +
                "userID=" + userID +
                ", projID=" + projID +
                '}';
    }
}
