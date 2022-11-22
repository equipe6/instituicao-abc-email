package br.edu.unisinos.instituicaoabc.dtos;

import br.edu.unisinos.instituicaoabc.core.BaseDto;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PessoaDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String cpf;

    private String nome;

    private String email;

    private LocalDate dataNascimento;

    private String telefone;

    private String endereco;

    private Long numeroEndereco;

    private String bairro;

    private Long cep;

    private String cidade;

    private String estado;


    @Override
    public String toString() {
        return super.toString();
    }
}