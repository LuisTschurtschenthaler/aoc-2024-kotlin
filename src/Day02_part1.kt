
fun main() {
    val lines = readInput("Day02")

    val saveReportCount = lines.count { line ->
        val levels = line.split(" ").map { it.trim().toInt() }

        val isIncreasing = levels.zipWithNext().all { (a, b) ->
            (b > a)
        }
        val isDecreasing = levels.zipWithNext().all { (a, b) ->
            (b < a)
        }

        val isValidDifference = levels.zipWithNext().all { (a, b) ->
            (b - a in 1..3) || (a - b in 1..3)
        }

        (isIncreasing || isDecreasing) && isValidDifference
    }

    println("Safe reports: $saveReportCount")
}
