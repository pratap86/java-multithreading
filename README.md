# Multithreading
Multithreading, Executor Framework, Concurrent Collections, Parallel Algorithms, Fork-Join Framework, MapReduce and Parallelization

## Thread Termination - Why & When
  - Thread consumes resources
    - Memory & Kernel resources
    - CPU Cycles & cache memory
  - If a thread finished its work, but the application is still running, we want to clean up the thread's resources.
  - If a thread is misbehaving, we want to stop it.
  - By default, the application will not stop as long as at least one thread is still running.
  - Thread termination :
    - Thread.interrupt(). When can we Interrupt a Thread?
      - If the thread is executing a method that throws an InterruptedException
      - If the thread's code is handling the interrupt signal explicitly. 
    - <b>Daemon Thread</b>
      - Background threads that do not prevent the application from exiting if the main thread terminates.
## Thread Coordination - Why do we need it?
- Different threads run independently.
- Order of execution is out of our control.
- Dependency - what if one thread is depends on another thread?
- <b>Thread.join</b>
## Performance in Multithreading
- Latency - The time to completion of a task. Measured in time units.
- Throughput - The amount of task completed in a given time period. Measered in tasks/time unit.
## What is the Stack?
- Memory region where
  - Methods are called.
  - Arguments are passed.
  - Local variables are stored.
  - Local primitive types
  - Local references
- Stack + Instruction Pointer = State of each thread's execution
## What is allocated on the Heap?
- Objects(anything created with the new operator)
  - String
  - Object
  - Collection
  - ....
- Members of classes
- Static variables
- Heap governed & managed by Garbage Collector
- Objects - stay as long as we have a referenced to them.
- Members of classes - exist as long as their parent objects exist
- static variables - stay forever
