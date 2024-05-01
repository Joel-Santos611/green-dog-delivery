package com.jestec.greendogdelivery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jestec.greendogdelivery.model.Item;
import com.jestec.greendogdelivery.repositories.ItemRepository;



@Controller  
@RequestMapping(value = "/itens")
public class ItemController {
@Autowired
private ItemRepository itemRepository; 


@GetMapping("")
public ModelAndView list() {
    ModelAndView modelAndView=new ModelAndView("itens/list");

    modelAndView.addObject("item", itemRepository.findAll());

    return modelAndView;
}
@PostMapping("/salvar")
public String adicionar(Item item) {
this.itemRepository.save(item);    
    return "redirect:/itens";
}
@GetMapping("/novo")
public String novo() {
    System.out.println("Carregando formulario");
    return "itens/form";
}

@GetMapping("/view/{id}")
 public String view(@PathVariable("id") Item item, Model model){
    Item itemExistente=itemRepository.findById(item.getId()).orElse(null);
    if (itemExistente!=null) {
        model.addAttribute("item", itemExistente);
        return "itens/view";
    }
    return "redirect:/clientes";
 }

@GetMapping("/editar/{id}")
 public String editar(@PathVariable Long id, Item item, Model model){
    Item itemExistente=itemRepository.findById(item.getId()).orElse(null);
    if (itemExistente!=null) {
        model.addAttribute("item", itemExistente);
        return "itens/edit";
    }
    return "redirect:/itens";
 }
@PostMapping("/update/{id}")
public String atualizarCliente(@ModelAttribute Item item) {
Item clienteExistente=itemRepository.findById(item.getId()).orElse(null);
if (clienteExistente != null) {
    // Atualizar os dados do cliente existente com os dados do formulário
    clienteExistente.setNome(item.getNome());
    clienteExistente.setPreco(item.getPreco());
    
    // Salvar as alterações
    itemRepository.save(clienteExistente);
}
        return "redirect:/itens";
    } 
@PostMapping("/delete/{id}")
public String eliminar(@PathVariable("id") Long id) {
    this.itemRepository.deleteById(id);
    return "redirect:/itens";
}

}
