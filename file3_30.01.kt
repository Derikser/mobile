import java.io.*
import java.nio.channels.*
import java.nio.file.*
import java.nio.ByteBuffer
import java.util.concurrent.Future

// Задание 1: Работа с потоками ввода-вывода
fun transformFile(inputFilePath: String, outputFilePath: String) {
    try {
        BufferedReader(FileReader(inputFilePath)).use { reader ->
            BufferedWriter(FileWriter(outputFilePath)).use { writer ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    writer.write(line!!.uppercase())
                    writer.newLine()
                }
            }
        }
        println("Файл успешно обработан и записан в $outputFilePath")
    } catch (e: FileNotFoundException) {
        println("Ошибка: файл не найден - ${e.message}")
    } catch (e: IOException) {
        println("Ошибка ввода-вывода: ${e.message}")
    }
}

// Задание 2: Реализация паттерна Декоратор
interface TextProcessor {
    fun process(text: String): String
}

class SimpleTextProcessor : TextProcessor {
    override fun process(text: String): String = text
}

class UpperCaseDecorator(private val processor: TextProcessor) : TextProcessor {
    override fun process(text: String): String = processor.process(text).uppercase()
}

class TrimDecorator(private val processor: TextProcessor) : TextProcessor {
    override fun process(text: String): String = processor.process(text).trim()
}

class ReplaceDecorator(private val processor: TextProcessor) : TextProcessor {
    override fun process(text: String): String = processor.process(text).replace(" ", "_")
}

// Задание 3: Сравнение производительности IO и NIO
fun readWithIO(inputFilePath: String, outputFilePath: String): Long {
    val startTime = System.currentTimeMillis()
    try {
        BufferedReader(FileReader(inputFilePath)).use { reader ->
            BufferedWriter(FileWriter(outputFilePath)).use { writer ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    writer.write(line)
                    writer.newLine()
                }
            }
        }
    } catch (e: IOException) {
        println("Ошибка ввода-вывода: ${e.message}")
    }
    return System.currentTimeMillis() - startTime
}

fun readWithNIO(inputFilePath: String, outputFilePath: String): Long {
    val startTime = System.currentTimeMillis()
    try {
        FileChannel.open(Paths.get(inputFilePath)).use { inputChannel ->
            FileChannel.open(Paths.get(outputFilePath), StandardOpenOption.WRITE, StandardOpenOption.CREATE).use { outputChannel ->
                val buffer = ByteBuffer.allocate(1024)
                while (inputChannel.read(buffer) > 0) {
                    buffer.flip()
                    outputChannel.write(buffer)
                    buffer.clear()
                }
            }
        }
    } catch (e: IOException) {
        println("Ошибка ввода-вывода: ${e.message}")
    }
    return System.currentTimeMillis() - startTime
}

// Задание 4: Программа с использованием Java NIO
fun copyFileWithNIO(source: String, destination: String) {
    try {
        FileChannel.open(Paths.get(source), StandardOpenOption.READ).use { srcChannel ->
            FileChannel.open(Paths.get(destination), StandardOpenOption.WRITE, StandardOpenOption.CREATE).use { destChannel ->
                srcChannel.transferTo(0, srcChannel.size(), destChannel)
            }
        }
        println("Файл успешно скопирован в $destination")
    } catch (e: IOException) {
        println("Ошибка при копировании файла: ${e.message}")
    }
}

// Задание 5: Асинхронное чтение файла с использованием NIO.2
fun readFileAsync(filePath: String) {
    try {
        val fileChannel = AsynchronousFileChannel.open(Paths.get(filePath), StandardOpenOption.READ)
        val buffer = ByteBuffer.allocate(1024)
        val future: Future<Int> = fileChannel.read(buffer, 0)

        while (!future.isDone) {
            println("Чтение файла...")
        }

        buffer.flip()
        val data = ByteArray(buffer.remaining())
        buffer.get(data)
        println("Содержимое файла: ${String(data)}")
        buffer.clear()
        fileChannel.close()
    } catch (e: Exception) {
        println("Ошибка при асинхронном чтении файла: ${e.message}")
    }
}

// Основная функция для тестирования всех задач
fun main() {
    println("--- Задание 1: Работа с потоками ввода-вывода ---")
    transformFile("input.txt", "output.txt")

    println("\n--- Задание 2: Реализация паттерна Декоратор ---")
    val processor: TextProcessor = ReplaceDecorator(
        UpperCaseDecorator(
            TrimDecorator(SimpleTextProcessor())
        )
    )
    val result = processor.process(" Hello world ")
    println(result) // Вывод: HELLO_WORLD

    println("\n--- Задание 3: Сравнение производительности IO и NIO ---")
    val inputFilePath = "large_input.txt"
    val outputFilePathIO = "output_io.txt"
    val outputFilePathNIO = "output_nio.txt"

    val timeIO = readWithIO(inputFilePath, outputFilePathIO)
    val timeNIO = readWithNIO(inputFilePath, outputFilePathNIO)

    println("Время выполнения IO: $timeIO мс")
    println("Время выполнения NIO: $timeNIO мс")

    println("\n--- Задание 4: Программа с использованием Java NIO ---")
    copyFileWithNIO("source.txt", "destination.txt")

    println("\n--- Задание 5: Асинхронное чтение файла с использованием NIO.2 ---")
    readFileAsync("async_input.txt")
}