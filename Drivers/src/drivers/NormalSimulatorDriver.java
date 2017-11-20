package drivers;

import java.util.Random;

public class NormalSimulatorDriver extends Driver {

	private Random random;
	private double mean;
	private double std;
	private int sleep;

	public NormalSimulatorDriver(double mean, double std, int sleep) {
		this.random = new Random();
		this.mean = mean;
		this.std = std;
		this.sleep = sleep;
	}

	@Override
	public void poll() {
		try {
			while (true) {
				double value = simulate();

				System.out.println("simulating: " + value);
				trigger(value);
				Thread.sleep(sleep);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private double simulate() {
		return mean + random.nextGaussian() * std;
	}
}
