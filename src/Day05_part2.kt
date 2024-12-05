
fun main() {
    val lines = readInput("Day05")

    val (rulesInput, updatesInput) = lines.joinToString("\n").split("\n\n")
    val rules = rulesInput.lines().map { it.split("|").let { (x, y) -> x.toInt() to y.toInt() } }
    val updates = updatesInput.lines().map { line -> line.split(",").map { it.toInt() } }

    val result = calculateMiddlePageSumForIncorrectUpdates(rules, updates)
    println(result)
}

fun calculateMiddlePageSumForIncorrectUpdates(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
    var total = 0

    for(update in updates) {
        val relevantRules = rules.filter { (x, y) -> x in update && y in update }

        if(!isTopologicallySorted(update, relevantRules)) {
            val correctedOrder = topologicalSort(update, relevantRules)

            val midIndex = correctedOrder.size / 2
            total += correctedOrder[midIndex]
        }
    }

    return total
}

fun topologicalSort(order: List<Int>, rules: List<Pair<Int, Int>>): List<Int> {
    val graph = mutableMapOf<Int, MutableList<Int>>()
    val inDegree = mutableMapOf<Int, Int>()
    val nodes = mutableSetOf<Int>()

    for ((x, y) in rules) {
        graph.computeIfAbsent(x) { mutableListOf() }.add(y)
        inDegree[y] = inDegree.getOrDefault(y, 0) + 1
        inDegree.putIfAbsent(x, 0)
        nodes.add(x)
        nodes.add(y)
    }

    val orderSet = order.toSet()
    val filteredGraph = graph.filterKeys { it in orderSet }
        .mapValues { (_, neighbors) -> neighbors.filter { it in orderSet } }
    val filteredInDegree = inDegree.filterKeys { it in orderSet }.toMutableMap()

    val queue = ArrayDeque(filteredInDegree.filterValues { it == 0 }.keys)
    val sortedOrder = mutableListOf<Int>()

    while(queue.isNotEmpty()) {
        val current = queue.removeFirst()
        sortedOrder.add(current)
        for(neighbor in filteredGraph[current] ?: emptyList()) {
            filteredInDegree[neighbor] = filteredInDegree.getValue(neighbor) - 1
            if(filteredInDegree[neighbor] == 0) {
                queue.add(neighbor)
            }
        }
    }

    return sortedOrder
}
