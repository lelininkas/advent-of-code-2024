fun main() {

    // mul(ddd,ddd)
    fun part1(input: List<String>): Int {
        val regex = """(mul\()([0-9]{1,3})(,)([0-9]{1,3})(\))""".toRegex()
        var result = 0

        input.forEach {
            val matches = regex.findAll(it)
            for (match in matches) {
                val (_, a, _,b) = match.destructured
//                println(a)
//                println(b)
                result += a.toInt() * b.toInt()
            }
        }

        return result
    }

    // The do() instruction enables future mul instructions.
    // The don't() instruction disables future mul instructions.
    // Only the most recent do() or don't() instruction applies. At the beginning of the program, mul instructions are enabled.
    fun part2(input: List<String>): Int {
        val regex = """do\(\)|don't\(\)|(mul\()([0-9]{1,3})(,)([0-9]{1,3})(\))""".toRegex()
        var result = 0
        var enabled = true
        input.forEach {
            val matches = regex.findAll(it)
            for (match in matches) {
                if (match.value == "do()") {
                    enabled = true
                } else if (match.value == "don't()") {
                    enabled = false
                } else if (enabled) {
                    val (_, a, _,b) = match.destructured
                    result += a.toInt() * b.toInt()
                }
            }
        }

        return result
    }

    check(part1(readInput("Day03_test")) == 161)
    check(part2(readInput("Day03_test_pt2")) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
