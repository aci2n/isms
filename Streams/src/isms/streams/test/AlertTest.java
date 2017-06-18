package isms.streams.test;

import isms.streams.AlertEmmiterTopologySupplier;
import isms.streams.Streams;

public class AlertTest {

	public static void main(String[] args) {
		System.out.println("Launching alert emitting stream.");
		Streams streams = new Streams(new AlertEmmiterTopologySupplier());
		streams.start();
	}
}
