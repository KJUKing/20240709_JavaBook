package service;

import controller.MainController;
import dao.MemberDao;
import vo.MemberVo;

import java.util.List;

public class MemberService {

    private static MemberService instance;

    private MemberService() {

    }

    public static MemberService getInstance() {
        if (instance == null) {
            instance = new MemberService();
        }
        return instance;
    }
    MemberDao dao = MemberDao.getInstance();

//    public void freeInsert(List<Object> param) {
//        dao.freeInsert(param);
//    }
//
//    public BoardVo freeView(List<Object> param) {
//        return dao.freeView(param);
//    }
//
//    public void freeUpdate(List<Object> param, int sel) {
//        dao.freeUpdate(param, sel);
//    }
//
//    public void freeDelete(List<Object> param) {
//        dao.freeDelete(param);
//    }
//
//    public List<MemberVo> bookList() {
//        dao.bookList();
//        return booklist();
//    }

    public boolean login(List<Object> param) {
        MemberVo member = dao.login(param);
        if (member ==null) return false;
        MainController.sessionStorage.put("member", member);
        return true;
    }
}
