import kotlin.math.abs

fun main() {
    val lines = readInput("Day01")

    val (left, right) = lines.map { line ->
        val first = line.substringBefore(" ").trim().toInt()
        val second = line.substringAfter(" ").trim().toInt()
        (first to second)
    }.unzip()

    val sortedLeft = left.sorted()
    val sortedRight = right.sorted()

    val totalDistance = sortedLeft.zip(sortedRight).sumOf { (left, right) ->
        abs(left - right)
    }

    println("The total distance is: $totalDistance")
}
