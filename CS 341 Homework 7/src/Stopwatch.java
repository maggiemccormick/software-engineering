public class Stopwatch {
	
		private long elapsedTime;
		private long startTime;
		private boolean timing;

		public Stopwatch() {
		}

		// getter
		public long getElapsedTime() {
			if (timing) {
				long endTime = System.currentTimeMillis();
				return elapsedTime + endTime - startTime;
			} else {
				return elapsedTime;
			}
		}

		// computers
		public void start() {
			if (timing) {
				return;
			}
			timing = true;
			startTime = System.currentTimeMillis();
		}

		public void stop() {
			if (!timing) {
				return;
			}
			timing = false;
			long endTime = System.currentTimeMillis();
			elapsedTime = elapsedTime + endTime - startTime;
		}

		public void reset() {
			elapsedTime = 0;
			timing = false;
		}

	}