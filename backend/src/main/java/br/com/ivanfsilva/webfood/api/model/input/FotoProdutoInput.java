package br.com.ivanfsilva.webfood.api.model.input;

import br.com.ivanfsilva.webfood.core.validation.FileSize;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoInput {

    @NotNull
    @FileSize(max = "500KB")
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;

}