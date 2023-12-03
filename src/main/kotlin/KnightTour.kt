class KnightTour {
    companion object {
        private const val N = 8

        private fun isSafe(x: Int, y: Int, sol: List<List<Int>>): Boolean {
            return (x >= 0) && (x < N) && (y >= 0) && (y < N) && (sol[x][y] == -1)
        }

        private fun printSolution(sol: List<List<Int>>) {
            for (x in 0..<N) {
                for (y in 0..<N) {
                    print(sol[x][y].toString().padEnd(2, ' ') + " ")
                }
                println()
            }
        }

        fun solveKT(): Boolean {
            val sol = List(N) { MutableList(N) { -1 } }
            val xMove = listOf(2, 1, -1, -2, -2, -1, 1, 2)
            val yMove = listOf(1, 2, 2, 1, -1, -2, -2, -1)

            sol[0][0] = 0

            if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
                println("Solution does not exist!")
                return false
            }
            printSolution(sol)
            return true
        }

        private fun solveKTUtil(
            x: Int,
            y: Int,
            movei: Int,
            sol: List<MutableList<Int>>,
            xMove: List<Int>,
            yMove: List<Int>
        ): Boolean {
            var nextX: Int;
            var nextY: Int
            if (movei == N * N) return true
            for (i in 0..<N) {
                nextX = x + xMove[i]
                nextY = y + yMove[i]
                if (isSafe(nextX, nextY, sol)) {
                    sol[nextX][nextY] = movei
                    if (solveKTUtil(nextX, nextY, movei + 1, sol, xMove, yMove)) return true
                    sol[nextX][nextY] = -1
                }
            }
            return false
        }
    }
}