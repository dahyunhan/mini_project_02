package hdh.wiki.db;

import hdh.wiki.dao.BoardDAO;
import hdh.wiki.dao.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLConnector {

    private String driver = "org.mariadb.jdbc.Driver";
    private String db_name = "wiki_board";
    private String url = "jdbc:mariadb://localhost:3306/" + db_name;
    private String id_mysql = "root";
    private String pw_mysql = "";

    public Connection conn = null;

    public MySQLConnector() {
        connectMySQL();
    }

    public void connectMySQL() {
        try {
            Class.forName(driver);
            System.out.println("driver load 성공");
            conn = DriverManager.getConnection(url, id_mysql, pw_mysql);
            System.out.println("MySQL 접속 성공");

        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    /**
     * 경리 유저 테이블 데이터 전체 조회 ----------------------------------------------------------------------------------------------------
     **/
    public ArrayList<UserDAO> userAll() {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<UserDAO> uDataAll = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from USER");

            uDataAll = new ArrayList<UserDAO>();
            while (rs.next()) {
                int userIdx = rs.getInt("userIdx");
                String userId = rs.getString("userId");
                String userPw = rs.getString("userPw");
                String userName = rs.getString("userName");
                String userRole = rs.getString("userRole");
                boolean userPass = rs.getBoolean("userPass");


                uDataAll.add(new UserDAO(userIdx, userId, userPw, userName, userPass, userRole));
            }

        } catch (SQLException e) {
            System.out.println("selectAll()  ERR : " + e.getMessage());
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("close" + e.getMessage());
            }
        }

        return uDataAll;
    } // userAll() END

    /**
     * 유저 테이블 데이터 수정
     **/
    public void updateID(String idx) {
        PreparedStatement pstmt = null;
        int n = 0;
        int i = Integer.parseInt(idx);
        try {
            // 체크 요망!!!
            pstmt = conn.prepareStatement("update USER set userPass=? where userIdx=?");

            pstmt.setBoolean(1, true);
            pstmt.setInt(2, i);
            n = pstmt.executeUpdate();


            if (n > 0) {
                System.out.println(n + " 개의 RECORD update Success!!!");
            }

        } catch (SQLException e) {
            System.out.println("update()  ERR : " + e.getMessage());
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                System.out.println("close" + e.getMessage());
            }
        }

    } // update() END

    /**
     * 유저 테이블 데이터 삭제
     **/
    public void deleteUser(String idx) {
        PreparedStatement pstmt = null;
        int n = 0;
        int i = Integer.parseInt(idx);
        try {

            pstmt = conn.prepareStatement("delete from USER where userIdx=?");
            pstmt.setInt(1, i);
            n = pstmt.executeUpdate();

            if (n > 0) {
                System.out.println(n + " 개의 RECORD delete Success!!!");
            }

        } catch (SQLException e) {
            System.out.println("delete()  ERR : " + e.getMessage());
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                System.out.println("close" + e.getMessage());
            }
        }
    } // delete() END

    /**
     * 게시판 전체 조회
     **/
    public ArrayList<BoardDAO> boardAll() {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<BoardDAO> cDataAll = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from board");

            cDataAll = new ArrayList<BoardDAO>();
            while (rs.next()) {
                int conIdx = rs.getInt("contentsIdx");
                String conTitle = rs.getString("contentsTitle");
                String conOwner = rs.getString("writerId");
                String writeDate = rs.getString("writeDate");
                int hit = rs.getInt("hit");


                cDataAll.add(new BoardDAO(conIdx, conTitle, conOwner, writeDate, hit));
            }

        } catch (SQLException e) {
            System.out.println("selectAll()  ERR : " + e.getMessage());
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("close" + e.getMessage());
            }
        }

        return cDataAll;
    } // userAll() END

    /**
     * 게시판 테이블 데이터 삭제
     **/
    public void deleteCon(String idx) {
        PreparedStatement pstmt = null;
        int n = 0;
        int i = Integer.parseInt(idx);
        try {

            pstmt = conn.prepareStatement("delete from board where contentsIdx=?");
            pstmt.setInt(1, i);
            n = pstmt.executeUpdate();

            if (n > 0) {
                System.out.println(n + " 개의 RECORD delete Success!!!");
            }

        } catch (SQLException e) {
            System.out.println("delete()  ERR : " + e.getMessage());
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                System.out.println("close" + e.getMessage());
            }
        }
    } // delete() END
/** 경리  테이블 끝 ----------------------------------------------------------------------------------------------------**/

    /**
     * 준형  테이블  ----------------------------------------------------------------------------------------------------
     **/
    // 로그인ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    public boolean loginUser(String userId, String userPw) { // 로그인
        boolean loginout = false;

        try {
            String sql = "SELECT * FROM user WHERE userId = ? AND userPw = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.setString(2, userPw);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                loginout = true;
            }


            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loginout;
    }

    // 회원가입ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    public static final String pushInfo = "INSERT INTO USER ( userId,userPw,userName,userRole,userPass) " +
            "SELECT ?,?,?,?,? " +
            "FROM dual " +
            "WHERE NOT EXISTS (" +
            "SELECT 1 " +
            "FROM user " +
            "WHERE userId = ? " +
            ")";

    public int UserJoin(UserDAO user) {
        int result = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(pushInfo);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPw());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, "user");
            pstmt.setInt(5, 0);
            pstmt.setString(6, user.getUserId());
            int n = pstmt.executeUpdate();
            if (n > 0) {
                result = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                close(pstmt);
            }
        }
        return result;
    }

    // 글쓰기ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    public void insertContents(String contentsTitle, String contentsText, String writeId) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("insert into board (contentsTitle, contentsText, writerId,writeDate, editDate,hit)values (?,?,?,now(),now(),0)");
            pstmt.setString(1, contentsTitle);
            pstmt.setString(2, contentsText);
            pstmt.setString(3, writeId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            close(pstmt);
        }
    }

    // 인덱스 최대값ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    public int getMaxIdx() {
        int maxIdx = 0;
        String sql = "select max(contentsIdx) AS contentsIdx from board";
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                maxIdx = rs.getInt("contentsIdx");
                System.out.println(maxIdx);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxIdx;
    }

    /**
     * 준형  테이블 끝 ----------------------------------------------------------------------------------------------------
     **/

//--------------------------------------------정훈작성시작-----------------------------------------------
    public ArrayList<BoardDAO> selectAllList() { // 검색 게시판 목록 조회
        ArrayList<BoardDAO> boards = new ArrayList<BoardDAO>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from BOARD");

            while (rs.next()) {
                BoardDAO board = new BoardDAO();
                board.setContentsIdx(rs.getInt("contentsIdx"));
                board.setContentsTitle(rs.getString("contentsTitle"));
                board.setContentsText(rs.getString("contentsText"));
                board.setwriterId(rs.getString("WriterId"));
                board.setWriteDate(rs.getString("writeDate"));
                board.setHit(rs.getInt("hit"));
                boards.add(board);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            close(stmt, rs);
        }

        return boards;
    }

    public ArrayList<BoardDAO> selectBest3() { // 검색 게시판 목록 조회
        ArrayList<BoardDAO> boards = new ArrayList<BoardDAO>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from BOARD order by hit");

            while (rs.next()) {
                BoardDAO board = new BoardDAO();
                board.setContentsIdx(rs.getInt("contentsIdx"));
                board.setContentsTitle(rs.getString("contentsTitle"));
                board.setContentsText(rs.getString("contentsText"));
                board.setwriterId(rs.getString("WriterId"));
                board.setWriteDate(rs.getString("writeDate"));
                board.setHit(rs.getInt("hit"));
                boards.add(board);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            close(stmt, rs);
        }

        return boards;
    }


    public BoardDAO selectDetailPage(int num) {
        BoardDAO board = new BoardDAO();
        String query = "select * from  BOARD where contentsIdx = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                board.setContentsIdx(rs.getInt("contentsIdx"));
                board.setContentsTitle(rs.getString("contentsTitle"));
                board.setContentsText(rs.getString("contentsText"));
                board.setwriterId(rs.getString("WriterId"));
                board.setWriteDate(rs.getString("writeDate"));
                board.setHit(rs.getInt("hit"));
            }


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            close(pstmt, rs);
        }

        return board;
    }

    public void updateHitCount(int num) { // 테이블 데이터 수정
        String query = "update BOARD set hit = hit + 1 where contentsIdx = ?";

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            close(pstmt);
        }
    }

    public ArrayList<BoardDAO> searchKeyword(String search, String keyword) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String searchKeywordQuery = "select * from BOARD where " + search + " like concat ('%', ?, '%') order by contentsIdx";

        ArrayList<BoardDAO> boards = new ArrayList<BoardDAO>();
        ;
        try {
            pstmt = conn.prepareStatement(searchKeywordQuery);
            pstmt.setString(1, keyword);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt("contentsIdx");
                String subject = rs.getString("contentsTitle");
                String writer = rs.getString("writerId");
                String contents = rs.getString("ContentsText");
                int hit = rs.getInt("hit");
                String writeDate = rs.getString("writeDate");

                boards.add(new BoardDAO(num, subject, writer, contents, hit, writeDate));
            }

        } catch (SQLException e) {
            System.out.println("showAllList ERR : " + e.getMessage());
        }
        return boards;
    }

    public void updateContents(String contentsTitle, String contentsText, int contentsIdx) { // 수정글 수정

        PreparedStatement pstmt = null;


        try {
            pstmt = conn.prepareStatement("update board set contentsTitle=?, contentsText=? ,editDate=now() where contentsIdx=?");
            pstmt.setString(1, contentsTitle);
            pstmt.setString(2, contentsText);
            pstmt.setInt(3, contentsIdx);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("update err! : " + e.getMessage());
        }

        close(pstmt);
    }

    //--------------------------------------------정훈작성끝-----------------------------------------------

    // close ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    public void close(PreparedStatement pstmt) {
        try {
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Close SQLException: " + e.getMessage());
        }
    }

    public void close(Statement stmt, ResultSet rs) {
        try {
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Close SQLException: " + e.getMessage());
        }
    }

}