# Multithreading
Multithreading, Executor Framework, Concurrent Collections, Parallel Algorithms, Fork-Join Framework, MapReduce and Parallelization

## Thread Termination - Why & When
  - Thread consumes resources
    - Memory & Kernel resources
    - CPU Cycles & cache memory
  - If a thread finished its work, but the application is still running, we want to clean up the thread's resources.
  - If a thread is misbehaving, we want to stop it.
  - By default, the application will not stop as long as at least one thread is still running. 
