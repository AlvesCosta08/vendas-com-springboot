package com.gvendas.gestao_vendas.controller.categoria;

import com.gvendas.gestao_vendas.entidades.Categoria;
import com.gvendas.gestao_vendas.servicos.CategoriaServico;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

    @Autowired
    private CategoriaServico categoriaServico;

    @GetMapping
    public List<Categoria> listarTodas(){
        return categoriaServico.listarTodas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable Long codigo){
        Optional<Categoria> categoria = categoriaServico.buscarPorCodigo(codigo);
        return categoria.isPresent()? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Categoria>salvar(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaServico.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }


    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria>atualizar(@PathVariable Long codigo ,@RequestBody Categoria categoria) {       
        return ResponseEntity.ok(categoriaServico.atualizar(codigo, categoria));
    }  
        
}
