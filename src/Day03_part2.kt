
fun main() {
    val lines = readInput("Day03")
    val corruptedMemory = lines.joinToString(" ")

    val tokenRegex = Regex("""mul\(\d+,\d+\)|do\(\)|don't\(\)""")
    val mulRegex = Regex("""mul\((\d+),(\d+)\)""")
    val doRegex = Regex("""\bdo\(\)""")
    val dontRegex = Regex("""\bdon't\(\)""")

    var isMultiplicationEnabled = true
    var totalSum = 0

    tokenRegex.findAll(corruptedMemory).forEach { tokenMatch ->
        val token = tokenMatch.value

        when {
            doRegex.matches(token) -> isMultiplicationEnabled = true
            dontRegex.matches(token) -> isMultiplicationEnabled = false
            mulRegex.matches(token) && isMultiplicationEnabled -> {
                val (x, y) = mulRegex.matchEntire(token)!!.destructured
                totalSum += x.toInt() * y.toInt()
            }
        }
    }

    println("Result: $totalSum")
}
