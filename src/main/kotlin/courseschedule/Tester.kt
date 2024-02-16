package courseschedule

import utils.assertWith

fun main() {
    val solution = CourseSchedule()

    solution.canFinish(
        numCourses = 2,
        prerequisites = arrayOf(intArrayOf(1,0))
    ).assertWith(true)

    solution.canFinish(
        numCourses = 2,
        prerequisites = arrayOf(intArrayOf(1,0), intArrayOf(0,1))
    ).assertWith(false)

    solution.canFinish(
        numCourses = 5,
        prerequisites = arrayOf(intArrayOf(1,0), intArrayOf(2,1), intArrayOf(3,2), intArrayOf(4,3))
    ).assertWith(true)
}