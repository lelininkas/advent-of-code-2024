fun main() {

    fun matchWord(word: Array<Char>): Boolean {
        val searchWord = arrayOf('X', 'M', 'A', 'S')
        return word.contentEquals(searchWord)
    }

    fun getWordsFromIndex(index: Pair<Int, Int>, matrix: List<CharArray>): List<Array<Char>> {
        val words = mutableListOf<Array<Char>>()
        val leftLimit = 0
        val rightLimit = matrix[index.second].size - 1
        val topLimit = 0
        val bottomLimit = matrix.size - 1

        //./
        //o--
        //.\
        if (index.first + 3 <= rightLimit) {
            val word = matrix[index.second].slice(index.first..index.first + 3).toTypedArray()
            words.add(word)

            if (index.second - 3 >= topLimit) {
                val word = arrayOf(
                    matrix[index.second][index.first],
                    matrix[index.second - 1][index.first + 1],
                    matrix[index.second - 2][index.first + 2],
                    matrix[index.second - 3][index.first + 3]
                )
                words.add(word)
            }

            if (index.second + 3 <= bottomLimit) {
                val word = arrayOf(
                    matrix[index.second][index.first],
                    matrix[index.second + 1][index.first + 1],
                    matrix[index.second + 2][index.first + 2],
                    matrix[index.second + 3][index.first + 3]
                )
                words.add(word)
            }
        }

        //.|
        //.o
        if (index.second - 3 >= topLimit) {
            val word = arrayOf(
                matrix[index.second][index.first],
                matrix[index.second - 1][index.first],
                matrix[index.second - 2][index.first],
                matrix[index.second - 3][index.first]
            )
            words.add(word)
        }

        //.o
        //.|
        if (index.second + 3 <= bottomLimit) {
            val word = arrayOf(
                matrix[index.second][index.first],
                matrix[index.second + 1][index.first],
                matrix[index.second + 2][index.first],
                matrix[index.second + 3][index.first]
            )
            words.add(word)
        }

        //.\
        //--o
        //./
        if (index.first - 3 >= leftLimit) {
            val word = arrayOf(
                matrix[index.second][index.first],
                matrix[index.second][index.first - 1],
                matrix[index.second][index.first - 2],
                matrix[index.second][index.first - 3]
            )
            words.add(word)

            if (index.second - 3 >= topLimit) {
                val word = arrayOf(
                    matrix[index.second][index.first],
                    matrix[index.second - 1][index.first - 1],
                    matrix[index.second - 2][index.first - 2],
                    matrix[index.second - 3][index.first - 3]
                )
                words.add(word)
            }

            if (index.second + 3 <= bottomLimit) {
                val word = arrayOf(
                    matrix[index.second][index.first],
                    matrix[index.second + 1][index.first - 1],
                    matrix[index.second + 2][index.first - 2],
                    matrix[index.second + 3][index.first - 3]
                )
                words.add(word)
            }
        }
        return words
    }

    fun part1(input: List<String>): Int {
        var total = 0
        val matrix = input.map { line -> line.toCharArray() }
        matrix.indices.forEach { i ->
            matrix[i].indices.forEach { j ->
//                println("""[$i, $j]""")
                val words = getWordsFromIndex(Pair(j, i), matrix)
                total += words.count { matchWord(it) }
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val searchWord = arrayOf('M', 'A', 'S')
        var total = 0
        val matrix = input.map { line -> line.toCharArray() }

        val leftLimit = 0
        val rightLimit = matrix[0].size - 1
        val topLimit = 0
        val bottomLimit = matrix.size - 1

        matrix.indices.forEach { y ->
            matrix[y].indices.forEach { x ->
//                println("""[$y, $x]""")
                if (matrix[y][x] == 'A') {
                    if (x + 1 <= rightLimit && x - 1 >= leftLimit && y + 1 <= bottomLimit && y - 1 >= topLimit) {
                        // /
                        val first = arrayOf(
                            matrix[y + 1][x + 1],
                            matrix[y][x],
                            matrix[y - 1][x - 1]
                        )
                        // \
                        val second = arrayOf(
                            matrix[y + 1][x - 1],
                            matrix[y][x],
                            matrix[y - 1][x + 1]
                        )

                        if ((first.contentEquals(searchWord) || first.reversed().toTypedArray()
                                .contentEquals(searchWord)) &&
                            (second.contentEquals(searchWord) || second.reversed().toTypedArray()
                                .contentEquals(searchWord))
                        ) {
                            total += 1
                        }

                    }

                }
            }
        }
        return total
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
