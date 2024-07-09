package controller;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import service.BookService;
import service.MemberService;
import util.ScanUtil;
import view.View;
import vo.BookVo;
import vo.MemberVo;

import static view.View.*;

public class MainController{
	static public Map<String, Object> sessionStorage = new HashMap<>();

	MemberService memberService = MemberService.getInstance();
	BookService bookService = BookService.getInstance();

	public static void main(String[] args) {
		new MainController().start();
	}
	
	private void start() {
		View view = View.MAIN;
		while (true) {
			switch (view) {
				case MAIN:
					view = main();
					break;
				case LOGIN:
					view = login();
					break;
				case ADMIN:
					view = admin();
					break;
				case BOOK_MAIN:
					view = bookMain();
					break;
				case BOOK_INSERT:
					view = bookInsert();
					break;
				case BOOK_LIST:
					view = bookList();
					break;
				case BOOK_DETAIL:
					view = bookDetail();
					break;
				case BOOK_DELETE:
					view = bookDelete();
					break;
				case BOOK_UPDATE:
					view = bookUpdate();
					break;
				case HOLD_MAIN:
					view = holdMain();
					break;
				case HOLD_INSERT:
					view = holdInsert();
					break;
				case HOLD_LIST:
					view = holdList();
					break;
				case HOLD_DELETE:
					view = holdDelete();
					break;

				default:
					break;

			}
		}
	}

	private View bookUpdate() {
		System.out.println("1. 전체 수정");
		System.out.println("2. 게시판 리스트");
		int sel = ScanUtil.menu();
		if (sel == 1) {
			int no = (int)sessionStorage.get("bookNo");
			List<Object> param = new ArrayList<>();
			String issn = ScanUtil.nextLine("ISSN : ");
			param.add(issn);
			String title = ScanUtil.nextLine("TITLE : ");
			param.add(title);
			String content = ScanUtil.nextLine("CONTENT : ");
			param.add(content);
			param.add(no);
			bookService.bookUpdate(param);
		}

		if (sel == 2) {
			return View.BOOK_LIST;
		}
			return View.BOOK_DETAIL;
	}

	private View bookDelete() {
		int bookNo = (int) sessionStorage.get("bookNo");
		List<Object> param = new ArrayList<>();
		param.add(bookNo);
		bookService.bookDelete(param);
		return BOOK_LIST;
	}

	private View holdDelete() {
		int bookNo = (int) sessionStorage.get("bookNo");
		List<Object> param = new ArrayList<>();
		param.add(bookNo);
		bookService.holdDelete(param);
		return HOLD_LIST;
	}

	private View holdList() {
		List<Map<String, Object>> list = bookService.holdList();
		for (Map<String, Object> map : list) {
			System.out.println(map);

		}
		System.out.println("1. 도서 보유");

		return HOLD_MAIN;
	}

	private View holdInsert() {
		List<BookVo> bookList = bookService.bookList();
		for (BookVo bookVo : bookList) {
			System.out.println(bookVo);
		}
		int bookNo = ScanUtil.nextInt("책 번호 : ");
		List<Object> param = new ArrayList<>();
		param.add(bookNo);

		bookService.holdInsert(param);
		return HOLD_LIST;
	}

	private View holdMain() {
		System.out.println("1. 도서 구매");
		System.out.println("2. 도서 파기");
		System.out.println("3. 보유 도서 리스트 조회");
		System.out.println("4. 관리자 홈");
		int sel = ScanUtil.menu();
		switch (sel) {
			case 1:
				return HOLD_INSERT;
			case 2:
				int bookNo = ScanUtil.nextInt("보유 도서 번호 : ");
				sessionStorage.put("bookNo", bookNo);
				return HOLD_DELETE;
			case 3:
				return HOLD_LIST;
			case 4:
				return ADMIN;
			default:
				return HOLD_MAIN;
		}
	}

	private View bookDetail() {
		int bookNo = (int) sessionStorage.get("bookNo");
		List<Object> param = new ArrayList<>();
		param.add(bookNo);
		BookVo bookVo = bookService.bookDetail(param);
		System.out.println(bookVo);
		System.out.println();
		System.out.println("1. 도서 삭제");
		System.out.println("2. 도서 변경");
		System.out.println("3. 도서 리스트");
		int sel = ScanUtil.menu();
		switch (sel) {
			case 1:
				return BOOK_DELETE;
			case 2:
				return View.BOOK_UPDATE;
			case 3:
				return View.BOOK_MAIN;
			default:
				return View.BOOK_DETAIL;
		}

	}

	private View admin() {
		System.out.println("1. 도서정보");
		System.out.println("2. 도서 보유 정보");
		int sel = ScanUtil.menu();
		switch (sel) {
			case 1:
				return BOOK_MAIN;
			case 2:
				return View.HOLD_MAIN;
			default:
				return View.ADMIN;
		}
	}

	private View bookList() {
		List<BookVo> bookList = bookService.bookList();
		for (BookVo bookVo : bookList) {
			System.out.println(bookVo);
		}
		System.out.println("1. 상세조회");
		System.out.println("2. 도서 정보");
		int sel = ScanUtil.nextInt("입력 : ");
		switch (sel) {
			case 1: int bookNo = ScanUtil.nextInt("책 번호 : ");
					sessionStorage.put("bookNo", bookNo);
				return View.BOOK_DETAIL;
			case 2: return View.BOOK_LIST;
			default: return BOOK_MAIN;
		}
	}

	private View bookInsert() {

		List<Object> param = new ArrayList<>();
		String issn = ScanUtil.nextLine("ISSN : ");
		String title = ScanUtil.nextLine("제목 : ");
		String content = ScanUtil.nextLine("내용 : ");

		param.add(issn);
		param.add(title);
		param.add(content);
		bookService.insert(param);

		return View.BOOK_LIST;
	}

	private View bookMain() {

		System.out.println("1. 도서 등록");
		System.out.println("2. 도서 리스트");
		System.out.println("3. 관리자 홈");
		int sel = ScanUtil.menu();
		switch (sel) {
			case 1: return View.BOOK_INSERT;
			case 2: return View.BOOK_LIST;
			case 3: return View.ADMIN;
			default: return BOOK_MAIN;
		}
	}

	private View login() {
		String id = ScanUtil.nextLine("ID : ");
		String pw = ScanUtil.nextLine("PW : ");
		List<Object> param = new ArrayList<>();
		param.add(id);
		param.add(pw);
		boolean login = memberService.login(param);
		if (!login) {
			System.out.println("1. 재로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 홈");
			return View.LOGIN;
		}
		return View.ADMIN;
	}


//	private View freeDelete() {
//		int no = (int)sessionStorage.get("BoardNo");
//		List<Object> param = new ArrayList<>();
//		param.add(no);
//		freeService.freeDelete(param);
//		return View.FREE_LIST;
//	}

//	private View freeUpdate() {
//		int no = (int)sessionStorage.get("BoardNo");
//
//		System.out.println("1. 제목 수정");
//		System.out.println("2. 내용 수정");
//		System.out.println("3. 작성자 수정");
//		System.out.println("4. 전체 수정");
//		System.out.println("5. 게시판 리스트");
//		int sel = ScanUtil.menu();
//		if (sel == 5) {
//			return View.FREE_LIST;
//		}
//		List<Object> param = new ArrayList<>();
//		if (sel == 1 || sel == 4) {
//			String title = ScanUtil.nextLine("제목 : ");
//			param.add(title);
//		}if (sel == 2 || sel == 4) {
//			String content = ScanUtil.nextLine("내용 : ");
//			param.add(content);
//		}if (sel == 3 || sel == 4) {
//			String writer = ScanUtil.nextLine("작성자 : ");
//			param.add(writer);
//		}
//		param.add(no);
//		freeService.freeUpdate(param, sel);
//
//		return View.FREE_VIEW;
//	}



//	private View freeInsert() {
//		System.out.println("게시판 등록");
//		String title = ScanUtil.nextLine("제목 : ");
//		String content = ScanUtil.nextLine("내용 : ");
//		String writer = ScanUtil.nextLine("작성자 : ");
//		List<Object> param = new ArrayList<Object>();
//		param.add(title);
//		param.add(content);
//		param.add(writer);
//		freeService.freeInsert(param);
//
//		return View.FREE_LIST;
//	}

//	private View freeList() {
//		List<BoardVo> boardList = freeService.freeList();
//		for (BoardVo boardVo : boardList) {
//
//			int no = boardVo.getNo();
//			String title = boardVo.getTitle();
//			String content = boardVo.getContent();
//			String writer = boardVo.getWriter();
//			String wDate = boardVo.getW_date();
//			System.out.println(no+"\t"+title+"\t"+content+"\t"+writer+"\t"+wDate);
//
//		}
//		int no = ScanUtil.nextInt("상세 조회 게시판 번호 : ");
//		sessionStorage.put("BoardNo", no);
//		return View.FREE_VIEW;
//	}


	public View main() {
		System.out.println("1. 로그인");
		System.out.println("2. 메뉴로");

		int sel = ScanUtil.nextInt("메뉴 선택: ");
		switch (sel) {
			case 1:
				return View.LOGIN;
			case 2:
				return BOOK_MAIN;
			default:
				return View.MAIN;
		}
	}

}
