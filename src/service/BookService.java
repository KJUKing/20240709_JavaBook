package service;

import dao.BookDao;
import vo.BookVo;

import java.util.List;
import java.util.Map;

public class BookService {

    private static BookService instance;

    private BookService() {

    }
    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }
    BookDao dao = BookDao.getInstance();

    public void insert(List<Object> param) {
        dao.insert(param);
    }

    public List<BookVo> bookList() {
        return dao.bookList();
    }

    public BookVo bookDetail(List<Object> param) {
        return dao.bookDetail(param);
    }

    public void holdInsert(List<Object> param) {
        dao.holdInsert(param);
    }

    public List<Map<String, Object>> holdList() {
        return dao.holdList();
    }

    public void holdDelete(List<Object> param) {
        dao.holdDelete(param);
    }

    public void bookDelete(List<Object> param) {
        dao.bookDelete(param);
    }

    public void bookUpdate(List<Object> param) {
        dao.bookUpdate(param);
    }
}
