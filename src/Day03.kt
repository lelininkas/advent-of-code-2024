private const val mulRegex = """mul\((?<first>\d{1,3}),(?<second>\d{1,3})\)"""
private const val doRegex = """do\(\)"""
private const val dontRegex = """don't\(\)"""
private const val allInstructionsRegex = """${doRegex}|${dontRegex}|${mulRegex}"""

fun main() {

    fun multiplyGroups(match: MatchResult): Long =
        (match.groups["first"]?.value?.toLong() ?: 0) * (match.groups["second"]?.value?.toLong() ?: 0)

    // mul(ddd,ddd)
    fun part1(input: List<String>): Long =
        input.sumOf { mulRegex.toRegex().findAll(it).sumOf { multiplyGroups(it) } }

    // The do() instruction enables future mul instructions.
    // The don't() instruction disables future mul instructions.
    // Only the most recent do() or don't() instruction applies. At the beginning of the program, mul instructions are enabled.
    fun part2(input: List<String>): Long {
        var result = 0L
        var enabled = true
        input.forEach { line ->
            allInstructionsRegex.toRegex().findAll(line).forEach { match ->
                when (match.value) {
                    "do()" -> enabled = true
                    "don't()" -> enabled = false
                    else -> if (enabled) result += multiplyGroups(match)
                }
            }
        }
        return result
    }

    check(part1(readInput("Day03_test")) == 161L)
    check(part2(readInput("Day03_test_pt2")) == 48L)

    val input = readInput("Day03")
    check(part1(input) == 190604937L)
    check(part2(input) == 82857512L)

//    part1(input).println()
//    part2(input).println()
}
