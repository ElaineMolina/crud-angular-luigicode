package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensagem;
import com.tutorial.crud.dto.ProdutoDto;
import com.tutorial.crud.entity.Produto;
import com.tutorial.crud.service.ProdutoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Produto>> list() {
        List<Produto> list = produtoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Produto> getById(@PathVariable("id") int id) {
        if (!produtoService.existsById(id))
            return new ResponseEntity(new Mensagem("no exists"), HttpStatus.NOT_FOUND);
        Produto produto = produtoService.getOne(id).get();
        return new ResponseEntity(produto, HttpStatus.OK);
    }
    @GetMapping("/detailname/{nome}")
    public ResponseEntity<Produto> getByNome(@PathVariable("nome") String nome) {
        if (!produtoService.existsByNome(nome))
            return new ResponseEntity(new Mensagem("no exists"), HttpStatus.NOT_FOUND);
        Produto produto = produtoService.getByNome(nome).get();
        return new ResponseEntity(produto, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProdutoDto produtoDto){
        if(StringUtils.isBlank(produtoDto.getNome()))
            return new ResponseEntity(new Mensagem(" o nome é obrigatorio"), HttpStatus.BAD_REQUEST);
        if(produtoDto.getPreco()<0)
            return new ResponseEntity(new Mensagem(" o preço deve ser maior que 0"), HttpStatus.BAD_REQUEST);
        if(ProdutoService.existsByNome(produtoDto.getNome()))
            return new ResponseEntity(new Mensagem(" esse nome já existe"), HttpStatus.BAD_REQUEST);
        Produto produto = new Produto(produtoDto.getNome(), produtoDto.getPreco());
        ProdutoService.save(produto);
        return new ResponseEntity<>(new Mensagem("Produto criado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProdutoDto produtoDto){
        if (!produtoService.existsById(id))
            return new ResponseEntity(new Mensagem("no exists"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(produtoDto.getNome()) && produtoService.getByNome(produtoDto.getNome()).get().getId()!= id)
            return new ResponseEntity(new Mensagem(" o nome é obrigatorio"), HttpStatus.BAD_REQUEST);
        if(produtoDto.getPreco()<0)
            return new ResponseEntity(new Mensagem(" o preço deve ser maior que 0"), HttpStatus.BAD_REQUEST);

        Produto produto = produtoService.getOne(id).get();
        produto.setNome(produtoDto.getNome());
        produto.setPreco(produtoDto.getPreco());
        ProdutoService.save(produto);
        return new ResponseEntity<>(new Mensagem("Produto atualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!produtoService.existsById(id))
            return new ResponseEntity(new Mensagem("Não existe"), HttpStatus.NOT_FOUND);
        produtoService.delete(id);
        return new ResponseEntity(new Mensagem("Produto deletado"), HttpStatus.OK);
    }
}