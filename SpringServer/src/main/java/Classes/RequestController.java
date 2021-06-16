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
		int standartSize = 40;
		List<Noticia> noticias = new ArrayList<>();
		//gerar 3 notícias com diferentes tamanhos
		for (int i=1; i<=3; i++){
			TemplateManager templateManager = new TemplateManager(tg.getIdJogador(), standartSize);

			noticias.add(templateManager.getFirstTemplate(tg));
		}


		//Lista de endereços de email a enviar
		List<String> tosend = Arrays.asList("testeLEIgrupo58@gmail.com"); //Lista de emails que vão receber a mensagem

		//Criar o conteúdo do email se as notícias tiverem sido criadas com sucesso
		String content = "";
		if (!noticias.isEmpty()){
			content = noticias.get(0).titulo + "\n";
			for (Noticia noticia : noticias)
				content += noticia.toString() + "\n\n";
		}

		//Enviar as notícias geradas por email
		SendEmail sendEmail = new SendEmail();
		//Proceder ao envio do email
		for (String tosend_email : tosend) {
			String finalContent = content;
			Runnable runnable = () -> {
				sendEmail.run(tosend_email, finalContent, "Notícia gerada");
			};
			Thread t = new Thread(runnable);
			t.start();
		}

		return content;
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
