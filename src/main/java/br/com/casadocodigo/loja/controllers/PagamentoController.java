package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.modelo.CarrinhoCompras;
import br.com.casadocodigo.loja.modelo.DadosPagamento;
import br.com.casadocodigo.loja.modelo.Usuario;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
	
	@Autowired
	private CarrinhoCompras carrinhoCompras;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MailSender sender;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String cadastro() {
		return "cadastro";
	}
	
	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(//@org.springframework.security.core.annotation.AuthenticationPrincipal 
			Usuario usuario, RedirectAttributes redirectAttributes) {
		
		//@AuthenticationPrincipal - usuario autenticadp é injetado
		//Callable - assincrono. Faz com que o tomcat libere os outros usuários enquanto essa thread
		//fica aguardando. Essa requisição de confirmação de compra	pode demorar um pouco casa esteja
		//enviado muitos dados o que causa lentidão, porque o servidor ficaria esperando a finalização
		//e somente quando sai deste metodo ele libera o servidor
		
		return () -> {
			
			try {
				String uri = "http://book-payment.herokuapp.com/payment";
				//precisa dessa classe para enviar um 'value' - JSON
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinhoCompras.getTotal()), 
						String.class); //mensagem de retorno
				
				sendSuccessEmail(usuario);
				carrinhoCompras.clear();
				
				redirectAttributes.addFlashAttribute("message", response + "! Você receberá um e-mail com os dados da compra. ");
				return new ModelAndView("redirect:/");
				
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				String msg = "Compra não aprovada. " + e.getResponseBodyAsString();
				
				redirectAttributes.addFlashAttribute("message", msg);
				return new ModelAndView("redirect:/");
			}
		};
	}

	private void sendSuccessEmail(Usuario usuario) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra finalizada com sucesso");
		email.setTo(usuario.getEmail());
		//email.setTo("guilhermefsouza@hotmail.com");
		email.setText("Compra aprovada com sucesso no valor de : R$" + carrinhoCompras.getTotal());
		email.setFrom("compras@casadocodigo.com.br");
		
		sender.send(email);
	}
}
