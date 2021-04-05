package Classes;

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
		List<String> templates = templateManager.getRelatedTemplates(tg.getName());

		String noticia = templateManager.selectTemplate(templates, new Values());

		//Method to get info

		//JsonConverter
		return noticia;
	}

}
