package br.com.academy.luizpedro.mercadolivre.controller;
import br.com.academy.luizpedro.mercadolivre.dto.UsuarioRequest;
import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import br.com.academy.luizpedro.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = usuarioRequest.toModel();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().body("Usuário cadastrado com sucesso");
    }
}
