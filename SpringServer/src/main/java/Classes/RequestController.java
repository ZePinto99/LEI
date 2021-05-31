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

		TemplateManager templateManager = new TemplateManager(tg.getIdJogador());

		String noticia = templateManager.getFirstTemplate(tg);


		return noticia;
	}

	@PostMapping("/getNoticia")
	public String getNoticia(@RequestBody GetNoticias tg) {

		String json = "";
		json += new Gson().toJson( tg.getNoticiaString() );
		return json;
	}

	@PostMapping("/getNoticias")
	public String getNoticias() {
		GetNoticias tg = new GetNoticias("-1", "-1");


		String json = "{ \"noticias\" : ";
		json += new Gson().toJson( tg.getNoticias());
		json += " }";
		return json;
	}

	@PostMapping("/classificate")
	public void setClassification(@RequestBody Classificate cl) {

		cl.classificate();


		return;
	}

	@PostMapping("/ChangeFinalText")
	public void setClassification(@RequestBody ChangeFinalText cft) {

		cft.changeText();


		return;
	}

}
