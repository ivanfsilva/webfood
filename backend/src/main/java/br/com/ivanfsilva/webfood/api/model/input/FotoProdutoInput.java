package br.com.ivanfsilva.webfood.api.model.input;

import br.com.ivanfsilva.webfood.core.validation.FileContentType;
import br.com.ivanfsilva.webfood.core.validation.FileSize;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoInput {

    @ApiModelProperty(hidden = true)
    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    private MultipartFile arquivo;

    @ApiModelProperty(value = "Descrição da foto do produto", required = true)
    @NotBlank
    private String descricao;

}