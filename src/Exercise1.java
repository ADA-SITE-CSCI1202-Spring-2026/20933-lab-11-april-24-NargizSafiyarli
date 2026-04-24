public class Exercise1 {
        static class MathTask implements Runnable {
            private final int threadId;
            private final long iterations;

            public MathTask(int threadId, long iterations) {
                this.threadId = threadId;
                this.iterations = iterations;
            }

            @Override
            public void run() {
                double result = 0;

                for (long i = 0; i < iterations; i++) {
                    result += Math.pow(i, 3) + i * threadId;
                }

                System.out.println("Thread " + threadId + " finished. Result = " + result);
            }
        }

        public static void main(String[] args) throws InterruptedException {

            int coreCount = Runtime.getRuntime().availableProcessors();

            System.out.println("Available logical processors: " + coreCount);

            runTest(1);
            runTest(coreCount);
        }

        private static void runTest(int threadCount) throws InterruptedException {

            Thread[] threads = new Thread[threadCount];

            long iterationsPerThread = 10_000_000L;

            long startTime = System.currentTimeMillis();

            for (int i = 0; i < threadCount; i++) {
                threads[i] = new Thread(new MathTask(i + 1, iterationsPerThread));
                threads[i].start();
            }

            for (int i = 0; i < threadCount; i++) {
                threads[i].join();
            }

            long endTime = System.currentTimeMillis();

            System.out.println("Time with " + threadCount + " thread(s): "
                    + (endTime - startTime) + " ms");
        }
    }

