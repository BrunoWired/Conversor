package br.com.rotapublicitaria.conversor;

import java.text.DecimalFormat;

public class Medida {
	
	public enum Unidade {
		
		IN(1),
		
		PT(72),
		PICA(6),
		MM(25.4);
		
		private double valor;
		
		Unidade(double d) {
			this.valor = d;
		}
		
		private double converterParaUnidadeBase(double d) {
			return d / this.valor;
		}
		
		private double converterParaNovaUnidade(double d) {
			return d * this.valor;
		}
		
	}
	
	private double valor;
	private Unidade unidade;
	
	public Medida(double valor, Unidade unidade) {
		this.valor = valor;
		this.unidade = unidade;
	}
	
	public Medida converterPara(Unidade novaUnidade) {
		
		double novoValor = novaUnidade.converterParaNovaUnidade(this.unidade.converterParaUnidadeBase(this.valor));
		
		return new Medida(novoValor, novaUnidade);
	}
	
	public String getValorFormatado() {

        DecimalFormat df = new DecimalFormat("#.0000");
       	return df.format(this.valor);
	}
	
	@Override
	public String toString() {
		return getValorFormatado() + " " + this.unidade.name();
	}

}
