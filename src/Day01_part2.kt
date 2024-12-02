
fun main() {
    val lines = readInput("Day01")

    val (left, right) = lines.map { line ->
        val first = line.substringBefore(" ").trim().toInt()
        val second = line.substringAfter(" ").trim().toInt()
        (first to second)
    }.unzip()

    val similarityScore = left.sumOf { leftNumber ->
        val countInRight = right.count { rightNumber ->
            (rightNumber == leftNumber)
        }
        (leftNumber * countInRight)
    }

    println("Total similarity score: $similarityScore")
}
