import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.Period
import java.time.Duration
import java.time.format.TextStyle // Добавлен импорт для TextStyle
import java.util.Locale
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    println("--- Задача 1: Текущая дата и время ---")
    printCurrentDateTime()

    println("\n--- Задача 2: Сравнение дат ---")
    println(compareDates(LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 15)))

    println("\n--- Задача 3: Дней до Нового года ---")
    println("До Нового года осталось ${daysUntilNewYear()} дней")

    println("\n--- Задача 4: Проверка високосного года ---")
    println("2024 год високосный? ${isLeapYear(2024)}")

    println("\n--- Задача 5: Подсчет выходных за месяц ---")
    println("Количество выходных в октябре 2023: ${countWeekends(2023, 10)}")

    println("\n--- Задача 6: Расчет времени выполнения метода ---")
    measureExecutionTime()

    println("\n--- Задача 7: Форматирование и парсинг даты ---")
    formatAndParseDate("15-10-2023")

    println("\n--- Задача 8: Конвертация между часовыми поясами ---")
    val utcDateTime = LocalDateTime.now()
    val moscowDateTime = convertTimeZone(utcDateTime, ZoneId.of("UTC"), ZoneId.of("Europe/Moscow"))
    println("Время в Москве: $moscowDateTime")

    println("\n--- Задача 9: Вычисление возраста по дате рождения ---")
    println("Возраст: ${calculateAge(LocalDate.of(1990, 1, 1))} лет")

    println("\n--- Задача 10: Календарь на месяц ---")
    printMonthCalendar(2023, 10)

    println("\n--- Задача 11: Генерация случайной даты в диапазоне ---")
    println("Случайная дата: ${generateRandomDate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31))}")

    println("\n--- Задача 12: Время до заданной даты ---")
    println(timeUntilEvent(LocalDateTime.of(2023, 12, 31, 23, 59)))

    println("\n--- Задача 13: Количество рабочих часов ---")
    println("Рабочие часы: ${calculateWorkingHours(LocalDateTime.now(), LocalDateTime.now().plusDays(7))}")

    println("\n--- Задача 14: Конвертация даты в строку с учетом локали ---")
    println("Дата с учетом локали: ${formatDateWithLocale(LocalDate.now(), Locale("ru"))}")

    println("\n--- Задача 15: Определение дня недели по дате ---")
    println("День недели: ${getDayOfWeekInRussian(LocalDate.now())}")
}

// Задача 1: Текущая дата и время
fun printCurrentDateTime() {
    val currentDate = LocalDate.now()
    val currentTime = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
    println("Текущая дата и время: ${currentDate.atTime(currentTime).format(formatter)}")
}

// Задача 2: Сравнение дат
fun compareDates(date1: LocalDate, date2: LocalDate): String {
    return when {
        date1.isAfter(date2) -> "Дата $date1 больше, чем $date2"
        date1.isBefore(date2) -> "Дата $date1 меньше, чем $date2"
        else -> "Даты равны"
    }
}

// Задача 3: Дней до Нового года
fun daysUntilNewYear(): Long {
    val today = LocalDate.now()
    val newYear = LocalDate.of(today.year + 1, 1, 1)
    return ChronoUnit.DAYS.between(today, newYear)
}

// Задача 4: Проверка високосного года
fun isLeapYear(year: Int): Boolean {
    return LocalDate.of(year, 1, 1).isLeapYear
}

// Задача 5: Подсчет выходных за месяц
fun countWeekends(year: Int, month: Int): Int {
    val yearMonth = YearMonth.of(year, month)
    var weekends = 0
    for (day in 1..yearMonth.lengthOfMonth()) {
        val date = yearMonth.atDay(day)
        if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) {
            weekends++
        }
    }
    return weekends
}

// Задача 6: Расчет времени выполнения метода
fun measureExecutionTime() {
    val time = measureTimeMillis {
        for (i in 1..1_000_000) {
            // Имитация работы
        }
    }
    println("Время выполнения: $time мс")
}

// Задача 7: Форматирование и парсинг даты
fun formatAndParseDate(dateString: String) {
    val formatterInput = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val date = LocalDate.parse(dateString, formatterInput)
    val newDate = date.plusDays(10)
    val formatterOutput = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    println("Новая дата: ${newDate.format(formatterOutput)}")
}

// Задача 8: Конвертация между часовыми поясами
fun convertTimeZone(dateTime: LocalDateTime, fromZone: ZoneId, toZone: ZoneId): ZonedDateTime {
    return ZonedDateTime.of(dateTime, fromZone).withZoneSameInstant(toZone)
}

// Задача 9: Вычисление возраста по дате рождения
fun calculateAge(birthDate: LocalDate): Int {
    return Period.between(birthDate, LocalDate.now()).years
}

// Задача 10: Календарь на месяц
fun printMonthCalendar(year: Int, month: Int) {
    val yearMonth = YearMonth.of(year, month)
    for (day in 1..yearMonth.lengthOfMonth()) {
        val date = yearMonth.atDay(day)
        val isWeekend = date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
        println("${date.dayOfMonth}: ${if (isWeekend) "Выходной" else "Рабочий день"}")
    }
}

// Задача 11: Генерация случайной даты в диапазоне
fun generateRandomDate(start: LocalDate, end: LocalDate): LocalDate {
    val daysBetween = ChronoUnit.DAYS.between(start, end)
    return start.plusDays(Random.nextLong(daysBetween + 1))
}

// Задача 12: Время до заданной даты
fun timeUntilEvent(eventDateTime: LocalDateTime): String {
    val now = LocalDateTime.now()
    val duration = Duration.between(now, eventDateTime)
    return "Осталось: ${duration.toHours()} часов, ${duration.toMinutes() % 60} минут, ${duration.seconds % 60} секунд"
}

// Задача 13: Количество рабочих часов
fun calculateWorkingHours(start: LocalDateTime, end: LocalDateTime): Long {
    var workingHours = 0L
    var current = start
    while (current.isBefore(end)) {
        if (current.dayOfWeek != DayOfWeek.SATURDAY && current.dayOfWeek != DayOfWeek.SUNDAY) {
            workingHours += Duration.between(current, current.plusHours(1)).toHours()
        }
        current = current.plusHours(1)
    }
    return workingHours
}

// Задача 14: Конвертация даты в строку с учетом локали
fun formatDateWithLocale(date: LocalDate, locale: Locale): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale)
    return date.format(formatter)
}

// Задача 15: Определение дня недели по дате
fun getDayOfWeekInRussian(date: LocalDate): String {
    return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ru"))
}