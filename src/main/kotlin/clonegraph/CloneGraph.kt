package clonegraph

import common.structures.GraphNode
import utils.printableGraph

class CloneGraph {
    init {
        val codeChallenge = "\n" +
            "********************* Clone Graph **********************\n" +
            "* Given a reference of a node in a connected           *\n" +
            "* undirected graph.                                    *\n" +
            "* Return a deep copy (clone) of the graph.             *\n" +
            "* Each node in the graph contains a value (int) and a  *\n" +
            "* list (List[Node]) of its neighbors.                  *\n" +
            "*                                                      *\n" +
            "* Test case format:                                    *\n" +
            "* For simplicity, each node's value is the same as the *\n" +
            "* node's index (1-indexed). For example, the first     *\n" +
            "* node with val == 1, the second node with val == 2,   *\n" +
            "* and so on. The graph is represented in the test case *\n" +
            "* using an adjacency list.                             *\n" +
            "*                                                      *\n" +
            "* An adjacency list is a collection of unordered lists *\n" +
            "* used to represent a finite graph. Each list describes*\n" +
            "* the set of neighbors of a node in the graph.         *\n" +
            "* The given node will always be the first node with    *\n" +
            "* val = 1. You must return the copy of the given node  *\n" +
            "* as a reference to the cloned graph.                  *\n" +
            "*                                                      *\n" +
            "* Example 1:                                           *\n" +
            "*    | Input: adjList = [[2,4],[1,3],[2,4],[1,3]]      *\n" +
            "*    | Output: [[2,4],[1,3],[2,4],[1,3]]               *\n" +
            "*    | Explanation: There are 4 nodes in the graph.    *\n" +
            "*         1st node (val = 1)'s neighbors are 2nd node  *\n" +
            "*                  (val = 2) and 4th node (val = 4).   *\n" +
            "*         2nd node (val = 2)'s neighbors are 1st node  *\n" +
            "*                  (val = 1) and 3rd node (val = 3).   *\n" +
            "*         3rd node (val = 3)'s neighbors are 2nd node  *\n" +
            "*                  (val = 2) and 4th node (val = 4).   *\n" +
            "*         4th node (val = 4)'s neighbors are 1st node  *\n" +
            "*                  (val = 1) and 3rd node (val = 3).   *\n" +
            "*                                                      *\n" +
            "* Example 2:                                           *\n" +
            "*    | Input: adjList = [[]]                           *\n" +
            "*    | Output: [[]]                                    *\n" +
            "*    | Explanation: Note that the input contains one   *\n" +
            "*         empty list. The graph consists of only one   *\n" +
            "*         node with val = 1 and it does not have any   *\n" +
            "*         neighbors.                                   *\n" +
            "*                                                      *\n" +
            "* Example 3:                                           *\n" +
            "*    | Input: adjList = []                             *\n" +
            "*    | Output: []                                      *\n" +
            "*    | Explanation: This an empty graph, it does not   *\n" +
            "*         have any nodes.                              *\n" +
            "*                                                      *\n" +
            "* Constraints:                                         *\n" +
            "*    | The number of nodes in the graph is in the      *\n" +
            "*      range [0, 100].                                 *\n" +
            "*    | 1 <= Node.val <= 100                            *\n" +
            "*    | Node.val is unique for each node.               *\n" +
            "*    | There are no repeated edges and no self-loops   *\n" +
            "*      in the graph.                                   *\n" +
            "*    | The Graph is connected and all nodes can be     *\n" +
            "*      visited starting from the given node.           *\n" +
            "********************************************************\n"
        println(codeChallenge)
    }

    fun cloneGraph(node: GraphNode?): GraphNode? {
        printSolutionStart(node)

        if (node == null) return null.printSolutionEnd()

        var newGraphHead: GraphNode? = null
        val graphRunner = mutableListOf<GraphNode>().apply {
            add(node)
        }
        val newGraphHash = mutableMapOf<Int, GraphNode>()

        while (graphRunner.isNotEmpty()) {
            val currentNode = graphRunner.removeFirst()

            // When current node was not yet processed
            if (!newGraphHash.contains(currentNode.`val`)) {
                // Create a new node
                val newNode = GraphNode(currentNode.`val`)

                // Check if we need to create new children nodes or re-use if they were already created
                currentNode.neighbors.cloneOrReuseNodes(newGraphHash, graphRunner, newNode)

                // Add this cloned node in the new graph
                newGraphHash[newNode.`val`] = newNode

                if (newGraphHead == null) {
                    newGraphHead = newNode
                }
            } else {
                // If this node was processed, we need to check if the neighbors for it was generated or not
                val newNode = newGraphHash.getValue(currentNode.`val`)
                if (currentNode.neighbors.size != newNode.neighbors.size) {

                    currentNode.neighbors.cloneOrReuseNodes(newGraphHash, graphRunner, newNode)
                }
            }
        }

        return newGraphHead.printSolutionEnd()
    }
}

private fun ArrayList<GraphNode?>.cloneOrReuseNodes(
    graphHash: MutableMap<Int, GraphNode>,
    graphRunner: MutableList<GraphNode>,
    newNode: GraphNode) {
    this.forEach { childNode ->
        childNode?.let {
            if (!graphHash.contains(it.`val`)) {
                graphRunner.add(it)
                val newChildNode = GraphNode(it.`val`)
                graphHash[newChildNode.`val`] = newChildNode
            }
            newNode.neighbors.add(graphHash[it.`val`])
        }
    }
}

private fun printSolutionStart(node: GraphNode?) {
    println("========================================================")
    println("- Input: graph = ${node.printableGraph()}")
}

private fun GraphNode?.printSolutionEnd(): GraphNode? {
    println("- Output: ${this.printableGraph()}")
    println("========================================================\n")
    return this
}