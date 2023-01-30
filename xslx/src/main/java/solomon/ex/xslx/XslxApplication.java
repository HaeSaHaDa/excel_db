package solomon.ex.xslx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import solomon.ex.xslx.dao.ConnectDao;

@SpringBootApplication
public class XslxApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(XslxApplication.class, args);
		InsertExcute ie = new InsertExcute();
		ie.Insert();
	}

}
