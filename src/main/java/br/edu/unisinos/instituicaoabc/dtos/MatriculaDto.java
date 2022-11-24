package br.edu.unisinos.instituicaoabc.dtos;

import br.edu.unisinos.instituicaoabc.core.BaseDto;
import br.edu.unisinos.instituicaoabc.enums.TipoCursoEnum;
import br.edu.unisinos.instituicaoabc.enums.TurnoEnum;
import lombok.*;

/**
 * Classe DTO para objeto Matrícula.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatriculaDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private Long id;

    //Curso

    private Long idCurso;

    private String nomeCurso;

    private TipoCursoEnum tipoCurso;

    //Pessoa
    private Long idPessoa;

    private String cpf;

    private String nome;

    private String email;

    private String dataNascimento;

    private String telefone;

    private String endereco;

    private Long numeroEndereco;

    private String bairro;

    private Long cep;

    private String cidade;

    private String estado;

    private TurnoEnum turno;


    @Override
    public String toString() {
        return super.toString();
    }
}