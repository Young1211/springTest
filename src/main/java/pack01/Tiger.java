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
	
	//���� ����° ���
	@RequestMapping("/t1")
	public String func01(Apple apple) { //Ŀ�ٵ�
		System.out.println(apple.getName());
		System.out.println(apple.getAge());
		return "Tigerview";
	}
	
	@RequestMapping("/t5")
	public String func05() {
		//db data Access(������ ������ )
		return "Tigerview";
	}
	
	@RequestMapping("/t9")
	public String func09() {
		//db data Access(������ ������ )
		return "Lionview";
	}
	
	//�迭�� �μ��� ���޹���
//	@RequestMapping("/t10")
//	public String func10(Model model) {
//		//db data Access(������ ������ )
//		String[] ar = {"tiger","lion","fox","rabbit"};
//		model.addAttribute("ar",ar);
//		return "Tigerview";
//	}
	
//	//��ü�� �μ��� ���޹��� 
//	@RequestMapping("/t11")
//	public String func11(Model model) {
//		//db data Access(������ ������ )
//		Apple apple = new Apple("ȣ����",30);
//		model.addAttribute("apple",apple);
//		return "Tigerview";
//	}
//	
//	
//	
//	@RequestMapping("/t12")
//	public String func12(Model model) {
//		//db data Access(������ ������ )
//		Apple apple = new Apple("ȣ����",30);
//		model.addAttribute("apple",apple);
//		return "Tigerview";
//	}
//	
	
	
	
	Apple[] apple = {new Apple("ȣ����",10),new Apple("�ڳ���",20),new Apple("ȣ��2",30),new Apple("�ڻԼ�",40)};
	
	@RequestMapping("/t12")
	public ModelAndView t1() {
		ModelAndView mv = new ModelAndView("Tigerview");
		mv.addObject("apple", apple);
		return mv;
	}
	
	//�ڷḦ ������ �� �ִ� �͵��� ���� -> �÷���
	//�÷��� ����ϱ� 
	
	@RequestMapping("/t13")
	public String func13(Model model) {
		LinkedList<String> Li = new LinkedList<String>();
		Li.add("ȣ����");
		Li.add("�ڳ���");
		Li.add("������");
		model.addAttribute("Li",Li);
		return "Lionview";
	}
	
	
	//String Ÿ�� ��� Apple ��ü Ÿ������ List ���
	
	
	@RequestMapping("/t14")
	public String func14(Model model) {
		LinkedList<Apple> Li = new LinkedList<Apple>();
		Li.add(new Apple("ȣ����",10));
		Li.add(new Apple("�ھ˶�",20));
		Li.add(new Apple("��踻",30));
		
		model.addAttribute("Li",Li);
		return "Lionview";
	}
	
	
	
	//���� ���� 
	
//	@RequestMapping("/t15")
//	public String func15(Model model) {
//		//1.����Ŭ db ����
//		//2.������ select
//		//3.����Ŭ db ���� ����
//		//4.view�� ������ ����
//		
//		//db ���� -> �����͸� �����ͼ� -> view�� ������ ����
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
	public String insert(Apple apple) { // Ŀ�ٵ�
		System.out.println(apple.getName());
		System.out.println(apple.getAge());

		db.executeUpdateQuery(
				" insert into appletable values( " + "'" + apple.getName() + "'" + ", " + apple.getAge() + ") ");

		return "BananaView";
	}
	
	@RequestMapping("/t17")
	public String update(Apple apple) { // Ŀ�ٵ�
		System.out.println(apple.getName());
		System.out.println(apple.getAge());

		db.executeUpdateQuery(
	            " update appletable set age = " + apple.getAge() + 
	            " where name = " + "'" + apple.getName() +"'");
	
		return "OrangeView";
	}
	
	
	@RequestMapping("/t18")
	public String delete(Apple apple) { // Ŀ�ٵ�
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
