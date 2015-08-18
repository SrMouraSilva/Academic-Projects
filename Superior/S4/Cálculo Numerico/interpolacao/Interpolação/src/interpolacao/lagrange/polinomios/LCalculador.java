package interpolacao.lagrange.polinomios;

import matematica.polinomio.Polinomio;
import matematica.polinomio.Monomio;
import interpolacao.util.coordenadas.Coordenadas;

public class LCalculador {
	private int index;

	public LCalculador(int index) {
		this.index = index;
	}
	
	public L calcularPara(Coordenadas coordenadas, double xInterpolador) {
		String concatenaNumerador = "";
		String concatenaDenominador = "";
		
		String concatenaNumerador2 = "";
		String concatenaDenominador2 = "";

		double denominador = 1;
		
		Polinomio numerador = Polinomio.unitario();

		for (int j=0; j<coordenadas.size(); j++) {
			if (index != j) {
				double Xi = coordenadas.getCoordenada(index).x();
				double Xj = coordenadas.getCoordenada(j).x();

				Polinomio polinomio = Polinomio.nulo()
				   .add(new Monomio(-Xj, 0))
				   .add(new Monomio(1, 1));

				numerador = numerador.vezes(polinomio);
				denominador *= Xi - Xj;
				concatenaNumerador   += "(x - x" + j +") *"; 
				concatenaDenominador += "(x"+ index + " - x" + j + ") *";
				
				concatenaNumerador2   += "(" + polinomio + ")*"; 
				concatenaDenominador2 += "("+ Xi + "-" + Xj+") *";
			}
		}

		concatenaNumerador   += " = " + concatenaNumerador2;
		concatenaDenominador += " = " + concatenaDenominador2;

		concatenaNumerador   += " = " + numerador;
		concatenaDenominador += " = " + denominador;

		return new L(index, numerador, denominador, concatenaNumerador, concatenaDenominador);
	}
}
