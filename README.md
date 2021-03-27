# Multithreading
Multithreading, Executor Framework, Concurrent Collections, Parallel Algorithms, Fork-Join Framework, MapReduce, Parallelization, ParallelStream, ParallelStream - Under the Hood - Spliterator, <b>CompletableFuture, Spring WebClient</b>

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
    
  ## Locking Strategies & Deadlocks
   - Fine-Grained Locking - use synchronized at block-level 
   - Coarse-Grained Locking - use synchronized at method-level
   - <b>Conditions for Deadlock</b>
      - Mutual Exclusion - Only one thread can have exclusive access to a resource.
      - Hold & Wait - At least one thread is holding a resource and is waiting for another resource.
      - Non-preemptive allocation - A resource is released only after the thread is done using it.
      - Circular wait - A chain of at least two threads each one is holding one resource and waiting for another resource.
   - If any one of above condition is met, than deadlock happened.
   - <b>Deadlock Solution</b> solved by avoding circular wait & hold.
   
   ## Advanced Locking
   - java.util.concurrent.locks.ReentrantLock
      - ReentrantLock.lockInterruptibly()
      - ReentrantLock.tryLock()
      - <b>ReentrantLock</b>
        - Works like the synchronized keyword applied on an object
        - Requires explicitly locking & unlocking
      
 ```ruby
      Object lockObject = new Object();
      Resource resource = new Resource();
      ...
      ...
      public void method(){
        synchronized(lockObject){
          ...
          use(resource);
        }//synchronized lock terminates here
      }
      
      // ReentrantLock
      Lock lockObject = new ReentrantLock();
      Resource resource = new Resource();
      ...
      ...
      public void method(){
        lockObject.lock();
        ...
        use(resource);
        lockObject.unlock();
      }
      
      // ReentrantLock Disadvantages
      1.
      Lock lockObject = new ReentrantLock();
      ...
      ...
      public int use(){
        lockObject.lock();
        ...
        return value;
        //lockObject.unlock(); DeadLock
      }
      
      2.
      Lock lockObject = new ReentrantLock();
      ...
      ...
      public void use() throws SomeException {
        lockObject.lock();
        throwExceptionMethod();
        lockObject.unlock();//DeadLock situation
      }
      
      3. Solution
      Lock lockObject = new ReentrantLock();
      ...
      ...
      public int use() throws SomeException {
        lockObject.lock();
        try{
          someOperations();
          return value;
          } 
          finally{
            objectLock.unlock();
          }
      }
      
```

- Queries methods - For testing
  - getQueuedThreads() - Return a list of threads waiting to acquire a lock
  - getOwner() - Return the thread that currently owns the lock
  - isHeldByCurrentThread() - Queries if the lock is held by the current thread
  - isLocked() - Queries if the lock is held by any thread

- ReentrantLock Fairness
  - ReentrantLock(true);
  - May reduce the throughput of the application, use when it is needed bcz lock acquisition take may longer.
- LockInterruptibily() use case
  - Watchdog for deadlock detection & recovery
  
```ruby
// ReentrantLock.tryLock() return boolean
if(lockObject.tryLock()){// if only true
  try{
    use(resource);
    ...
  } finally{
    lockObject.unlcok();
  }
} else {...}

```

- ReentrantReadWriteLock use case
  - Synchronized & ReentrantLock do not allow multiple readers to access a shared resource concurrently.
  - when read operations are predominant(present as the strongest or main element)
  - multiple threads can aquire the readLock.
  - readLock internally keeps count of how many reader threads are holding at given moment.
  - Only a single thread allow to lock a writeLock.
  - Mutual Exclusion between readers & writers.
    - If a writeLock is acquired, no thread can acquire the a readLock.
    - If at least one thread holds a readLock, no thread can acquire a writeLock.

```ruby
ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
Lock readLock = rwLock.readLock();
Lock writeLock = rwLock.writeLock();

writeLock.lock();
try{
  modifySharedResource();
} finally {
  writeLock.unlock();
}
...
...
readLock.lock();
try{
  readFromSharedResources();
} finally {
  readLock.unlock();
}
```

## Semaphore
  - Introduction
    - Can be used to restrict the number of "users" to a perticulat resource or group of resources.
    - Unlike the locks that allow only one "user" per resource
    - The Semaphore can restrict any given number of users to a resource
    - Inter-thread - Semaphore as condition var
      - Calling the acquire() on a Semaphore is equivalent to checking the condition "Is Number of permits > 0?"
      - If the condition is not met - thread A goes to sleep until another thread changes the semaphore's state.
      - when thread B calls the release() method, thread A wakes up.
      - Thread A checks the condition "Is Number of permits > 0?"
        - If it is, thread A continues to the next instruction
      - Condition variable is always associated with lock
      - 
  - Binary Semaphores
  - Semaphore VS Locks
    - Any thread can release a semaphore(no ownership).
  - Producer Consumer(Inter Thread COmmunication) using Semaphore


## Parallel & Asynchronous Programming

  - use gradle, lombook, Annotation Processor

|Concurency|Parallelism|
|---|---|
|A concept where two or more tasks can run simultaneously|A concept where two or more tasks are literally running in parallel|
|can be implemented in single or multiple cores|only be implemented in a multi-core machine|
|is about correctly and efficiently controlling access to shared resource|is about using more resources to access the result faster|


## Threads, Futures and ForkJoin Framework limitations.

### Threads API

  - Threads are basically used to offload the blocking tasks as background task.
  - Allow developer to write the asynchronous style of code.
  - <b>Thread Limitations</b>;
    - create the thread
    - start the thread
    - join the thread
    - Threads are expensive, threads have its own runtime-stack, memory registers and more.
    - Thread Pool was created specially to solve above limitations.

### ExecutorService
  - Asynchronous Task Execution Engine
  - This enables coarse-grained task based parallelism.
  - <b>Working of ExecutorService</b>
  - Client<Future>, ExecutorService(WorkQueue<BlockingQueue>, CompletionQueue & ThreadPool)
  - any new task put in to WorkQueue, thread came from ThreadPool, pick the task from WorkQueue & process it and put it into CompletionQueue while its processing is done.

### Fork/Join Framework
  - Introduced as part of Java7
  - An extension of ExecutorService
  - Fork/Join framework is designed to acheive Data Parallelism
  - Executor Service is designed to achieve to Task based Parallelism

### what is Data Parallelism?
  - Data Parallelism is a concept where a given `Task` is recursively split in to `SubTasks` until it reaches it least possible size & execute those tasks in parallel.
  - Basically it uses the divide and conquer approach.

### ForkJoin Pool
  - Client submit the ForkJoin task to sharedWorkQueue & each and every threads in Double Ended Work Queue(deck) continuosly poll the shared work queue for the new task, and task is taken by one of the thread in deck, the task in deck always processed in LIFO(Last In First Out) order, and task is further divided in to sub tasks, WorkStealing is a concept where the other threads in the pool check each other work queue for task. Once task is executed, result shared to the client.

### ForkJoin Task
  - ForkJoin task represents part of the data and its computation.
  - Type of tasks to submit to ForkJoin Pool,
    - ForkJoinTask
      - RecursiveTask -> task that returns a value
      - RecursiveAction -> task that does not return a value

### ParallelStreams - How it works?
  - parallelStream()
    - Split the data in to chunks, This is done using Spliterators. For ArrayList Spliterator is ArrayListSpliterator
      - Each & every collection has a different Spliterator implementation
      - Performance differ based on the implementation 
    - Execute the data chunks
      - Split & Execute phase run parallel
    - Combine the data chunks
      - combine the executed results in to final result
      - terminal operations
      - uses collect & reduce functions
  - <b>Common ForkJoin Pool</b>
    - used by Parallel Stream & CompletableFuture
    - CompletableFuture have options to use a User-defined ThreadPools
    - Common ForkJoin Pool is shared by whole process.
    - There is a way to look in to parallelism & threads involved
      - <b>ForkJoinPool.getCommonPoolParallelism()</b>

  - Modifting Default parallelism
    - Default value is Number of cores in your machine - 1.
    - System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "100"); OR
    - -Djava.util.concurrent.ForkJoinPool.common.parallelism=100

  - When use & Not to use the parallelStream
    - use when split & combine is faster like wrt ArrayList, not in case of LinkedList
    - AutoBoxing & UnBoxing

### CompletableFuture
  - Introduced in JDK 1.8
  - An Asynchronous Reactive Finctional Programming API
  - Asynchronous computation in Functional Style
  - CompleatableFutures API is created to solve the limitations of Future API
#### CompletableFuture & Reactive Programming
  - A reactive programming have 4 features
    - Responsive(1)
      - Call returns immediately and the response will be sent when its available
    - Resilient(2)
      - Exception OR Error won't crash the app or code
    - Elastic(3)
      - Number of threads can go up or down based on the need
    - Message Driven(4)
      - Asynchronous computations intract with each other through messages in a event driven style


#### CompletableFuture API divided in to 3 categories
  - Factory Methods
    - Initiate asynchronous computation
  - Completion Stage Methods
    - Chain asynchronous computation
  - Exception Methods
    - Handle exceptions in an Asynchronous Computation

|`Factory Methods`|`Description`|`Completion Stage Methods`|`Description`|`Exception Methods`|`Description`|
|---|---|---|---|---|---|
|`supplyAsync()`|Initiate aynchronous computation<br>Initiate a <b>Supplier</b>Functional Interface<br>Returns CompleatableFuture(T)()|`thenAccept()`|Chain asynchronous computation<br>Input is <b>Consumer</b> Functional Interface<br>Consume the result of the previous<br>Returns CompleatableFuture(void)|||
|||`thenApply()`|Transform the data from one form to another<br>Input is <br>Function</br>functional interface<br>Returns CompletableFuture(T)|||
|||`thenCombine()`|Used to combine Independent CompletableFuture<br>Takes two arguments, CompletionStage & BiFunction<br>client -> Service -> service1 & service2 & these are independent call<br>Returns a CompletableFuture|||
|||`thenCompose()`|Transform the data from one form to another<br>Input is Function functional Interface<br>Returns CompletableFuture|||

#### Exception Handling In CompletableFuture
  - CompletableFuture API has functional style of handling exceptions
  - Three options are available;
    - handle()
    - exceptionally()
    - whenComplete()
    - <b>Remark</b>: handle & exceptionally are catch the exception & recover while whenComplete only catch the exception & does not recover
    
