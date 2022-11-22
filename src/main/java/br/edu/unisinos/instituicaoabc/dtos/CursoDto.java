package br.edu.unisinos.instituicaoabc.dtos;

import br.edu.unisinos.instituicaoabc.core.BaseDto;
import br.edu.unisinos.instituicaoabc.enums.TipoCursoEnum;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CursoDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private TipoCursoEnum tipo;

    @Override
    public String toString() {
        return super.toString();
    }

}