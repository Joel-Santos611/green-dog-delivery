package com.jestec.greendogdelivery.controllers;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jestec.greendogdelivery.model.Cliente;
import com.jestec.greendogdelivery.model.Pedido;
import com.jestec.greendogdelivery.repositories.ClienteRepository;
import com.jestec.greendogdelivery.repositories.ItemRepository;
import com.jestec.greendogdelivery.repositories.PedidoRepository;



@Controller  
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

	private final ItemRepository itemRepository;
	private final ClienteRepository clienteRepository;

    public PedidoController(PedidoRepository pedidoRepository,ItemRepository itemRepository,ClienteRepository clienteRepository) {
		this.pedidoRepository = pedidoRepository;
		this.itemRepository = itemRepository;
		this.clienteRepository = clienteRepository;
	}
@GetMapping("")
public ModelAndView list(){
    ModelAndView modelAndView=new ModelAndView("pedidos/list");
    modelAndView.addObject("pedidos", pedidoRepository.findAll());

    return modelAndView;
}
@GetMapping("/novo")
public String novo(){
    return "pedidos/form";
}

@GetMapping("/excluir/{id}")
	public ModelAndView remover(@PathVariable("id") Long id,RedirectAttributes redirect) {

		Optional<Pedido> pedidoParaRemoverOpt = pedidoRepository.findById(id);
		Pedido pedidoParaRemover = pedidoParaRemoverOpt.get();

		Optional<Cliente> clienteOpt = clienteRepository.findById(pedidoParaRemover.getCliente().getId());
		Cliente c = clienteOpt.get();
		  
		c.getPedidos().remove(pedidoParaRemover);

		this.clienteRepository.save(c);
		this.pedidoRepository.deleteById(id);

		Iterable<Pedido> pedidos = this.pedidoRepository.findAll();

		ModelAndView mv = new ModelAndView("pedidos/list","pedidos",pedidos);
		mv.addObject("globalMessage","Pedido removido com sucesso");

		return mv; 
	}
}
