import java.util.concurrent.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

// Задача 1: Общий счётчик с многопоточностью
class Counter {
    @Volatile private var count = 0

    @Synchronized fun increment() {
        count++
    }

    fun getCount(): Int {
        return count
    }
}

// Задача 2: Генерация последовательности чисел с потокобезопасной коллекцией
fun generateSequence() {
    val list = CopyOnWriteArrayList<Int>()
    val task = Runnable {
        for (i in 1..100) {
            list.add(i)
        }
    }
    val threads = List(10) { Thread(task).apply { start() } }
    threads.forEach { it.join() }
    println("List size: ${list.size}")
}

// Задача 3: Распределение задач с использованием пула потоков
fun distributeTasks() {
    val executorService = Executors.newFixedThreadPool(4)
    for (i in 1..20) {
        val taskId = i
        executorService.submit {
            println("Task $taskId executed by ${Thread.currentThread().name}")
        }
    }
    executorService.shutdown()
}

// Задача 4: Симуляция работы банка с потокобезопасным переводом
class Account(private var balance: Int) {
    private val lock = ReentrantLock()
    
    fun transfer(target: Account, amount: Int) {
        if (this !== target) {
            lock.lock()
            target.lock.lock()
            try {
                balance -= amount
                target.balance += amount
            } finally {
                lock.unlock()
                target.lock.unlock()
            }
        }
    }
}

fun simulateBank() {
    val account1 = Account(1000)
    val account2 = Account(2000)
    val task = Runnable { account1.transfer(account2, 100) }
    val threads = List(5) { thread(start = true, block = task) }
    threads.forEach { it.join() }
    println("Account 1 balance: ${account1.balance}")
    println("Account 2 balance: ${account2.balance}")
}

// Задача 5: Барьер синхронизации с использованием CyclicBarrier
fun syncBarrier() {
    val barrier = CyclicBarrier(5) { println("All threads have reached the barrier.") }
    val task = Runnable {
        println("${Thread.currentThread().name} is working.")
        barrier.await()
        println("${Thread.currentThread().name} passed the barrier.")
    }
    val threads = List(5) { Thread(task).apply { start() } }
    threads.forEach { it.join() }
}

// Задача 6: Ограниченный доступ к ресурсу с использованием Semaphore
fun limitAccess() {
    val semaphore = Semaphore(2)
    val task = Runnable {
        semaphore.acquire()
        println("${Thread.currentThread().name} is accessing the resource.")
        Thread.sleep(1000)
        semaphore.release()
        println("${Thread.currentThread().name} has finished using the resource.")
    }
    val threads = List(5) { thread(start = true, block = task) }
    threads.forEach { it.join() }
}

// Задача 7: Обработка результатов задач с использованием Callable и Future
fun calculateFactorial() {
    val executorService = Executors.newFixedThreadPool(10)
    val task = Callable {
        (1..10).fold(1L) { acc, i -> acc * i }
    }
    val results = List(10) { executorService.submit(task) }
    results.forEach { println("Factorial: ${it.get()}") }
    executorService.shutdown()
}

// Задача 8: Симуляция производственной линии с использованием BlockingQueue
fun simulateProduction() {
    val queue = LinkedBlockingQueue<Int>(10)
    val producer = thread {
        repeat(10) {
            queue.put(it)
            println("Produced: $it")
        }
    }
    val consumer = thread {
        repeat(10) {
            val item = queue.take()
            println("Consumed: $item")
        }
    }
    producer.join()
    consumer.join()
}

// Задача 9: Многопоточная сортировка
fun sortArray() {
    val array = intArrayOf(5, 3, 8, 6, 2, 7, 1, 4)
    val sortTask = Runnable {
        array.sort()
        println("Sorted Array: ${array.joinToString()}")
    }
    val sortingThread = Thread(sortTask).apply { start() }
    sortingThread.join()
}

// Задача 10: Таймер с многопоточностью
fun runTimer() {
    val timerTask = thread {
        var time = 0
        while (!Thread.currentThread().isInterrupted) {
            println("Time: $time seconds")
            time++
            Thread.sleep(1000)
        }
    }
    Thread.sleep(10000)
    timerTask.interrupt()
}

// Задача 11: Параллельное вычисление суммы массива
fun parallelSum() {
    val array = IntArray(1000) { it + 1 }
    val sum = array.toList().parallelStream().mapToInt { it }.sum()
    println("Parallel sum: $sum")
}

// Задача 12: Поиск максимального значения в массиве многопоточно
fun parallelMax() {
    val array = IntArray(1000) { it + 1 }
    val max = array.toList().parallelStream().mapToInt { it }.max().orElse(-1)
    println("Parallel max: $max")
}

fun main() {
    val counter = Counter()
    val counterTask = Runnable {
        repeat(1000) { counter.increment() }
    }
    val threads = List(5) { thread(start = true, block = counterTask) }
    threads.forEach { it.join() }
    println("Final count: ${counter.getCount()}")

    generateSequence()
    distributeTasks()
    simulateBank()
    syncBarrier()
    limitAccess()
    calculateFactorial()
    simulateProduction()
    sortArray()
    runTimer()
    parallelSum()
    parallelMax()
}
