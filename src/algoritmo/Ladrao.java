package algoritmo;

import java.awt.Point;

public class Ladrao extends ProgramaLadrao {

	private int[][] campo;
	private int[][] visao;

	private int[][] cheiroP;
	private int[][] cheiroL;

	private Point posicao;
	private int moedas;
	
	

	public Ladrao() {
		visao = new int[5][5];
		cheiroP = new int[5][5];
		cheiroL = new int[5][5];
		
		campo = new int[30][30];
		for (int lin = 0; lin < campo.length; lin++) {
			for (int col = 0; col < campo.length; col++) {
				campo[col][lin] = -2;
			}
		}
		
	}

	public int acao() {
		//sensores para Modelo
		posicao = sensor.getPosicao();
		moedas = sensor.getNumeroDeMoedas();
		visao = vetor2Grid(sensor.getVisaoIdentificacao());
		cheiroP = vetor2Grid(sensor.getAmbienteOlfatoPoupador());
		cheiroL = vetor2Grid(sensor.getAmbienteOlfatoLadrao());
		campo = visao2Campo(visao, campo, posicao);
		//sensores para Modelo Fim
		
		

		return 0;
	}

	private int[][] vetor2Grid(int[] vetor) {
		int[][] grid = new int[5][5];
		int aux = 0;
		for (int lin = 0; lin < grid.length; lin++) {
			for (int col = 0; col < grid.length; col++) {
				if (vetor.length == 8 && lin >= 1 && lin <= 3 && col >= 1 && col <= 3) {
					if (col == 2 && lin == 2) {
						visao[col][lin] = 200;
					} else {
						visao[col][lin] = vetor[aux];
						aux++;
					}
				} else {
					if (vetor.length == 24) {
						if (col == 2 && lin == 2) {
							visao[col][lin] = 200;
						} else {
							visao[col][lin] = vetor[aux];
							aux++;
						}
					}
				}
			}
		}
		return grid;
	}

	private int[][] visao2Campo(int[][] visao, int[][] campo, Point posicao) {
		for (int lin = -2; lin <= 2; lin++) {
			for (int col = -2; col <= 2; col++) {
				if (!(visao[col + 2][lin + 2] < 0)) {
					campo[posicao.x + col][posicao.y + lin] = visao[col + 2][lin + 2];
					
				}
			}
		}

		return campo;
	}
}