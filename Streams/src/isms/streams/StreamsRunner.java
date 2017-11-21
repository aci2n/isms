package isms.streams;

import isms.common.SafeNumberParser;

public class StreamsRunner {

	public static void main(String[] args) {
		try {
			if (args.length < 1) {
				error("No action defined");
			}

			String action = args[0];
			Streams streams = null;

			switch (action) {
			case "aggregator":
				if (args.length < 2) {
					error("Missing window size for aggregator");
				}
				streams = aggregator(args[1]);
				break;
			case "alerts":
				streams = alerts();
				break;
			default:
				error("Invalid action: %s", action);
			}

			streams.start();
		} catch (Exception e) {
			error("Unexpected exception: ", e.getMessage());
		}
	}

	private static Streams aggregator(String rawWindowSize) {
		Integer windowSize = new SafeNumberParser().parseInt(rawWindowSize);

		if (windowSize == null) {
			error("Invalid window size: %s", rawWindowSize);
		}

		return new Streams(new AggregatorTopologySupplier(windowSize));
	}

	private static Streams alerts() {
		return new Streams(new AlertEmmiterTopologySupplier());
	}

	private static void error(String message, Object... args) {
		System.err.println(String.format(message, args));
		System.exit(1);
	}
}
