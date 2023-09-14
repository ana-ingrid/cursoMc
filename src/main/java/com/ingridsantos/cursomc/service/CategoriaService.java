package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.dto.CategoriaDTO;
import com.ingridsantos.cursomc.exceptions.IntegridadeDataException;
import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;
import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.repository.CategoriaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository repository) {
        this.categoriaRepository = repository;
    }

    public Categoria consultaCategoriaId(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
    }

    public Categoria salvaCategoria(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria alteraCategoria(Categoria obj, Integer id) {
        Categoria categoria = consultaCategoriaId(id);
        atualizaDados(obj, categoria);
        return categoriaRepository.save(categoria);
    }

    private void atualizaDados(Categoria newObj, Categoria obj) {
        obj.setNome(newObj.getNome());
    }


    public void deletaCategoria(Integer id) {
        consultaCategoriaId(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new IntegridadeDataException("Não é possível excluir uma categoria que possui produtos adicionados");
        }
    }

    public List<Categoria> consultaCategorias() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> consultaPaginada(Integer pagina, Integer linhasPagina, String tipo, String ordem){
            PageRequest pageRequest = PageRequest.of(pagina, linhasPagina, Sort.Direction.valueOf(tipo), ordem);
            return categoriaRepository.findAll(pageRequest);
    }

    public Categoria conversaoDTO(CategoriaDTO objDto){
        return new Categoria(objDto.getNome());
    }

}
