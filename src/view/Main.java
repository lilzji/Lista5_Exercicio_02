package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPorta;

public class Main {

	public static void main(String[] args) {
		int permissao = 1;
		Semaphore mutex = new Semaphore(permissao);
		
		for(int id = 0; id < 4; id++) {
			Thread threadPorta = new ThreadPorta(id, mutex);
			threadPorta.start();
		}
	}

}
