package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.ClienteEntity;
import com.example.demo.entity.PaqueteEntity;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.PaqueteRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ClienteRepository clienteRepositorio;
	
	@Autowired
	private PaqueteRepository paqueteRepositorio;

	
	@GetMapping("/")
	public String home() {
		return "home.html";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin.html";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("cliente2", new ClienteEntity());
		
		return "login.html";
	}
	
	//PAQUETES
	
	@GetMapping("/verPaquetes")
	public String paquetes(Model model) {
		List<PaqueteEntity> lista = paqueteRepositorio.findAll();
		model.addAttribute("listaPaquete", lista);
		
		return "verPaquetes.html";
	}
	
	@GetMapping("/paquete/formPaquete")
	public String mostrarFormularioPaquete(Model model) {
		model.addAttribute("paquete", new PaqueteEntity());
		
		List<ClienteEntity> lista = clienteRepositorio.findAll();
		model.addAttribute("listaCliente", lista);
		
		return "formPaquete";
	}
	
	@PostMapping("/guardarPaquete")
	public String guardarPaquete (PaqueteEntity paquete) {
		paqueteRepositorio.save(paquete);
		
		return "redirect:/verPaquetes";
	}
	
	@GetMapping("/paquete/editar/{id}")
	public String modificar (@PathVariable("id") Long id, Model model) {
		PaqueteEntity pa = paqueteRepositorio.findById(id).get();
		model.addAttribute("paquete", pa);
		
		List<ClienteEntity> lista = clienteRepositorio.findAll();
		model.addAttribute("listaCliente", lista);
		
		
		return "formPaquete";
	}
	
	@GetMapping("/paquete/eliminar/{id}")
	public String eliminarPaquete(@PathVariable("id") Long id, Model model) {
		paqueteRepositorio.deleteById(id);
		return "redirect:/verPaquetes";
	}
	
	//USUARIOS
	
	@GetMapping("/verUsuarios")
	public String usuarios(Model model) {
		List<ClienteEntity> lista = clienteRepositorio.findAll();
		model.addAttribute("listaUsuario", lista);
		
		return "verUsuarios.html";
	}
	
	@GetMapping("/usuario/formUsuario")
	public String mostrarFormularioUsu(Model model) {
		model.addAttribute("usuario", new ClienteEntity());
		
		List<PaqueteEntity> lista = paqueteRepositorio.findAll();
		model.addAttribute("listaPaquetes", lista);
		
		return "formUsuarios.html";
	}
	
	@PostMapping("/guardarUsuario")
	public String guardarUus (ClienteEntity usuario) {
		clienteRepositorio.save(usuario);
		
		return "redirect:/verUsuarios";
	}
	
	@GetMapping("/usuario/editar/{id}")
	public String modificarUsu (@PathVariable("id") Long id, Model model) {
		ClienteEntity pa = clienteRepositorio.findById(id).get();
		model.addAttribute("usuario", pa);
		
		List<ClienteEntity> lista = clienteRepositorio.findAll();
		model.addAttribute("listaCliente", lista);
		
		
		return "formUsuarios.html";
	}
	
	@GetMapping("/usuario/eliminar/{id}")
	public String eliminarUsu(@PathVariable("id") Long id, Model model) {
		clienteRepositorio.deleteById(id);
		return "redirect:/verUsuarios";
	}
	
	//LOGIN
	
	@PostMapping("/loguearse")
	public String loguearse(ClienteEntity cliente2, Model model) {
		ClienteEntity cliente = clienteRepositorio.findByCorreo(cliente2.getCorreo());
		
		if(cliente != null) {
			List<PaqueteEntity> lista = paqueteRepositorio.findByCliente(cliente);
			
			model.addAttribute("usuario", cliente);
			model.addAttribute("listaPaquetes", lista);
			return "usuario.html";
		}
		
		model.addAttribute("cliente2", new ClienteEntity());
		return "login.html";
	}
}
