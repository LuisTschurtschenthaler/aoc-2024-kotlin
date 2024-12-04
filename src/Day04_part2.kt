
fun main() {
    val lines = readInput("Day04")
    val grid = lines.map { it.toCharArray() }

    val totalOccurrences = countXMasPatterns(grid)
    println("The X-MAS pattern appears $totalOccurrences times.")
}

fun countXMasPatterns(grid: List<CharArray>): Int {
    val rows = grid.size
    val cols = grid[0].size
    val mas = "MAS"
    val masReversed = mas.reversed()
    var count = 0

    fun checkXMas(x: Int, y: Int): Boolean {
        if(x - 1 !in 0 until rows || x + 1 !in 0 until rows || y - 1 !in 0 until cols || y + 1 !in 0 until cols) {
            return false
        }

        val top = "${grid[x - 1][y - 1]}${grid[x][y]}${grid[x + 1][y + 1]}"
        val bottom = "${grid[x + 1][y - 1]}${grid[x][y]}${grid[x - 1][y + 1]}"
        return (top == mas || top == masReversed) && (bottom == mas || bottom == masReversed)
    }

    for(x in 1 until rows - 1) {
        for(y in 1 until cols - 1) {
            if(!checkXMas(x, y))
                continue

            count++
        }
    }

    return count
}
