package Controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ServidorController extends Thread {
	int _threadId;
	static Semaphore s1 = new Semaphore(1);
	public ServidorController(int threadId) {
		this._threadId = threadId;
	}
	
	public void run() {
		try {
			ServidorController.s1.acquire();
			this.calc();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ServidorController.s1.release();
		}
	}
	
	public void DbCalc(int sleepMinMili, int sleepMaxMili, int sleepTransactionMili) throws InterruptedException {
		Random rnd = new Random();
		System.out.println(String.format("Thread: %d \n", this._threadId));
		int sleepMult = rnd.nextInt(sleepMinMili, sleepMaxMili);
		
		System.out.println(String.format("Thread %d %s", this._threadId, "Realizando calculo"));			
		Thread.sleep(sleepMult);
		System.out.println(String.format("Thread %d %s", this._threadId, "Calculo realizado"));
		
		System.out.println(String.format("Thread %d %s", this._threadId, "Realizando transação"));
		Thread.sleep(sleepTransactionMili);
		System.out.println(String.format("Thread %d %s", this._threadId, "Transação realizada"));
	}
	
	public void calc() {
		if((this._threadId % 3) == 1) {
			try {
				this.DbCalc(200, 1000, 1000);
				this.DbCalc(200, 1000, 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		} 
		
		if((this._threadId % 3) == 2){
			try {
				this.DbCalc(500, 1500, 1500);
				this.DbCalc(500, 1500, 1500);
				this.DbCalc(500, 1500, 1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}
		
		if((this._threadId % 3) == 0) {
			try {
				this.DbCalc(1000, 2000, 1500);
				this.DbCalc(1000, 2000, 1500);
				this.DbCalc(1000, 2000, 1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}
	}
	
}
