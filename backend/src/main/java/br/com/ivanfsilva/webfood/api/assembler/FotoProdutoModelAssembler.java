package br.com.ivanfsilva.webfood.api.assembler;

import br.com.ivanfsilva.webfood.api.model.FotoProdutoModel;
import br.com.ivanfsilva.webfood.domain.model.FotoProduto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModel toModel(FotoProduto foto) {
        return modelMapper.map(foto, FotoProdutoModel.class);
    }

}
