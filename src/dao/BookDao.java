package dao;

import util.JDBCUtil;
import vo.BookVo;

import java.util.List;
import java.util.Map;

public class BookDao {
    private static BookDao instance;

    private BookDao() {

    }

    public static BookDao getInstance() {
        if (instance == null) {
            instance = new BookDao();
        }
        return instance;
    }
    JDBCUtil jdbc = JDBCUtil.getInstance();

    public void insert(List<Object> param) {
        String sql = "INSERT INTO BOOK VALUES ((SELECT NVL(MAX(NO),0)+1 FROM BOOK), ?, ?, ?, SYSDATE)";
        jdbc.update(sql, param);
    }

    public List<BookVo> bookList() {
        String sql = "SELECT * FROM BOOK";
        return jdbc.selectList(sql, BookVo.class);
    }

    public BookVo bookDetail(List<Object> param) {
        String sql = "SELECT * FROM BOOK" +
                "     WHERE NO = ?";
        return jdbc.selectOne(sql, param, BookVo.class);
    }

    public void holdInsert(List<Object> param) {

        String sql = "INSERT INTO BOOK_HOLD VALUES ((SELECT NVL(MAX(HOLD_NO),0)+1\n" +
                "                                    FROM BOOK_HOLD),\n" +
                "                                    ?, SYSDATE)";
        jdbc.update(sql, param);
    }

    public List<Map<String, Object>> holdList() {

        String sql = "SELECT NO, ISSN, TITLE, CONTENT, PUB_DATE, HOLD_NO, PUR_DATE\n" +
                     "FROM BOOK B, BOOK_HOLD H\n" +
                     "WHERE B.NO = H.BOOK_NO";
        return jdbc.selectList(sql);
    }

    public void holdDelete(List<Object> param) {

        String sql = " DELETE BOOK_HOLD " +
                "      WHERE HOLD_NO = ?";

        jdbc.update(sql, param);
    }

    public void bookDelete(List<Object> param) {
        String sql = " DELETE BOOK " +
                "      WHERE NO = ?";

        jdbc.update(sql, param);

    }

    public void bookUpdate(List<Object> param) {
        String sql = "UPDATE BOOK\n" +
                     "SET ISSN = ?,\n" +
                     "    TITLE = ?,\n" +
                     "    CONTENT = ?,\n" +
                     "    PUB_DATE = SYSDATE\n" +
                     "WHERE NO = ?";

        jdbc.update(sql, param);
    }
}
