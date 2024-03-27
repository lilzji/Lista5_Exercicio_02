package controller;

import java.util.concurrent.Semaphore;

public class ThreadPorta extends Thread {

	private int distCorredor = 40;
	private int id;
	Semaphore mutex;
	static int posicao;

	public ThreadPorta(int id, Semaphore mutex) {
		this.id = id;
		this.mutex = mutex;
	}

	public void run() {
		corredor();
	}

	public void corredor() {
		while (distCorredor > 0) {
			int distPercorrida = (int) ((Math.random() * 3) + 4);
			if (distPercorrida < distCorredor) {
				distPercorrida = distCorredor;
			}
			distCorredor -= distPercorrida;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		synchronized (mutex) {
	        posicao++;
	        System.out.println("Pessoa " + (id + 1) + " chegou em " + posicao + "°");
	    }
		semaforo();
	}

	private void abrirPorta() {
		int tempo = (int) ((Math.random() * 1001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Pessoa " + (id + 1) + " abriu e cruzou a porta");
	}

	private void semaforo() {
		try {
			mutex.acquire();
			abrirPorta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
		}
	}

}
