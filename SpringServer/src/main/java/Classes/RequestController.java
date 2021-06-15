package Classes;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class RequestController {




	@PostMapping("/getInfo")
	public String getInfo(@RequestBody Tags tg) {
		int standartSize = 50;
		List<String> noticias = new ArrayList<>();
		//gerar 3 notícias com diferentes tamanhos
		for (int i=1; i<=3; i++){
			TemplateManager templateManager = new TemplateManager(tg.getIdJogador(), standartSize);

			noticias.add(templateManager.getFirstTemplate(tg));
		}
		//gerar link de classificação

		//Enviar as notícias geradas por email
		SendEmail sendEmail = new SendEmail();

		//Criar corpo de notícia
		String content = "As notícias geradas são:";
		for (String noticia : noticias)
			content += "\n\n" + noticia;
		String finalContent = content;
		////////////////////////
		//Proceder ao envio do email
		List<String> tosend = Arrays.asList("testeLEIgrupo58@gmail.com"); //Lista de emails que vão receber a mensagem
		for (String tosend_email : tosend) {
			Runnable runnable = () -> {
				sendEmail.run("testeLEIgrupo58@gmail.com", finalContent, "Notícia gerada");
			};
			Thread t = new Thread(runnable);
			t.start();
		}
		///////////////////////

		return noticias.toString();
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
