import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.IHolaMundo;


public class Principal {

	public static void main(String[] args) {
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		IHolaMundo holaMundo = ctx.getBean("holaMundo", IHolaMundo.class);
		holaMundo.saludar();
	}

}
