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
## Atomic Operation
- An operation or a set of operations is considered atomic, if it appears to the rest of the system as if it occurred at once.
-  Single step - "all or nothing"
- No intermediate states
  - item++ Not an atomic operation, internally its perform 3 operations
    - get current value of items
    - Increment current value by 1
    - stores the result into items
    - eg:
    
    ```
    items++ operation
    (int item = 0;)
    1. currentValue <- 0
    2. newValue <- currentValue + 1 = 1
    3. item <- newValue = 1
    ```
    
 - Which operations are atomic?
    - All reference assignments are atomic.
    - getters & setters
    - All assignments to primitive types are safe except long and double
    - That means reading from, and writing to the following types;
      - int, short, byte, float, char, boolean
      safely, no need to synchronization.
  - use volatile wrt long and double, bcz long & double are 64 bits
  - classes in the java.util.concurrent.atomic
  
  ## Race condition & Data Race
  - Race Condition
    - Condition when multiple threads are accessing a shared resource.
    - At least one thread is modifying the resource.
    - The timing of thread's scheduling may cause incorrect results.
    - The core of the problem is non atomic operations performed on the shared resource.
    - Synchronized solves both Race Condition and Data Race. But has a performance penality.
    - Volatile solves all data races by guaranteeing order.
  
