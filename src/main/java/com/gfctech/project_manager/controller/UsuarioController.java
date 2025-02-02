package com.gfctech.project_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfctech.project_manager.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin(origins = "http://localhost:4000")
public class UsuarioController
 {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO>ListarTodos()
    {
        return usuarioService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody UsuarioDTO usuario)
    {
        usuarioService.inserir(usuario);
    }

    @PutMapping
    public void alterar(@RequestBody UsuarioDTO usuario)
    {
        return usuarioService.alterar(usuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id)
    {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
