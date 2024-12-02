
fun main() {
    val lines = readInput("Day02")

    val saveReportCount = lines.count { line ->
        val levels = line.split(" ").map { it.trim().toInt() }

        if(isSaveReport(levels)) {
            return@count true
        }

        levels.indices.any { index ->
            val modifiedLevels = levels.filterIndexed { i, _ ->
                (i != index)
            }
            isSaveReport(modifiedLevels)
        }
    }

    println("Safe reports: $saveReportCount")
}

fun isSaveReport(levels: List<Int>): Boolean {
    val isIncreasing = levels.zipWithNext().all { (a, b) ->
        (b > a)
    }
    val isDecreasing = levels.zipWithNext().all { (a, b) ->
        (b < a)
    }

    val isValidDifference = levels.zipWithNext().all { (a, b) ->
        (b - a in 1..3) || (a - b in 1..3)
    }

    return (isIncreasing || isDecreasing) && isValidDifference
}
