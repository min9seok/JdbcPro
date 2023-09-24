package survey.controller;

import java.io.IOException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import days04.board.domain.BoardDTO;
import survey.domain.SurveyDTO;
import survey.service.SurveyService;

public class SurveyController {

	private Scanner scanner;
	private int selectedNumber;
	private SurveyService service;
	//페이징 처리 빌드
	private int currentPage = 1;
	private int numberPerPage = 10;
	private int numberOfPageBlock = 10;

	public SurveyController() {
		this.scanner = new Scanner(System.in);
	}
	public SurveyController(SurveyService service) {
		this();
		this.service = service;
	}
	//Survey Start
	public void SurveyStart() throws Exception {
		while(true) {
			메뉴출력();
			메뉴선택();
			메뉴처리();
		}
	}
	private void 메뉴처리() throws Exception {
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
		System.out.println("> 검색조건:제목(1),작성자(2) 선택좀 ");
		int searchCondition = this.scanner.nextInt();
		System.out.print("> 검색어 입력해줭 ");
		String searchWord = this.scanner.next();
		// 목록보기()
		System.out.println("> 현재 페이지 (currentPage) 번호를 입력 해");
		this.currentPage = scanner.nextInt();
		ArrayList<SurveyDTO> list = this.service.searchService(searchCondition,searchWord);
		// 뷰 출력담당
		System.out.println("\t\t\t 게시판");
		System.out.println("-".repeat(100));
		System.out.printf("%s\t%-30s  %s\t\t%s\t\t%s  %s\n","글번호","글제목","시작일","종료일","작성자","참여자");
		System.out.println("-".repeat(100));
		if(list == null) {
			System.out.println("\t\t> 게시글 좀 작성하고와 ");			
		}else {
			Iterator<SurveyDTO>  ir = list.iterator();
			while (ir.hasNext()) {
				SurveyDTO dto = ir.next();
				System.out.printf("%d\t%-30s  %s\t%s\t%s\t%d\n"
						,dto.getSeq()
						,dto.getQuestion()						
						,dto.getStart_date()
						,dto.getEnd_date()
						,dto.getUser_name()
						,dto.getReaded());			
			}
		}
		System.out.println("-".repeat(100));
		
		System.out.println("\t\t\t[1] 2 3 4 5 6 7 8 9 10 >");
		System.out.println("-".repeat(100));
		일시정지();
		
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");		
		System.out.println("> 수정할 게시글 글번호(seq)를 입력 ? ");
		int seq = this.scanner.nextInt();
		// 수정 전의 원래 게시글 정보 
		SurveyDTO dto = this.service.viewService(seq);
		if(dto == null) {
			System.out.println("> 해당 게시글 이제 없는디 ");
			return;
		}
		System.out.println("\t ㄱ. 글번호 : "+seq);
		System.out.println("\t ㄴ. 작성자 : "+dto.getUser_name());
		System.out.println("\t ㄷ. 조회수 : "+dto.getReaded());
		System.out.println("\t ㄹ. 글제목 : "+dto.getQuestion());
		System.out.println("\t ㅁ. 시작일 : "+dto.getStart_date());
		System.out.println("\t ㅂ. 종료일 : "+dto.getEnd_date());		
		System.out.println("\t ㅎ. 항목수 : "+dto.getCate_count());
		// email,title,content 수정
		System.out.println("> 제목 입력 ? ");
		String Question = this.scanner.next();		
		dto = SurveyDTO.builder().seq(seq)
				.question(Question).build();			
		int rowCount = this.service.updateService(dto);
		if(rowCount==1)System.out.printf("> %d 게시글 수정용",seq);
		일시정지();		
		
	}
	private void 상세보기() {
		System.out.println("> 게시글 글번호(seq)를 입력 ? ");
		int seq = this.scanner.nextInt();

		SurveyDTO dto = this.service.viewService(seq);
		if(dto == null) {
			System.out.println("> 해당 게시글 이제 없는디 ");
			return;
		}
		System.out.println("\t ㄱ. 글번호 : "+seq);
		System.out.println("\t ㄴ. 작성자 : "+dto.getUser_name());
		System.out.println("\t ㄷ. 조회수 : "+dto.getReaded());
		System.out.println("\t ㄹ. 글제목 : "+dto.getQuestion());
		System.out.println("\t ㅁ. 시작일 : "+dto.getStart_date());
		System.out.println("\t ㅂ. 종료일 : "+dto.getEnd_date());		
		System.out.println("\t ㅎ. 항목수 : "+dto.getCate_count());		

		System.out.println("\t\n [수정] [삭제] [목록(home)]");
		일시정지();
		
	}
	private void 목록보기() {
		System.out.println("> 현재 페이지 (currentPage) 번호를 입력 해");
		this.currentPage = scanner.nextInt();
		ArrayList<SurveyDTO> list = this.service.selectService(currentPage,numberPerPage);
		// 뷰 출력담당
		System.out.println("\t\t\t 게시판");
		System.out.println("-".repeat(100));
		System.out.printf("%s\t%-30s  %s\t\t%s\t\t%s  %s\n","글번호","글제목","시작일","종료일","작성자","참여자");
		System.out.println("-".repeat(100));
		if(list == null) {
			System.out.println("\t\t> 게시글 좀 작성하고와 ");			
		}else {
			Iterator<SurveyDTO>  ir = list.iterator();
			while (ir.hasNext()) {
				SurveyDTO dto = ir.next();
				System.out.printf("%d\t%-30s  %s\t%s\t%s\t%d\n"
						,dto.getSeq()
						,dto.getQuestion()						
						,dto.getStart_date()
						,dto.getEnd_date()
						,dto.getUser_name()
						,dto.getReaded());		
			}
		}
		System.out.println("-".repeat(100));
		String pagingBlock = this.service.pageService(
				this.currentPage,this.numberPerPage,this.numberOfPageBlock
				//검색조건 검색어
				);
		System.out.println(pagingBlock);
		System.out.println("-".repeat(100));
		일시정지();
		
	}
	private void 새글쓰기() throws Exception {
		System.out.println("> user_name , question, cate_count 입력 ? ");
		//관리자,언어언어언어,4
		String [] datas = this.scanner.next().split(",");		
		String user_name = datas[0];				
		String question = datas[1];
		int cate_count = Integer.parseInt(datas[2]);
		
		SurveyDTO dto = SurveyDTO.builder()
				.user_name(user_name).question(question).cate_count(cate_count).build();
				
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
