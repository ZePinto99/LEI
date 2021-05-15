package Classes;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {




	@PostMapping("/getInfo")
	public String getInfo(@RequestBody Tags tg) {

		System.out.println("--------" + tg.getName());
		TemplateManager templateManager = new TemplateManager();

		String noticia = templateManager.getFirstTemplate(tg.getName());


		//Method to get info
		System.out.println(noticia);
		//JsonConverter
		return noticia;
	}

	@PostMapping("/getNoticia")
	public String getNoticia(@RequestBody GetNoticias tg) {

		System.out.println("----------------");
		String json = "{ \"noticia\" : ";
		json += new Gson().toJson( tg.getNoticiaString() );
		json += " }";
		return json;
	}

	@PostMapping("/getNoticias")
	public String getNoticias() {
		GetNoticias tg = new GetNoticias("-1", "-1");
		System.out.println("----------------");


		String json = "{ \"noticia\" : ";
		json += new Gson().toJson( tg.getNoticias());
		json += " }";
		return json;
	}

}
