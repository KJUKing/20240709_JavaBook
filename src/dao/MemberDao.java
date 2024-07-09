package dao;

import util.JDBCUtil;
import vo.MemberVo;

import java.util.List;

public class MemberDao {

    private static MemberDao instance;

    private MemberDao() {

    }

    public static MemberDao getInstance() {
        if (instance == null) {
            instance = new MemberDao();
        }
        return instance;
    }
    JDBCUtil jdbc = JDBCUtil.getInstance();

    public MemberVo login(List<Object> param) {
        String sql = "SELECT *\n" +
                "FROM JAVA_MEMBER\n" +
                "WHERE MEM_ID = ?\n" +
                "AND MEM_PASS = ?";

        return jdbc.selectOne(sql, param, MemberVo.class);
    }
//
//    public List<BoardVo> freeList() {
//        String sql = "SELECT NO, TITLE, CONTENT, WRITER, W_DATE\n" +
//                "FROM BOARD\n" +
//                "WHERE DELYN = 'N'";
//
//        return jdbc.selectList(sql, BoardVo.class);
//    }
//
//    public void freeInsert(List<Object> param) {
//        String sql = "INSERT INTO BOARD (NO,TITLE,CONTENT,WRITER)\n" +
//                "VALUES ((SELECT NVL(MAX(NO),0)+1 FROM BOARD), ?, ?, ?)";
//
//        jdbc.update(sql, param);
//
//    }
//
//    public BoardVo freeView(List<Object> param) {
//        String sql = "SELECT NO, TITLE, CONTENT, WRITER, W_DATE\n" +
//                "FROM BOARD\n" +
//                "WHERE NO = ?";
//        return jdbc.selectOne(sql, param, BoardVo.class);
//    }
//
//    public void freeUpdate(List<Object> param, int sel) {
//
//        String sql = "UPDATE BOARD " +
//                "     SET  ";
//        if (sel == 1 || sel == 4) {
//            sql += " TITLE = ?";
//            if (sel == 4) sql += ", ";
//        }
//        if (sel == 2 || sel == 4) {
//            sql += " CONTENT = ?";
//            if (sel == 4) sql += ", ";
//        }if (sel == 3 || sel == 4) {
//            sql += " WRITER = ?";
//        }
//        sql += " WHERE NO = ? ";
//
//        jdbc.update(sql, param);
//    }
//
//
//    public void freeDelete(List<Object> param) {
//
//        String sql = " UPDATE BOARD " +
//                "      SET DELYN = 'Y'" +
//                "      WHERE NO = ?";
//
//        jdbc.update(sql, param);
//    }
//
//    public void bookList() {
//    }
}
