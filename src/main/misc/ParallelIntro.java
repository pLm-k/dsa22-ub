package main.misc;

import java.util.concurrent.Semaphore;

public final class ParallelIntro implements Runnable{
	
	private final Semaphore semaphore = new Semaphore(1);
	private int counter = 0;
	private int mCounter = 0;
	private int sCounter = 0;

	@Override
	public final void run() {
		for(int i = 0; i < 100000; i++) {
			
			this.counter++;
			
			try {
				this.semaphore.acquire();
				this.sCounter++;
				this.semaphore.release();
			} catch (InterruptedException e) {
				System.out.println("Interrupted!");
			}
			
			synchronized(this) {
				this.mCounter++;
			}
		}
	}

	
	public static void main(String[] args) {
		ParallelIntro parallelObject = new ParallelIntro();
		
		Thread t1 = new Thread(parallelObject);
		Thread t2 = new Thread(parallelObject);
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
		}
		
		System.out.println("No sync:		" + parallelObject.counter);
		System.out.println("Sync with semaphore:	" + parallelObject.mCounter);
		System.out.println("Sync with monitor:	" + parallelObject.sCounter);
	}
}
