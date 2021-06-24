# Multithreading
Multithreading, Executor Framework, Concurrent Collections, Parallel Algorithms, Fork-Join Framework, MapReduce, Parallelization, ParallelStream, ParallelStream - Under the Hood - Spliterator, <b>CompletableFuture, Spring WebClient</b>

#### Multithreading
- Multithreading is the ability of the CPU to execute multiple processes or threads concurrently.
- Both Process and Threads are independent sequence of execution.

#### Process
- A process is an instance of program exection
- eg. When you open a web browser or any software - these are distinct processes
- The OS assigns distinct register, program counter, stack memory and heap memory to every single process.
- In Java, we can create the process with the help of ProcessBuilder class.
- Every single process has unique PID, memory usage

#### Threads
- A thread is a 'Light Weight Process'.
- It is a unit of execution within a given process(a process may have several threads)
- Each thread in a process share the memory and resources.

#### Thread Life Cycle
1. NEW - When we instantiate a thread. It is in this state until we start it by start()
2. RUNNABLE - After we have started the thread by start(). The thread is executing its task in this state by calling the run() method.
3. WAITING - A RUNNABLE thread becomes in WAITING state by calling wait() or sleep(), for example for another thread to finish its task, When another thread signals then this thread goes back to the runnable state.
4. DEAD - After thread finishes its task.

#### Daemon Threads and User Thread
- When a java program starts then one thread begins running immediately(main thread) - its start the main thread.
- We can create the child threads from the main thread. The main thread is the last thread to finish execution because it performs various shutdown operations.
- Daemon threads are intended as helper threads(for example Garbage collector)
- Daemon threads are terminated by the JVM when all other worker threads are terminated(finish execution)

#### Memory Management
- The typical difference between thread and process is that  threads(of the same process) run in a shared memory space, while processes run in separate memory spaces.

#### Stack Memory
- The local variables and method arguments as well as method calls are stored on the stack.
- By default Stack memory is fast
- Stack memory is small

#### Every thread has its own stack memory and cache but all threads share the heap memory(Shared memory space)

#### Heap Memory
- Objects are stored in the Heap memory and live as long as it is referenced from somewhere in the application.
- By default Heap memory is slow bcz its take more time to access the object stored in the heap memory.
- Heal memory is larger

#### The main purpose of synchronization is the sharing of resources without interference using mutual exclusion.

``` ruby
public void increment(){
  counter++;// reading the number from memory
            // increment the value
            // writing number to the memory
            // return with the value
}

These operation seems to be atomic in the sense that requires only a single operation but this is not the case
-> It take some time to finish with increment operation
-> During this procedure another thread may call this method as well with the original counter value.
```

#### Intrinsic Lock (Monitor)
``` ruby
public synchronize void increament(){
    counter++;
}
```

- Every object in java has a so-called intrinsic lock
- A thread that needs exclusive and consistent access to an object's fields has to acquire the object's intrinsic lock before accessing them, and release the intrinsic lock when it's done with them.
- Because of the monitor lock: no two threads can execute the same synchronized method at the same time.
-  

#### Process VS Thread
|Comparison Basis|Process|Thread|
|---|---|---|
|`Basic`|An executing program|A small part of a Process|
|`Address Space`|each prcess has its own separate address space|All the threads of a process share the same address space|
|`Communication`|Communication between two processes is expensive and limited|Communication between two threads is less expensive as compared to process.|
|`Switching`|Context switching from one process to another process is expensive.|Context switching from one thread to another thread is less expensive as compared to process.|
|`Components`|A process has its own address space, global variables, signal handlers, open files, child processes, accounting information.|A thread has its own register, state, stack, program counter.|
|`Control`|Process-based multitasking is not under the control of Java.|Thread-based multitasking is under the control of Java.|

### Inter-Thread Communication
  <p> Inter-thread communication allows synchronized threads to communicate with each other using a set of methods.<br>
The methods used are wait, notify, and notifyAll, which are all inherited from the Object class.<br>
Wait() causes the current thread to wait indefinitely until some other thread calls notify() or notifyAll() on the same object. We can call notify() to waking up threads that are waiting for access to this object’s monitor.
</p>

#### Programming Questions over Inter-Thread Communication
  - Three threads print the numbers like t1 -> 1, t2 -> 2, t3-> 3 and again t1-> 4, t2-> 5, t3-> 6, ...
  - Print even and odd Number alternatively using threads & Semaphore.

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

## java.util.concurrent Package

### BlockingQueue

### PriorityBlockingQueue

### Semaphore
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

### CountDownLatch

### CyclicBarrier


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

### allOf - Dealing with Multiple CompletableFutures
  - static method that's part of CompletableFuture API
  - use when you are dealing with multiple CompleatableFuture

### anyOf - Dealing with Multiple CompletableFutures
  - static method that's part of CompletableFuture API
  - use when you are dealing with retrieving data from miltiple data source
  - anyOf pick the fastest one only.
    
