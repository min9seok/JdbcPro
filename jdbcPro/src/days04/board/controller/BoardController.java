package days04.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import days04.board.domain.BoardDTO;
import days04.board.service.BoardService;

public class BoardController {

	private Scanner scanner;
	private int selectedNumber;
	private BoardService service;

	//페이징 처리 빌드
	private int currentPage = 1;
	private int numberPerPage = 10;
	private int numberOfPageBlock = 10;

	public BoardController() {
		this.scanner = new Scanner(System.in);
	}
	public BoardController(BoardService service) {
		this();
		this.service = service;
	}

	// Board Start
	public void boardStart() {
		while(true) {
			메뉴출력();
			메뉴선택();
			메뉴처리();
		}
	}
	private void 메뉴처리() {
		switch (this.selectedNumber) {
		case 1: //새글
			새글쓰기();
			break;
		case 2:	//목록
			목록보기();
			break;
		case 3:	//보기
			상세보기();
			break;
		case 4:	//수정
			수정하기();
			break;
		case 5:	//삭제
			삭제하기();
			break;
		case 6:	//검색
			검색하기();
			break;
		case 7:	//종료		
			exit();
			break;
		default:
			break;
		}
	}
	private void 검색하기() {


	}
	private void 삭제하기() {
		System.out.println("> 게시글 글번호(seq)를 입력 ? ");
		int seq = this.scanner.nextInt();
		int rowCount = this.service.deleteService(seq);
		if(rowCount == 0) {
			System.out.println("> 삭제할 자료가 없는데용 ");
		}else {
			System.out.printf("> %d번 삭제를 해봤어용 \n",seq);
		}
		
		일시정지();
		
	}
	private void 수정하기() {
		System.out.println("> writer,pwd,email,title,tag,content 입력 ? ");

	}
	private void 상세보기() {
		System.out.println("> 게시글 글번호(seq)를 입력 ? ");
		int seq = this.scanner.nextInt();

		BoardDTO dto = this.service.viewService(seq);
		if(dto == null) {
			System.out.println("> 해당 게시글 이제 없는디 ");
			return;
		}
		System.out.println("\t ㄱ. 글번호 : "+seq);
		System.out.println("\t ㄴ. 작성자 : "+dto.getWriter());
		System.out.println("\t ㄷ. 조회수 : "+dto.getReaded());
		System.out.println("\t ㄹ. 글제목 : "+dto.getTitle());
		System.out.println("\t ㅁ. 글내용 : "+dto.getContent());
		System.out.println("\t ㅂ. 작성일 : "+dto.getWritedate());

		System.out.println("\t\n [수정] [삭제] [목록(home)]");
		일시정지();
	}
	private void 목록보기() {
		System.out.println("> 현재 페이지 (currentPage) 번호를 입력 해");
		this.currentPage = scanner.nextInt();
		ArrayList<BoardDTO> list = this.service.selectService();
		// 뷰 출력담당
		System.out.println("\t\t\t 게시판");
		System.out.println("-".repeat(100));
		System.out.printf("%s\t%-30s  %s\t%-10s\t%s\n","글번호","글제목","글쓴이","작성일","조회수");
		System.out.println("-".repeat(100));
		if(list == null) {
			System.out.println("\t\t> 게시글 좀 작성하고와 ");			
		}else {
			Iterator<BoardDTO>  ir = list.iterator();
			while (ir.hasNext()) {
				BoardDTO dto = ir.next();
				System.out.printf("%d\t%-30s  %s\t%-10s\t%d\n"
						,dto.getSeq()
						,dto.getTitle()
						,dto.getWriter()
						,dto.getWritedate()
						,dto.getReaded());			
			}
		}
		System.out.println("-".repeat(100));
		System.out.println("\t\t\t[1] 2 3 4 5 6 7 8 9 10 >");
		System.out.println("-".repeat(100));
		일시정지();
	}
	private void 새글쓰기() {
		System.out.println("> writer,pwd,email,title,tag,content 입력 ? ");
		//임긍재,1234,im@sist.co.kr,제목,0,내용
		String [] datas = this.scanner.next().split(",");
		String writer = datas[0];
		String pwd = datas[1];
		String email = datas[2];
		String title = datas[3];
		int tag = Integer.parseInt(datas[4]);
		String content = datas[5];

		BoardDTO dto = BoardDTO.builder()
				.writer(writer).pwd(pwd).email(email).title(title).tag(tag).content(content).build();
		int rowCount = this.service.insertService(dto);
		if(rowCount==1)System.out.println("> 새글을 써버렷네용 ");
		일시정지();
	}
	private void 메뉴선택() {
		System.out.println("> 메뉴 선택하세요 ? ");
		this.selectedNumber = this.scanner.nextInt();
	}
	private void 메뉴출력() {
		String [] menus = {"새글","목록","보기","수정","삭제","검색","종료"};
		System.out.println(" [ 메뉴 ] ");
		for (int i = 0; i < menus.length; i++) {
			System.out.printf("%d. %s\t",i+1,menus[i]);
		}
		System.out.println();
	}
	private void 일시정지() {
		System.out.println(" \t\t 계속하려면 엔터치세요.");
		try {
			System.in.read();
			System.in.skip(System.in.available()); // 13, 10
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	private void exit() {
		DBConn.close();
		System.out.println("\t\t\t  프로그램 종료!!!");
		System.exit(-1);
	}

}
