
fun main() {
    val lines = readInput("Day04")
    val grid = lines.map { it.toCharArray() }
    val word = "XMAS"

    val totalOccurrences = countWordOccurrences(grid, word)
    println("The word '${word}' appears $totalOccurrences times.")
}

fun countWordOccurrences(grid: List<CharArray>, word: String): Int {
    val directions = listOf(
        Pair(0, 1),   // Right
        Pair(1, 0),   // Down
        Pair(0, -1),  // Left
        Pair(-1, 0),  // Up
        Pair(1, 1),   // Down-right
        Pair(1, -1),  // Down-left
        Pair(-1, 1),  // Up-right
        Pair(-1, -1)  // Up-left
    )

    val rows = grid.size
    val cols = grid[0].size
    var count = 0

    fun isWordAt(x: Int, y: Int, dx: Int, dy: Int): Boolean {
        for (i in word.indices) {
            val xPos = x + i * dx
            val yPos = y + i * dy
            if (xPos !in 0 until rows || yPos !in 0 until cols || grid[xPos][yPos] != word[i]) {
                return false
            }
        }
        return true
    }

    for (x in 0 until rows) {
        for (y in 0 until cols) {
            for ((dx, dy) in directions) {
                if (!isWordAt(x, y, dx, dy))
                    continue

                count++
            }
        }
    }

    return count
}
