package pack01;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import db.OracleDB;


@Controller
public class Tiger {
	
	//전송 세번째 방식
	@RequestMapping("/t1")
	public String func01(Apple apple) { //커넨드
		System.out.println(apple.getName());
		System.out.println(apple.getAge());
		return "Tigerview";
	}
	
	@RequestMapping("/t5")
	public String func05() {
		//db data Access(데이터 엑세스 )
		return "Tigerview";
	}
	
	@RequestMapping("/t9")
	public String func09() {
		//db data Access(데이터 엑세스 )
		return "Lionview";
	}
	
	//배열을 인수로 전달받음
//	@RequestMapping("/t10")
//	public String func10(Model model) {
//		//db data Access(데이터 엑세스 )
//		String[] ar = {"tiger","lion","fox","rabbit"};
//		model.addAttribute("ar",ar);
//		return "Tigerview";
//	}
	
//	//객체를 인수로 전달받음 
//	@RequestMapping("/t11")
//	public String func11(Model model) {
//		//db data Access(데이터 엑세스 )
//		Apple apple = new Apple("호랑이",30);
//		model.addAttribute("apple",apple);
//		return "Tigerview";
//	}
//	
//	
//	
//	@RequestMapping("/t12")
//	public String func12(Model model) {
//		//db data Access(데이터 엑세스 )
//		Apple apple = new Apple("호랑이",30);
//		model.addAttribute("apple",apple);
//		return "Tigerview";
//	}
//	
	
	
	
	Apple[] apple = {new Apple("호랑이",10),new Apple("코끼리",20),new Apple("호랑2",30),new Apple("코뿔소",40)};
	
	@RequestMapping("/t12")
	public ModelAndView t1() {
		ModelAndView mv = new ModelAndView("Tigerview");
		mv.addObject("apple", apple);
		return mv;
	}
	
	//자료를 관리할 수 있는 것들의 집합 -> 컬렉션
	//컬렉션 사용하기 
	
	@RequestMapping("/t13")
	public String func13(Model model) {
		LinkedList<String> Li = new LinkedList<String>();
		Li.add("호랑이");
		Li.add("코끼리");
		Li.add("독수리");
		model.addAttribute("Li",Li);
		return "Lionview";
	}
	
	
	//String 타입 대신 Apple 객체 타입으로 List 출력
	
	
	@RequestMapping("/t14")
	public String func14(Model model) {
		LinkedList<Apple> Li = new LinkedList<Apple>();
		Li.add(new Apple("호랑이",10));
		Li.add(new Apple("코알라",20));
		Li.add(new Apple("얼룩말",30));
		
		model.addAttribute("Li",Li);
		return "Lionview";
	}
	
	
	
	//팀별 과제 
	
//	@RequestMapping("/t15")
//	public String func15(Model model) {
//		//1.오라클 db 접속
//		//2.데이터 select
//		//3.오라클 db 접속 종료
//		//4.view로 데이터 전송
//		
//		//db 접속 -> 데이터를 가져와서 -> view로 데이터 전송
//		return "Lionview";
//	}
	
	
	
	
	
	
	
	
	
	OracleDB db = new OracleDB();

	@RequestMapping("/t15")
	public String t123(Model model) {
		List<Apple> appleArray = new ArrayList<Apple>();
		System.out.println(12345);
		ResultSet rs = db.executeQuery("select * from appleTable");
		System.out.println(rs.toString());
		try {
			while (rs.next()) {
				appleArray.add(new Apple(rs.getString("name"), rs.getInt("age")));
			}

		} catch (SQLException e) {
			e.toString();
		}
		System.out.println(appleArray.toString());
		model.addAttribute("array", appleArray);
		return "AppleView";

	}
	
//	@RequestMapping("/t16")
//	public String update(HttpServletRequest req){
//		System.out.println(req.getAttribute("name"));
//		System.out.println(req.getAttribute("age"));
//		System.out.println(1234);
////	    db.executeUpdateQuery(
////	    		"update appletable set age=" + apple.getAge() +
////	    		"where name =" + "'" + apple.getName() + "'");
//		return "BananaView";
//	}
	
	@RequestMapping("/t16")
	public String insert(Apple apple) { // 커넨드
		System.out.println(apple.getName());
		System.out.println(apple.getAge());

		db.executeUpdateQuery(
				" insert into appletable values( " + "'" + apple.getName() + "'" + ", " + apple.getAge() + ") ");

		return "BananaView";
	}
	
	@RequestMapping("/t17")
	public String update(Apple apple) { // 커넨드
		System.out.println(apple.getName());
		System.out.println(apple.getAge());

		db.executeUpdateQuery(
	            " update appletable set age = " + apple.getAge() + 
	            " where name = " + "'" + apple.getName() +"'");
	
		return "OrangeView";
	}
	
	
	@RequestMapping("/t18")
	public String delete(Apple apple) { // 커넨드
		System.out.println(apple.getName());
		System.out.println(apple.getAge());

		db.executeUpdateQuery(
	            "delete from appletable where name = " + "'" + apple.getName() +"'");
		return "MelonView";
	}
	
	
	
	
	
	public void close() {
		db.close();
	}
	
	



	
	
	
}
