package msa.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.config.Message;
import msa.application.config.enumerator.Esito;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.user.UserLoggedDTO;
import msa.application.service.domain.DomainService;
import msa.infrastructure.persistence.NazioniDBO;
import msa.web.api.BaseController;

@RestController
@RequestMapping(value="/api/login")
public class LoginController extends BaseController {

	@Autowired
	DomainService domainService;
	
	@ApiOperation(value="Metodo che effettua la chiamata al servizio di login")
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public BaseDTO<List<NazioniDBO>> getUserInfo(){
		BaseDTO<List<NazioniDBO>> output = new BaseDTO<List<NazioniDBO>>();
		
		//TEST user
		UserLoggedDTO user = new UserLoggedDTO();
		user.setUsername("Bottalicog");
		user.setNome("Gianluca");
		user.setCognome("Bottalico");
		user.setRuolo("ADMIN");
		
		List<NazioniDBO> nazioni = domainService.getElencoNazioni();
		
		
		output.setEsito(Esito.OK);
		output.setResult(nazioni);
		
		//test messaggi
		Message messaggio = new Message(MessageType.INFO, "test avvenuto con successo");
		output.getMessaggi().add(messaggio);
		
		return output;
	}
	
}
