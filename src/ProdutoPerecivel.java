import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ProdutoPerecivel extends Produto {

    private static final double DESCONTO = 0.25;
    private static final int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate validade) {
        super(desc, precoCusto, margemLucro);
        if (validade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de validade não pode ser anterior à data atual.");
        }

        this.dataDeValidade = validade;
    }

    @Override
    public double valorDeVenda() {

        double desconto = 0.0;
        int diasValidade = LocalDate.now().until(dataDeValidade).getDays();
        if (diasValidade <= PRAZO_DESCONTO) {
            desconto = DESCONTO;
        }
        return (precoCusto * (1.0 + margemLucro)) * (1.0 - desconto);

    }

    @Override
    public String toString() {

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dados = super.toString();
        dados += " - Validade: " + formatador.format(dataDeValidade);

        return dados;
    }
}
