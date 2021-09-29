package br.com.ivanfsilva.webfood.api.assembler;

import br.com.ivanfsilva.webfood.api.model.GrupoModel;
import br.com.ivanfsilva.webfood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoModel toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModel.class);
    }

    public List<GrupoModel> toCollectionModel(List<Grupo> grupos) {
        return grupos.stream()
                .map(grupo -> toModel(grupo))
                .collect(Collectors.toList());
    }
}