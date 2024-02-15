package clonegraph

import common.structures.GraphNode
import utils.assertWith

fun main() {
    val solution = CloneGraph()

    val nodeOne = GraphNode(1)
    val nodeTwo = GraphNode(2)
    val nodeThree = GraphNode(3)
    val nodeFour = GraphNode(4)
    nodeOne.neighbors = arrayListOf(nodeTwo, nodeFour)
    nodeTwo.neighbors = arrayListOf(nodeOne, nodeThree)
    nodeThree.neighbors = arrayListOf(nodeTwo, nodeFour)
    nodeFour.neighbors = arrayListOf(nodeOne, nodeThree)

    solution.cloneGraph(nodeOne).assertWith("[[1|2,4],[2|1,3],[4|1,3],[3|2,4]]")
    solution.cloneGraph(GraphNode(1)).assertWith("[[1|]]")
    solution.cloneGraph(null).assertWith("[]")
}