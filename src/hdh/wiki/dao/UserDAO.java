package hdh.wiki.dao;

public class UserDAO {
    private int userIdx;
    private String userId;
    private String userPw;
    private String userName;
    private boolean userPass;
    private String userRole;

    public UserDAO() {

    }

    public UserDAO(int userIdx, String userId, String userPw, String userName, boolean userPass, String userRole) {
        super();
        this.userIdx = userIdx;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userPass = userPass;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserDAO [userIdx=" + userIdx + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
                + ", userPass=" + userPass + ", userRole=" + userRole + "]";
    }

    public int getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isUserPass() {
        return userPass;
    }

    public void setUserPass(boolean userPass) {
        this.userPass = userPass;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}