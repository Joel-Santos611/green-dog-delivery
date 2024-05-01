package com.jestec.greendogdelivery.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jestec.greendogdelivery.model.Cliente;
import com.jestec.greendogdelivery.repositories.ClienteRepository;

import org.springframework.web.bind.annotation.PostMapping;


@Controller  
@RequestMapping(value = "/clientes")
public class ClienteController {
@Autowired
private ClienteRepository clienteRepository;
 
@GetMapping("")
public ModelAndView list() {
    ModelAndView modelAndView=new ModelAndView("clientes/list");

    modelAndView.addObject("clientes", clienteRepository.findAll());

    return modelAndView;
}
@PostMapping("/salvar")
public String adicionar(Cliente cliente) {
this.clienteRepository.save(cliente);    
    return "redirect:/clientes";
}
@GetMapping("/novo")
public String novo() {
    System.out.println("Carregando formulario");
    return "clientes/form";
}

@GetMapping("/view/{id}")
 public String view(@PathVariable("id") Cliente cliente, Model model){
    Cliente clienteExistente=clienteRepository.findById(cliente.getId()).orElse(null);
    if (clienteExistente!=null) {
        model.addAttribute("cliente", clienteExistente);
        return "clientes/view";
    }
    return "redirect:/clientes";
 }

@GetMapping("/editar/{id}")
 public String editar(@PathVariable Long id, Cliente cliente, Model model){
    Cliente clienteExistente=clienteRepository.findById(cliente.getId()).orElse(null);
    if (clienteExistente!=null) {
        model.addAttribute("cliente", clienteExistente);
        return "clientes/edit";
    }
    return "redirect:/clientes";
 }
@PostMapping("/update/{id}")
public String atualizarCliente(@ModelAttribute Cliente cliente) {
Cliente clienteExistente=clienteRepository.findById(cliente.getId()).orElse(null);
if (clienteExistente != null) {
    // Atualizar os dados do cliente existente com os dados do formulário
    clienteExistente.setNome(cliente.getNome());
    clienteExistente.setEndereco(cliente.getEndereco());
    
    // Salvar as alterações
    clienteRepository.save(clienteExistente);
}
        return "redirect:/clientes";
    } 
@PostMapping("/delete/{id}")
public String eliminar(@PathVariable("id") Long id) {
    this.clienteRepository.deleteById(id);
    return "redirect:/clientes";
}

}
