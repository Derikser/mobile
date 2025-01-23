fun main() {
    val zad1 = zad1(5,7)
    println("Результат zad1: $zad1")
    val zad2 = zad2(10.0, 2.0)
    println("Результат zad2: $zad2")
    val zad3 = zad3("один")
    println("Результат zad3: $zad3")
    val zad4 = zad4(151)
    println("Результат zad4: $zad4")
    val zad5 = zad5(9.0)
    println("Результат zad5: $zad5")
    val zad6 = zad6(5)
    println("Результат zad6: $zad6")
    val zad7 = zad7(arrayOf(1, 2, 3, 4))
    println("Результат zad7: $zad7")
    val zad8 = zad8(2.0, 3)
    println("Результат zad8: $zad8")
    val zad9 = zad9("Hello, world!", 5)
    println("Результат zad9: $zad9")
    val zad10 = zad10(arrayOf(1, 2, 3, 4), 3)
    println("Результат zad10: $zad10")
    val zad11 = zad11(10)
    println("Результат zad11: $zad11")
    val zad12 = zad12(10, 2)
    println("Результат zad12: $zad12")
    val zad13 = zad13(listOf(1, 2, 3, 4), 2)
    println("Результат zad13: $zad13")
    val zad14 = zad14("securePass")
    println("Результат zad14: $zad14")
    val zad15 = zad15("01.01.2023")
    println("Результат zad15: $zad15")
    val zad16 = zad16("Hello", " World")
    println("Результат zad16: $zad16")
    val zad17 = zad17(10, 3)
    println("Результат zad17: $zad17")
    val zad18 = zad18(16.0)
    println("Результат zad18: $zad18")
    val zad19 = zad19(25.0)
    println("Результат zad19: $zad19")
    val zad20 = zad20("Test")
    println("Результат zad20: $zad20")
}

fun zad1(n: Int, z: Int): Int {
    var max_: Int
    if (n == z) throw Exception("Ошибка: числа равны!")
    else if(n>z) max_ = n
    else max_ = z
    return max_
}

fun zad2(n: Double, z: Double): Double {
    if (z == 0.0) throw ArithmeticException("Деление на ноль недопустимо!")
    return n / z
}

fun zad3(str: String): Int {
    return str.toIntOrNull() ?: throw NumberFormatException("Ошибка: некорректный формат строки!")
}
fun zad4(age: Int): String {
    if (age !in 1..150) throw IllegalArgumentException("Ошибка: некорректный возраст!")
    return "Возраст корректен"
}

fun zad5(num: Double): Double {
    if (num < 0) throw IllegalArgumentException("Ошибка: отрицательное число!")
    return kotlin.math.sqrt(num)
}

fun zad6(n: Int): Int {
    if (n < 0) throw Exception("Ошибка: отрицательное число!")
    var result = 1
    for (i in 1..n) {
        result *= i
    }
    return result
}

fun zad7(arr: Array<Int>): String {
    if (arr.contains(0)) throw Exception("Ошибка: массив содержит нули!")
    return "Массив корректен"
}

fun zad8(base: Double, exp: Int): Double {
    if (exp < 0) throw Exception("Ошибка: отрицательная степень!")
    return Math.pow(base, exp.toDouble())
}

fun zad9(str: String, len: Int): String {
    if (len > str.length) throw Exception("Ошибка: длина больше строки!")
    return str.substring(0, len)
}

fun zad10(arr: Array<Int>, elem: Int): String {
    if (!arr.contains(elem)) throw Exception("Ошибка: элемент не найден!")
    return "Элемент найден"
}

fun zad11(n: Int): String {
    if (n < 0) throw Exception("Ошибка: отрицательное число!")
    return Integer.toBinaryString(n)
}

fun zad12(a: Int, b: Int): Boolean {
    if (b == 0) throw ArithmeticException("Ошибка: деление на ноль!")
    return a % b == 0
}

fun zad13(list: List<Int>, index: Int): Int {
    if (index !in list.indices) throw IndexOutOfBoundsException("Ошибка: индекс вне диапазона!")
    return list[index]
}

fun zad14(password: String): String {
    if (password.length < 8) throw Exception("Ошибка: пароль слишком короткий!")
    return "Пароль надёжный"
}

fun zad15(date: String): String {
    val regex = Regex("\\d{2}\\.\\d{2}\\.\\d{4}")
    if (!regex.matches(date)) throw Exception("Ошибка: неверный формат даты!")
    return "Дата корректна"
}

fun zad16(str1: String?, str2: String?): String {
    if (str1 == null || str2 == null) throw NullPointerException("Ошибка: одна из строк равна null!")
    return str1 + str2
}

fun zad17(a: Int, b: Int): Int {
    if (b == 0) throw Exception("Ошибка: деление на ноль!")
    return a % b
}

fun zad18(num: Double): Double {
    if (num < 0) throw Exception("Ошибка: отрицательное число!")
    return kotlin.math.sqrt(num)
}

fun zad19(tempC: Double): Double {
    if (tempC < -273.15) throw Exception("Ошибка: температура ниже абсолютного нуля!")
    return tempC * 9 / 5 + 32
}

fun zad20(str: String?): String {
    if (str.isNullOrEmpty()) throw Exception("Ошибка: строка пустая или равна null!")
    return "Строка корректна"
}
