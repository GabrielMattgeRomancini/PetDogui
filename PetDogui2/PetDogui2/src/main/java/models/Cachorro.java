package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cachorro {
    private Long id;
    private String nomeCachorro;
    private int tamanho;
    private boolean temPerfume;
    private Date dataNascimento;
    private Raca raca;
    private Cor cor;
    private Pelagem pelagem;
}
