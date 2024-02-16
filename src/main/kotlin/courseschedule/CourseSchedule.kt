package courseschedule

import utils.printableArrayOfIntArray

class CourseSchedule {
    /*
        TOPOLOGICAL SORT:
        - Topological sorting is a way to represent a way to arrange a collection of tasks or events
          in such a sequence that each task comes before the tasks that depend on it.
        - If the graph has a cycle, it cannot be topologically sorted.

        ALGORITHM:
          The most common algorithm to perform topological sorting is based on Depth-First Search (DFS).
          Here are the basic steps:
          1. Identify the nodes with no incoming edge (in-degree == 0);
          2. Add them into the queue;
            3. Remove the node from the head of the queue;
            4. Check all the nodes that are dependant of this current node;
            5. Subtract by one the in-degree of the dependants (as this current node is being 'removed from the graph')
            6. If after step 5, the dependant in-degree reaches 0, it means that it can be added to the queue to be processed
            7. Repeat from step 3, until the queue gets empty.
     */

    init {
        val codeChallenge = "\n" +
            "********************** Course Schedule **********************\n" +
            "* There are a total of numCourses courses you have to take, *\n" +
            "* labeled from 0 to numCourses - 1. You are given an array  *\n" +
            "* prerequisites where prerequisites[i] = [ai, bi] indicates *\n" +
            "* that you must take course bi first if you want to take    *\n" +
            "* course ai.                                                *\n" +
            "*                                                           *\n" +
            "* For example, the pair [0, 1], indicates that to take      *\n" +
            "* course 0 you have to first take course 1.                 *\n" +
            "* Return true if you can finish all courses. Otherwise,     *\n" +
            "* return false.                                             *\n" +
            "*                                                           *\n" +
            "* Example 1:                                                *\n" +
            "*    | Input: numCourses = 2, prerequisites = [[1,0]]       *\n" +
            "*    | Output: true                                         *\n" +
            "*    | Explanation: There are a total of 2 courses to take. *\n" +
            "*        To take course 1 you should have finished course 0.*\n" +
            "*        So it is possible.                                 *\n" +
            "*                                                           *\n" +
            "* Example 2:                                                *\n" +
            "*    | Input: numCourses = 2, prerequisites = [[1,0],[0,1]] *\n" +
            "*    | Output: false                                        *\n" +
            "*    | Explanation: There are a total of 2 courses to take. *\n" +
            "*       To take course 1 you should have finished course 0, *\n" +
            "*       and to take course 0 you should also have finished  *\n" +
            "*       course 1. So it is impossible.                      *\n" +
            "*                                                           *\n" +
            "* Constraints:                                              *\n" +
            "*    | 1 <= numCourses <= 2000                              *\n" +
            "*    | 0 <= prerequisites.length <= 5000                    *\n" +
            "*    | prerequisites[i].length == 2                         *\n" +
            "*    | 0 <= ai, bi < numCourses                             *\n" +
            "*    | All the pairs prerequisites[i] are unique.           *\n" +
            "*************************************************************\n"
        println(codeChallenge)
    }

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        printSolutionStart(numCourses, prerequisites)

        // courseGraph to represent course dependencies and an array to track in-degrees of courses.
        val courseGraph = mutableMapOf<Int,MutableList<Int>>()
        val indegrees = IntArray(numCourses)

        // Building up the Graph while counting in-degrees for the course.
        prerequisites.forEach { (course, prereq) ->
            courseGraph.computeIfAbsent(prereq) { mutableListOf() }.add(course)

            indegrees[course] += 1
        }

        // Initialize the courses list with the ones with no prerequisites (in-degrees of 0).
        val coursesList = indegrees.mapIndexed { index, _ -> index }
            .filter { index -> indegrees[index] == 0 }
            .toMutableList()

        // If none of the courses can be done without a prerequisite, then it will be impossible to finish this course.
        if (coursesList.isEmpty()) return false.printSolutionEnd()

        // Counter to track the number of courses that can be taken.
        var courseTakenCounter = 0

        // While the list is not empty, process courses that can be taken.
        while (coursesList.isNotEmpty()) {
            // Analyze the first course in the list.
            val course = coursesList.removeFirst()

            // Increment the course counter, indicating that you can take this course.
            courseTakenCounter++

            // Retrieve all the courses that can be taken after finishing this current course from the graph
            courseGraph[course]?.let { followingCourses ->
                for (followingCourse in followingCourses) {
                    // Decreasing the in-degree counter of this following course by one
                    indegrees[followingCourse] -= 1
                    if (indegrees[followingCourse] == 0) {
                        // If all pre-requirements for this following course were done, we can add it to the courseList.
                        coursesList.add(followingCourse)
                    }
                }
            }
        }

        // If the course taken counter is equal to the total number of courses, than you can finish them all!
        return (courseTakenCounter == numCourses).printSolutionEnd()
    }
}

private fun printSolutionStart(numCourses: Int, prerequisites: Array<IntArray>) {
    println("=============================================================")
    println("- Input: numCourses = $numCourses, prerequisites = ${prerequisites.printableArrayOfIntArray()}")
}

private fun Boolean.printSolutionEnd(): Boolean {
    println("- Output: $this")
    println("=============================================================\n")
    return this
}