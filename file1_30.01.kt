fun main() {
    // Вызов функции для тестирования Singleton подключения к базе данных
    testDatabaseSingleton()

    // Вызов функции для тестирования Singleton логирования
    testLogger()

    // Вызов функции для тестирования работы с заказом
    testOrder()

    // Вызов функции для тестирования перевода сезонов года
    testSeasons()
}

// Задача 1: Singleton для подключения к базе данных
object DatabaseConnection {
    init {
        println("Создано подключение к базе данных")
    }
    fun connect() {
        println("Подключение установлено")
    }
}

fun testDatabaseSingleton() {
    val db1 = DatabaseConnection
    val db2 = DatabaseConnection
    println("Ссылаются на один объект: ${db1 === db2}")
}

// Задача 2: Singleton для логирования
object Logger {
    private val logs = mutableListOf<String>()

    fun log(message: String) {
        logs.add(message)
    }

    fun showLogs() {
        logs.forEach { println(it) }
    }
}

fun testLogger() {
    Logger.log("Первый лог")
    Logger.log("Второй лог")
    Logger.showLogs()
}

// Задача 3: Enum для статусов заказа и класс Order
enum class OrderStatus {
    NEW, IN_PROGRESS, DELIVERED, CANCELLED
}

class Order(var status: OrderStatus) {
    fun changeStatus(newStatus: OrderStatus) {
        if (status == OrderStatus.DELIVERED && newStatus == OrderStatus.CANCELLED) {
            println("Нельзя отменить доставленный заказ")
        } else {
            status = newStatus
            println("Статус заказа изменен на $status")
        }
    }
}

fun testOrder() {
    val order = Order(OrderStatus.NEW)
    order.changeStatus(OrderStatus.IN_PROGRESS)
    order.changeStatus(OrderStatus.DELIVERED)
    order.changeStatus(OrderStatus.CANCELLED)
}

// Задача 4: Enum для сезонов года и функция перевода
enum class Season {
    WINTER, SPRING, SUMMER, AUTUMN
}

fun getSeasonName(season: Season): String {
    return when (season) {
        Season.WINTER -> "Зима"
        Season.SPRING -> "Весна"
        Season.SUMMER -> "Лето"
        Season.AUTUMN -> "Осень"
    }
}

fun testSeasons() {
    println(getSeasonName(Season.WINTER))
    println(getSeasonName(Season.SUMMER))
}