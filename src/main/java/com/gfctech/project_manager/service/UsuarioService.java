package com.gfctech.project_manager.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gfctech.project_manager.dto.UsuarioDTO;
import com.gfctech.project_manager.entity.UsuarioEntity;
import com.gfctech.project_manager.repository.UsuarioRepository;

@Service
public class UsuarioService {


    @Autowired  
	private UsuarioRepository usuarioRepository;
	
	// @Autowired
	// private UsuarioVerificadorRepository usuarioVerificadorRepository;
	
	// @Autowired(required = true)
	// private PasswordEncoder passwordEncoder;
	
	// @Autowired
	// private EmailService emailService;
    
    public List<UsuarioDTO> listarTodos(){
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios.stream() .map(UsuarioDTO::new).toList();
	}
	
	public void inserir(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioRepository.save(usuarioEntity);
		

	}
	
	public void inserirNovoUsuario(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		// usuarioEntity. setSituacao(TipoSituacaoUsuario.PENDENTE); 
		usuarioEntity.setId(null);
		usuarioRepository.save(usuarioEntity);

    }

	public UsuarioDTO alterar(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		return new UsuarioDTO(usuarioRepository.save(usuarioEntity));
	}
	
	public void excluir(Long id) {
		UsuarioEntity usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
	}
	
	public UsuarioDTO buscarPorIdDto(Long id) {
		return new UsuarioDTO(usuarioRepository.findById(id).get());
	}
}

