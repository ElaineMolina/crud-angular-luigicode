package com.tutorial.crud.service;

import com.tutorial.crud.entity.Produto;
import com.tutorial.crud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> list(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> getOne(int id){
        return produtoRepository.findById(id);
    }

    public Optional<Produto> getByName(String name){
        return produtoRepository.findByName(name);
    }

    public void save(Produto produto){
        produtoRepository.save(produto);
    }

    public void delete(int id){
        produtoRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return produtoRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return produtoRepository.existsByName(name);
    }
}
