package utils

import common.structures.GraphNode


fun IntArray.printableIntArray(): String {
    val message = StringBuilder("[")
    this.forEachIndexed { index, element ->
        message.append(element)
        if (index < this.size - 1) {
            message.append(",")
        }
    }
    message.append("]")
    return message.toString()
}

fun CharArray.printableCharArray(): String {
    val message = StringBuilder("\n     [")
    this.forEachIndexed { index, element ->
        message.append("'$element'")
        if (index < this.size - 1) {
            message.append(",")
        }
    }
    message.append("]")
    return message.toString()
}

fun Array<IntArray>.printableArrayOfIntArray(): String {
    val message = StringBuilder("[")
    this.forEachIndexed { index, element ->
        message.append(element.printableIntArray())
        if (index < this.size - 1) {
            message.append(",")
        }
    }
    return message.append("]").toString()
}

fun Array<CharArray>.printableArrayOfCharArray(): String {
    val message = StringBuilder("[")
    this.forEachIndexed { index, element ->
        message.append(element.printableCharArray())
        if (index < this.size - 1) {
            message.append(",")
        }
    }
    return message.append("\n   ]").toString()
}

fun GraphNode?.printableGraph(): String {
    val currentResult = StringBuilder()
    if (this == null) {
        currentResult.append("[]")
    } else {
        val graphRunner = mutableListOf<GraphNode>().apply {
            add(this@printableGraph)
        }
        currentResult.append("[")
        val nodesAlreadyVisited = mutableSetOf<GraphNode>().apply { add(this@printableGraph) }
        while (graphRunner.isNotEmpty()) {
            val currentNode = graphRunner.removeFirst()

            currentResult.append("[${currentNode.`val`}|")
            currentNode.neighbors.forEachIndexed { index, graphNode ->
                currentResult.append("${graphNode?.`val`}")
                if (index < currentNode.neighbors.size - 1) {
                    currentResult.append(",")
                } else {
                    currentResult.append("]")
                }
                if (graphNode != null && !nodesAlreadyVisited.contains(graphNode)) {
                    nodesAlreadyVisited.add(graphNode)
                    graphRunner.add(graphNode)
                }
            }
            currentResult.append(",")
        }
        if (currentResult.endsWith(",")) {
            currentResult.deleteCharAt(currentResult.length - 1)
        }
        if (currentResult.endsWith("|")) {
            currentResult.append("]")
        }
        currentResult.append("]")
    }
    return currentResult.toString()
}