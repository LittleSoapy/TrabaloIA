package algoritmo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

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
		// sensores para Modelo
		posicao = sensor.getPosicao();
		moedas = sensor.getNumeroDeMoedas();
		visao = vetor2GridV(sensor.getVisaoIdentificacao());
		cheiroP = vetor2GridC(sensor.getAmbienteOlfatoPoupador());
		cheiroL = vetor2GridC(sensor.getAmbienteOlfatoLadrao());
		campo = visao2Campo(visao, campo, posicao);
		// sensores para Modelo Fim

		ArrayList<Integer> acoes = new ArrayList<Integer>();
		acoes = eliminarParedes(acoes);
		acoes = perseguirPoupador(acoes);

		if (acoes.size() == 0) {
			return 0;
		} else {
			Random rand = new Random();
			int num = rand.nextInt(acoes.size());
			return acoes.get(num);
		}
	}

	private ArrayList<Integer> perseguirPoupador(ArrayList<Integer> acoes) {
		Point poupador = null;
		for (int lin = 0; lin < visao.length; lin++) {
			for (int col = 0; col < visao.length; col++) {
				if (visao[col][lin] >= 100 && visao[col][lin] < 200) {
					poupador = new Point(col, lin);
				}
			}
		}

		if (poupador == null) {

			Point cheiroFortePoint = null;
			int cheiroForteNum = 0;
			for (int lin = 0; lin < cheiroP.length; lin++) {
				for (int col = 0; col < cheiroP.length; col++) {
					if (cheiroP[col][lin] > cheiroForteNum) {
						cheiroFortePoint = new Point(col, lin);
					}
				}
			}
			if (cheiroFortePoint == null) {
				return acoes;
			} else {
				if (cheiroFortePoint.x == 0 && cheiroFortePoint.y == 0) {
					acoes.remove((Integer) 2);
					acoes.remove((Integer) 3);
				} else if (cheiroFortePoint.x == 1 && cheiroFortePoint.y == 0) {
					acoes.remove((Integer) 2);
					acoes.remove((Integer) 3);
					acoes.remove((Integer) 4);
				} else if (cheiroFortePoint.x == 2 && cheiroFortePoint.y == 0) {
					acoes.remove((Integer) 2);
					acoes.remove((Integer) 4);
				} else if (cheiroFortePoint.x == 1 && cheiroFortePoint.y == 0) {
					acoes.remove((Integer) 1);
					acoes.remove((Integer) 2);
					acoes.remove((Integer) 3);
				} else if (cheiroFortePoint.x == 1 && cheiroFortePoint.y == 2) {
					acoes.remove((Integer) 1);
					acoes.remove((Integer) 2);
					acoes.remove((Integer) 4);
				} else if (cheiroFortePoint.x == 2 && cheiroFortePoint.y == 0) {
					acoes.remove((Integer) 1);
					acoes.remove((Integer) 3);
				} else if (cheiroFortePoint.x == 2 && cheiroFortePoint.y == 1) {
					acoes.remove((Integer) 1);
					acoes.remove((Integer) 3);
					acoes.remove((Integer) 4);
				} else if (cheiroFortePoint.x == 2 && cheiroFortePoint.y == 2) {
					acoes.remove((Integer) 1);
					acoes.remove((Integer) 4);
				}
				return acoes;
			}
		} else {
			if ((poupador.x == 0 && poupador.y == 0) || (poupador.x == 1 && poupador.y == 0)
					|| (poupador.x == 0 && poupador.y == 1) || (poupador.x == 1 && poupador.y == 1)) {
				acoes.remove((Integer) 2);
				acoes.remove((Integer) 3);
			} else if ((poupador.x == 2 && poupador.y == 0) || (poupador.x == 2 && poupador.y == 1)) {
				acoes.remove((Integer) 2);
				acoes.remove((Integer) 3);
				acoes.remove((Integer) 4);
			} else if ((poupador.x == 3 && poupador.y == 0) || (poupador.x == 4 && poupador.y == 0)
					|| (poupador.x == 3 && poupador.y == 1) || (poupador.x == 4 && poupador.y == 1)) {
				acoes.remove((Integer) 2);
				acoes.remove((Integer) 4);
			} else if ((poupador.x == 0 && poupador.y == 2) || (poupador.x == 1 && poupador.y == 2)) {
				acoes.remove((Integer) 1);
				acoes.remove((Integer) 2);
				acoes.remove((Integer) 3);
			} else if ((poupador.x == 3 && poupador.y == 2) || (poupador.x == 4 && poupador.y == 2)) {
				acoes.remove((Integer) 1);
				acoes.remove((Integer) 2);
				acoes.remove((Integer) 4);
			} else if ((poupador.x == 0 && poupador.y == 3) || (poupador.x == 1 && poupador.y == 3)
					|| (poupador.x == 0 && poupador.y == 4) || (poupador.x == 1 && poupador.y == 4)) {
				acoes.remove((Integer) 1);
				acoes.remove((Integer) 3);
			} else if ((poupador.x == 2 && poupador.y == 3) || (poupador.x == 2 && poupador.y == 4)) {
				acoes.remove((Integer) 1);
				acoes.remove((Integer) 3);
				acoes.remove((Integer) 4);
			} else if ((poupador.x == 3 && poupador.y == 3) || (poupador.x == 4 && poupador.y == 3)
					|| (poupador.x == 3 && poupador.y == 4) || (poupador.x == 4 && poupador.y == 4)) {
				acoes.remove((Integer) 1);
				acoes.remove((Integer) 4);
			}
			return acoes;
		}
	}

	private ArrayList<Integer> eliminarParedes(ArrayList<Integer> acoes) {
		if (visao[2][1] != -1 && visao[2][1] != 1 && visao[2][1] != 3 && visao[2][1] != 4 && visao[2][1] != 5
				&& !(visao[2][1] >= 200 && visao[2][1] < 300)) {
			acoes.add(1);
		}
		if (visao[2][3] != -1 && visao[2][3] != 1 && visao[2][3] != 3 && visao[2][3] != 4 && visao[2][3] != 5
				&& !(visao[2][3] >= 200 && visao[2][3] < 300)) {
			acoes.add(2);
		}
		if (visao[3][2] != -1 && visao[3][2] != 1 && visao[3][2] != 3 && visao[3][2] != 4 && visao[3][2] != 5
				&& !(visao[3][2] >= 200 && visao[3][2] < 300)) {
			acoes.add(3);
		}
		if (visao[1][2] != -1 && visao[1][2] != 1 && visao[1][2] != 3 && visao[1][2] != 4 && visao[1][2] != 5
				&& !(visao[1][2] >= 200 && visao[1][2] < 300)) {
			acoes.add(4);
		}
		return acoes;
	}

	private int[][] vetor2GridV(int[] vetor) {
		int[][] grid = new int[5][5];
		int aux = 0;
		for (int lin = 0; lin < grid.length; lin++) {
			for (int col = 0; col < grid.length; col++) {
				if (col == 2 && lin == 2) {
					grid[col][lin] = 300;
				} else {
					grid[col][lin] = vetor[aux];
					aux++;
				}
			}
		}
		return grid;
	}

	private int[][] vetor2GridC(int[] vetor) {
		int[][] grid = new int[3][3];
		int aux = 0;
		for (int lin = 0; lin < grid.length; lin++) {
			for (int col = 0; col < grid.length; col++) {
				if (col == 1 && lin == 1) {
					grid[col][lin] = -1;
				} else {
					grid[col][lin] = vetor[aux];
					aux++;
				}
			}
		}
		return grid;
	}

	private int[][] visao2Campo(int[][] visao, int[][] campo, Point posicao) {
		for (int lin = -2; lin <= 2; lin++) {
			for (int col = -2; col <= 2; col++) {
				if (!(visao[col + 2][lin + 2] < 0)) {
					if (!(posicao.x + col < 0 || posicao.x + col >= 30 || posicao.y + lin < 0
							|| posicao.y + lin >= 30)) {
						if (visao[col + 2][lin + 2] >= 200 && visao[col + 2][lin + 2] < 300) {
							campo[posicao.x + col][posicao.y + lin] = 0;
						} else {
							campo[posicao.x + col][posicao.y + lin] = visao[col + 2][lin + 2];
						}
					}
				}
			}
		}
		return campo;
	}
}