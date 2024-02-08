package utils



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