package pack02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //�̰� �־�� ��Ʈ�ѷ��� �ν���!
public class Cat {
	
	//��Ʈ�ѷ��� Cat Ŭ���� �ȿ� �ִ�!
	
	@RequestMapping("/t10")
	public String func10() {
		return "Catview";
	}

	@RequestMapping("/t11")
	public String func11() {
		return "/animal/Dogview";
	}

}
