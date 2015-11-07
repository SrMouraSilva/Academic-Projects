package br.edu.ifce.estrutura.arvore;

public class ArvoreBinaria<E> {

	private No<E> raiz;

	public void inserir(int chave, E valor) {
		No<E> aInserir = new No<>(chave, valor);
		
		if (raiz == null) {
			raiz = aInserir;
			return;
		}

		No<E> no = raiz;
		No<E> anterior = null;

		do {
			boolean chaveJaExistente = no.chave == aInserir.chave; 
			if (chaveJaExistente) {
				no.valor = aInserir.valor;
				return;
			}

			anterior = no;
			no = no.chave < aInserir.chave ? no.direita : no.esquerda;

		} while (no != null);


		aInserir.pai = anterior;
		if (anterior.chave < chave)
			anterior.direita = aInserir;
		else 
			anterior.esquerda = aInserir;
	}

	public E buscar(int chave) {
		No<E> no = buscar(chave, raiz);
		
		return no == null ? null : no.valor;
	}

	private No<E> buscar(int chave, No<E> no) {
		if (no == null || chave == no.chave)
			return no;

		no = chave > no.chave ? no.direita : no.esquerda;

		return buscar(chave, no);
	}

	public E min() {
		No<E> menor = minDe(raiz);
		return menor == null ? null : menor.valor;
	}

	public No<E> minDe(No<E> noh) {
		return extrema(
			noh,
			no -> no.esquerda != null,
			no -> no.esquerda
		);
	}

	public E max() {
		No<E> maior = maxDe(raiz);
		return maior == null ? null : maior.valor;
	}

	public No<E> maxDe(No<E> noh) {
		return extrema(
			noh,
			no -> no.direita != null,
			no -> no.direita
		);
	}

	private No<E> extrema(No<E> raiz, Condicao condicao, Acao<E> acao) {
		if (raiz == null)
			return raiz;

		No<E> no = raiz;
		while (condicao.isVerdadeira(no))
			no = acao.acao(no);

		return no;
	}

	public E sucessor(int chave) {
		No<E> no = buscar(chave, raiz);
		No<E> sucessor = noSucessorDe(no);

		return sucessor == null ? null : sucessor.valor;
	}

	private No<E> noSucessorDe(No<E> no) {
		if (no == null)
			return null;

		if (no.direita != null)
			return minDe(no.direita);

		while (no.isFilhoADireita()) 
			no = no.pai;

		return no.pai;
	}

	public E antecessor(int chave) {
		No<E> no = buscar(chave, raiz);
		No<E> antecessor = noAntecessorDe(no);

		return antecessor == null ? null : antecessor.valor;
	}
	
	private No<E> noAntecessorDe(No<E> no) {
		if (no == null)
			return null;

		if (no.esquerda != null)
			return maxDe(no.esquerda);

		while (no.isFilhoAEsquerda()) 
			no = no.pai;

		return no.pai;
	}
}