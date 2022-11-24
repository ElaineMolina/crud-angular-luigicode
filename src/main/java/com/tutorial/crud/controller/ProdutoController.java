package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Message;
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

    @GetMapping("/list")
    public ResponseEntity<List<Produto>> list() {
        List<Produto> list = produtoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Produto> getById(@PathVariable("id") int id) {
        if (!produtoService.existsById(id))
            return new ResponseEntity(new Message("no exists"), HttpStatus.NOT_FOUND);
        Produto produto = produtoService.getOne(id).get();
        return new ResponseEntity(produto, HttpStatus.OK);
    }
    @GetMapping("/detailname/{name}")
    public ResponseEntity<Produto> getByName(@PathVariable("name") String name) {
        if (!produtoService.existsByName(name))
            return new ResponseEntity(new Message("não existe"), HttpStatus.NOT_FOUND);
        Produto produto = produtoService.getByName(name).get();
        return new ResponseEntity(produto, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProdutoDto produtoDto){
        if(StringUtils.isBlank(produtoDto.getName()))
            return new ResponseEntity(new Message(" o nome é obrigatorio"), HttpStatus.BAD_REQUEST);
        if(produtoDto.getPreco()==null || produtoDto.getPreco()<0)
            return new ResponseEntity(new Message(" o preço deve ser maior que 0"), HttpStatus.BAD_REQUEST);
        if(produtoService.existsByName(produtoDto.getName()))
            return new ResponseEntity(new Message(" esse nome já existe"), HttpStatus.BAD_REQUEST);
        Produto produto = new Produto(produtoDto.getName(), produtoDto.getPreco());
        produtoService.save(produto);
        return new ResponseEntity<>(new Message("Produto criado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProdutoDto produtoDto){
        if (!produtoService.existsById(id))
            return new ResponseEntity(new Message("não existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(produtoDto.getName()) && produtoService.getByName(produtoDto.getName()).get().getId()!= id)
            return new ResponseEntity(new Message(" o nome é obrigatorio"), HttpStatus.BAD_REQUEST);
        if(produtoDto.getPreco()<0)
            return new ResponseEntity(new Message(" o preço deve ser maior que 0"), HttpStatus.BAD_REQUEST);

        Produto produto = produtoService.getOne(id).get();
        produto.setName(produtoDto.getName());
        produto.setPreco(produtoDto.getPreco());
        produtoService.save(produto);
        return new ResponseEntity<>(new Message("Produto atualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!produtoService.existsById(id))
            return new ResponseEntity(new Message("Não existe"), HttpStatus.NOT_FOUND);
        produtoService.delete(id);
        return new ResponseEntity(new Message("Produto deletado"), HttpStatus.OK);
    }
}