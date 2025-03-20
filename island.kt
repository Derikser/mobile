import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.random.Random

// Остров (массив локаций)
class Island(val width: Int, val height: Int) {
    val grid: Array<Array<Location>> = Array(width) { Array(height) { Location() } }

    fun printStats() {
        println("=== Состояние острова ===")
        for (row in grid) {
            for (cell in row) {
                print("[${cell.animals.size}] ")
            }
            println()
        }
    }
}

// Клетка острова
class Location {
    val animals = mutableListOf<Animal>()
    var plants: Int = Random.nextInt(5, 20) // Запас еды для травоядных
}

// Абстрактный класс для всех животных
abstract class Animal(var energy: Int) {
    abstract fun move(island: Island, x: Int, y: Int)
    abstract fun eat(location: Location)
    abstract fun reproduce(location: Location): Animal?

    fun decreaseEnergy() {
        energy -= 5 // Все животные теряют энергию на такт
    }
}

// 🦊 Хищники
abstract class Predator(energy: Int) : Animal(energy) {
    override fun eat(location: Location) {
        val prey = location.animals.filterIsInstance<Herbivore>().randomOrNull()
        if (prey != null && Random.nextInt(100) < 60) { // Вероятность атаки
            location.animals.remove(prey)
            energy += 20
            println("${this::class.simpleName} съел ${prey::class.simpleName}")
        }
    }
}

// 🦌 Травоядные
abstract class Herbivore(energy: Int) : Animal(energy) {
    override fun eat(location: Location) {
        if (location.plants > 0) {
            location.plants--
            energy += 10
            println("${this::class.simpleName} съел растение")
        }
    }
}

// 🐺 Волк
class Wolf : Predator(energy = 50) {
    override fun move(island: Island, x: Int, y: Int) = moveToRandomCell(island, x, y)
    override fun reproduce(location: Location): Animal? = if (Random.nextInt(100) < 20) Wolf() else null
}

// 🦊 Лиса
class Fox : Predator(energy = 40) {
    override fun move(island: Island, x: Int, y: Int) = moveToRandomCell(island, x, y)
    override fun reproduce(location: Location): Animal? = if (Random.nextInt(100) < 25) Fox() else null
}

// 🐻 Медведь
class Bear : Predator(energy = 60) {
    override fun move(island: Island, x: Int, y: Int) = moveToRandomCell(island, x, y)
    override fun reproduce(location: Location): Animal? = if (Random.nextInt(100) < 15) Bear() else null
}

// 🦅 Орёл
class Eagle : Predator(energy = 45) {
    override fun move(island: Island, x: Int, y: Int) = moveToRandomCell(island, x, y)
    override fun reproduce(location: Location): Animal? = if (Random.nextInt(100) < 10) Eagle() else null
}

// 🐍 Удав
class Python : Predator(energy = 55) {
    override fun move(island: Island, x: Int, y: Int) = moveToRandomCell(island, x, y)
    override fun reproduce(location: Location): Animal? = if (Random.nextInt(100) < 30) Python() else null
}

// 🐰 Кролик
class Rabbit : Herbivore(energy = 30) {
    override fun move(island: Island, x: Int, y: Int) = moveToRandomCell(island, x, y)
    override fun reproduce(location: Location): Animal? = if (Random.nextInt(100) < 40) Rabbit() else null
}

// 🐴 Лошадь
class Horse : Herbivore(energy = 50) {
    override fun move(island: Island, x: Int, y: Int) = moveToRandomCell(island, x, y)
    override fun reproduce(location: Location): Animal? = if (Random.nextInt(100) < 20) Horse() else null
}

// 🦌 Олень
class Deer : Herbivore(energy = 40) {
    override fun move(island: Island, x: Int, y: Int) = moveToRandomCell(island, x, y)
    override fun reproduce(location: Location): Animal? = if (Random.nextInt(100) < 25) Deer() else null
}

// 🐁 Мышь
class Mouse : Herbivore(energy = 20) {
    override fun move(island: Island, x: Int, y: Int) = moveToRandomCell(island, x, y)
    override fun reproduce(location: Location): Animal? = if (Random.nextInt(100) < 50) Mouse() else null
}

// 🔄 Функция перемещения животного
fun Animal.moveToRandomCell(island: Island, x: Int, y: Int) {
    val dx = listOf(-1, 0, 1).random()
    val dy = listOf(-1, 0, 1).random()
    val newX = (x + dx).coerceIn(0, island.width - 1)
    val newY = (y + dy).coerceIn(0, island.height - 1)

    if (island.grid[newX][newY].animals.size < 10) {
        island.grid[newX][newY].animals.add(this)
        island.grid[x][y].animals.remove(this)
    }
}

// 🚀 Симуляция
class Simulation(private val island: Island) {
    private val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(3)

    fun start() {
        executor.scheduleAtFixedRate({ tick() }, 0, 2, TimeUnit.SECONDS)
    }

    private fun tick() {
        println("\n=== Новый такт ===")

        for (x in island.grid.indices) {
            for (y in island.grid[x].indices) {
                val location = island.grid[x][y]
                location.animals.toList().forEach { animal ->
                    animal.move(island, x, y)
                    animal.eat(location)
                    animal.reproduce(location)?.let { if (location.animals.size < 10) location.animals.add(it) }
                    animal.decreaseEnergy() // Уменьшаем энергию
                    if (animal.energy <= 0) location.animals.remove(animal)
                }
            }
        }

        island.printStats()
    }
}

// 🔥 Запуск
fun main() {
    val island = Island(5, 5)

    island.grid[2][2].animals.add(Wolf())
    island.grid[3][3].animals.add(Rabbit())
    island.grid[1][1].animals.add(Fox())
    island.grid[4][4].animals.add(Deer())

    val simulation = Simulation(island)
    simulation.start()
}
