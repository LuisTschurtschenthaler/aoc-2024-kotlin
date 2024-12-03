
fun main() {
    val lines = readInput("Day03")
    val corruptedMemory = lines.joinToString(" ")

    val regex = Regex("""mul\((\d+),(\d+)\)""")
    val matches = regex.findAll(corruptedMemory)

    val totalSum = matches.sumOf { match ->
        val (x, y) = match.destructured
        x.toInt() * y.toInt()
    }

    println("Result: $totalSum")
}
