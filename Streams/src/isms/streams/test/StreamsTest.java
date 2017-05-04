package isms.streams.test;

import isms.streams.AggregatorTopologyProvider;
import isms.streams.Streams;

public class StreamsTest {

	public static void main(String[] args) {
		Streams streams = new Streams(new AggregatorTopologyProvider());
		streams.start();
	}
}
