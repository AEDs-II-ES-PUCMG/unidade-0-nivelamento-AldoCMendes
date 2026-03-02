
public class ProdutoNaoPerecivel extends Produto {

	public ProdutoNaoPerecivel(String desc, double precoCusto, double margemLucro) {
		super(desc, precoCusto, margemLucro);
	}

	public ProdutoNaoPerecivel(String desc, double precoCusto) {
		super(desc, precoCusto);
	}

	@Override
	public double valorDeVenda() {
		return super.valorDeVenda();
	}

	@Override
	public String gerarDadosTexto() {
		String PrecoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
		String MargemFormatada = String.format("%.2f", margemLucro).replace(",", ".");
		return String.format("1; %s; %s; %s", getDescricao(), PrecoFormatado, MargemFormatada);
	}

}
