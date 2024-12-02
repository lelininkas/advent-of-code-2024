import kotlin.math.abs

fun main() {
//    The levels are either all increasing or all decreasing.
//    Any two adjacent levels differ by at least one and at most three.

    fun part1(input: List<String>): Int {

        val reports = input.map { it.split("""\s+""".toRegex()).map(String::toInt) }
        var safe = 0

        reports.forEach { report ->
                var isIncreasing: Boolean? = null
                val isSafe = report.zipWithNext { a, b ->
                        if (isIncreasing == null) {
                            isIncreasing = b > a
                        }
                        val diff = abs(b - a)
                    !((isIncreasing != b > a) || (diff < 1 || diff > 3))
                }.contains(false).not()

                if (isSafe) {
                    safe++
                }
        }
        return safe
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { it.split("""\s+""".toRegex()).map(String::toInt) }
        var safe = 0

        fun checkSafety(report: List<Int>): Boolean {
            var isIncreasing: Boolean? = null
            return report.zipWithNext { a, b ->
                if (isIncreasing == null) {
                    isIncreasing = b > a
                }
                val diff = abs(b - a)
                !((isIncreasing != b > a) || (diff < 1 || diff > 3))
            }.contains(false).not()
        }

        reports.forEach { report ->
            var isSafe = checkSafety(report)
            if (!isSafe){
                for (i in 0 until report.size){
                    val damper = report.toMutableList()
                    damper.removeAt(i)
                    isSafe = checkSafety(damper)
                    if (isSafe){
                        break
                    }
                }
            }


            if (isSafe) {
                safe++
            }
        }
        return safe
    }


    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
