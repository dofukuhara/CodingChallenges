package sortcolors

import utils.assertWith

fun main() {

    val solution = SortColor()

    with(solution) {
        bubbleSort(intArrayOf(2,0,2,1,1,0)).assertWith("[0,0,1,1,2,2]")
        bubbleSort(intArrayOf(2,0,1)).assertWith("[0,1,2]")
        bubbleSort(intArrayOf(1,0)).assertWith("[0,1]")
        bubbleSort(intArrayOf(1,2,3,4,5,6,7,8)).assertWith("[1,2,3,4,5,6,7,8]")
        bubbleSort(intArrayOf(8,1,2,3,4,5,6,7)).assertWith("[1,2,3,4,5,6,7,8]")
    }

    with(solution) {
        selectionSort(intArrayOf(2,0,2,1,1,0)).assertWith("[0,0,1,1,2,2]")
        selectionSort(intArrayOf(2,0,1)).assertWith("[0,1,2]")
        selectionSort(intArrayOf(1,0)).assertWith("[0,1]")
        selectionSort(intArrayOf(1,2,3,4,5,6,7,8)).assertWith("[1,2,3,4,5,6,7,8]")
        selectionSort(intArrayOf(8,1,2,3,4,5,6,7)).assertWith("[1,2,3,4,5,6,7,8]")
        selectionSort(intArrayOf(1,2,3,4,5,7,6,8)).assertWith("[1,2,3,4,5,6,7,8]")
    }

    with(solution) {
        mergeSort(intArrayOf(2,0,2,1,1,0)).assertWith("[0,0,1,1,2,2]")
        mergeSort(intArrayOf(2,0,1)).assertWith("[0,1,2]")
        mergeSort(intArrayOf(1,0)).assertWith("[0,1]")
        mergeSort(intArrayOf(1,2,3,4,5,6,7,8)).assertWith("[1,2,3,4,5,6,7,8]")
        mergeSort(intArrayOf(8,1,2,3,4,5,6,7)).assertWith("[1,2,3,4,5,6,7,8]")
        mergeSort(intArrayOf(1,2,3,4,5,7,6,8)).assertWith("[1,2,3,4,5,6,7,8]")
    }

    with(solution) {
        heapSort(intArrayOf(1,2,3,4,5)).assertWith("[1,2,3,4,5]")
        heapSort(intArrayOf(8,7,6,5,4,3,2,1)).assertWith("[1,2,3,4,5,6,7,8]")
        heapSort(intArrayOf(2,0,2,1,1,0)).assertWith("[0,0,1,1,2,2]")
        heapSort(intArrayOf(2,0,1)).assertWith("[0,1,2]")
        heapSort(intArrayOf(1,0)).assertWith("[0,1]")
        heapSort(intArrayOf(1,2,3,4,5,6,7,8)).assertWith("[1,2,3,4,5,6,7,8]")
        heapSort(intArrayOf(8,1,2,3,4,5,6,7)).assertWith("[1,2,3,4,5,6,7,8]")
        heapSort(intArrayOf(1,2,3,4,5,7,6,8)).assertWith("[1,2,3,4,5,6,7,8]")
    }
}