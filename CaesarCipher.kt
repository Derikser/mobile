import java.io.File
import java.io.IOException

// Функция для шифрования и расшифровки текста с учетом русского и английского алфавита
fun caesarCipher(text: String, shift: Int, encrypt: Boolean = true): String {
    val engAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val rusAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"

    val lowerEngAlphabet = engAlphabet.lowercase()
    val lowerRusAlphabet = rusAlphabet.lowercase()

    val shiftDirection = if (encrypt) shift else -shift

    return text.map { char ->
        when {
            char in engAlphabet -> shiftChar(char, engAlphabet, shiftDirection)
            char in lowerEngAlphabet -> shiftChar(char, lowerEngAlphabet, shiftDirection)
            char in rusAlphabet -> shiftChar(char, rusAlphabet, shiftDirection)
            char in lowerRusAlphabet -> shiftChar(char, lowerRusAlphabet, shiftDirection)
            else -> char // Остальные символы остаются без изменений
        }
    }.joinToString("")
}

// Функция для сдвига символа в алфавите
fun shiftChar(char: Char, alphabet: String, shift: Int): Char {
    val newIndex = (alphabet.indexOf(char) + shift + alphabet.length) % alphabet.length
    return alphabet[newIndex]
}

// Функция для чтения содержимого файла
fun readFile(filePath: String): String? {
    return try {
        File(filePath).readText(Charsets.UTF_8)
    } catch (e: IOException) {
        println("Ошибка при чтении файла: ${e.message}")
        null
    }
}

// Функция для записи в файл
fun writeFile(filePath: String, content: String) {
    try {
        File(filePath).writeText(content, Charsets.UTF_8)
    } catch (e: IOException) {
        println("Ошибка при записи в файл: ${e.message}")
    }
}

// Валидация ключа
fun isValidKey(key: Int): Boolean {
    return key >= 0
}

fun main() {
    while (true) {
        println("\nВыберите режим работы:")
        println("1 - Шифрование текста")
        println("2 - Расшифровка текста")
        println("3 - Brute force (перебор)")
        println("4 - Выйти")

        when (readLine()) {
            "1" -> {
                println("Введите путь к исходному файлу (или 'отмена' для выхода):")
                val inputFile = readLine()
                if (inputFile == "отмена") continue

                println("Введите путь для записи зашифрованного файла:")
                val outputFile = readLine()
                if (outputFile == "отмена") continue

                println("Введите ключ (сдвиг):")
                val shift = readLine()?.toIntOrNull()
                if (shift == null || !isValidKey(shift)) {
                    println("Ошибка: неверный ключ.")
                    continue
                }

                val inputText = readFile(inputFile!!)
                if (inputText != null) {
                    val encryptedText = caesarCipher(inputText, shift, encrypt = true)
                    writeFile(outputFile!!, encryptedText)
                    println("Текст успешно зашифрован и записан в файл.")
                }
            }
            "2" -> {
                println("Введите путь к зашифрованному файлу (или 'отмена' для выхода):")
                val inputFile = readLine()
                if (inputFile == "отмена") continue

                println("Введите путь для записи расшифрованного файла:")
                val outputFile = readLine()
                if (outputFile == "отмена") continue

                println("Введите ключ (сдвиг):")
                val shift = readLine()?.toIntOrNull()
                if (shift == null || !isValidKey(shift)) {
                    println("Ошибка: неверный ключ.")
                    continue
                }

                val inputText = readFile(inputFile!!)
                if (inputText != null) {
                    val decryptedText = caesarCipher(inputText, shift, encrypt = false)
                    writeFile(outputFile!!, decryptedText)
                    println("Текст успешно расшифрован и записан в файл.")
                }
            }
            "3" -> {
                println("Введите путь к зашифрованному файлу для brute force (или 'отмена' для выхода):")
                val inputFile = readLine()
                if (inputFile == "отмена") continue

                val inputText = readFile(inputFile!!)
                if (inputText != null) {
                    println("Попытки расшифровки с разными ключами...")
                    for (shift in 0 until 33) { // Перебираем 33 символа русского алфавита
                        val decryptedText = caesarCipher(inputText, shift, encrypt = false)
                        println("Ключ: $shift - $decryptedText")
                    }
                }
            }
            "4" -> {
                println("Выход из программы.")
                return
            }
            else -> println("Неверный выбор, попробуйте снова.")
        }
    }
}
