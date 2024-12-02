import kotlin.math.abs

fun main() {
    fun prepareInput(input: List<String>): List<List<Int>> =
        input.map { it.split("""\s+""".toRegex()).map(String::toInt) }

    // The levels are either all increasing or all decreasing.
    // Any two adjacent levels differ by at least one and at most three.
    fun checkSafety(report: List<Int>): Boolean {
        var isIncreasing: Boolean? = null
        return report.zipWithNext().all { (a, b) ->
            isIncreasing = isIncreasing ?: (b > a)
            isIncreasing == (b > a) && abs(b - a) in 1..3
        }
    }

    fun part1(reports: List<List<Int>>): Int {
        return reports.count { checkSafety(it) }
    }

    // Damper the report by removing one level and check if it's still safe.
    fun part2(reports: List<List<Int>>): Int {
        return reports.count { report ->
            checkSafety(report) || report.indices.any { i ->
                checkSafety(report.toMutableList().apply { removeAt(i) })
            }
        }
    }

    val testInput = prepareInput(readInput("Day02_test"))
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = prepareInput(readInput("Day02"))
    part1(input).println()
    part2(input).println()
}


