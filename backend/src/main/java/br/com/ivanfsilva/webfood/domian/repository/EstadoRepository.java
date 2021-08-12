package br.com.ivanfsilva.webfood.domian.repository;

import br.com.ivanfsilva.webfood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Estado estado);

}