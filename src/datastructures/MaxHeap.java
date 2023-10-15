package datastructures;

import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> implements IHeap<T>{
    private ArrayList<T> heap;
    private ArrayList<T> list;

    public MaxHeap(ArrayList<T> list) {
        this.list = list;
        this.heap = list;
        buildMaxHeap();
    }

    @Override
    public int parent(int index) {
        return (int) (Math.floor((index + 1) / 2) - 1);
    }

    @Override
    public int left(int index) {
        return 2 * (index + 1) - 1;
    }

    @Override
    public int right(int index) {
        return 2 * (index + 1);
    }

    @Override
    public void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public void insert(T value) {
        // add element at the end
        this.heap.add(value);
        int currentIndex = this.heap.size() - 1;

        // keep heap properties
        // maxHeapify(currentIndex);
        // no hago un maxHeapify para mantener las propiedades, hago un increaseKey:
        heapIncreaseKey(currentIndex);
    }

    @Override
    public T extractMax() {
        if (this.heap.isEmpty()) {
            System.out.println("heap its empty");
            return null;
        }

        if (this.heap.size() == 1) {
            return this.heap.remove(0);
        }

        // extract max element of the heap
        T maxValue = this.heap.get(0);
        this.heap.set(0, this.heap.remove(this.heap.size() - 1));
        // keep properties of the heap
        maxHeapify(0);

        return maxValue;
    }

    @Override
    public void maxHeapify(int currentIndex) {
        int leftIndex = left(currentIndex);
        int rightIndex = right(currentIndex);
        int largest = currentIndex;

        if (leftIndex < this.heap.size() && this.heap.get(leftIndex).compareTo(this.heap.get(largest)) > 0) {
            largest = leftIndex;
        }

        if (rightIndex < this.heap.size() && this.heap.get(rightIndex).compareTo(this.heap.get(largest)) > 0) {
            largest = rightIndex;
        }

        if (currentIndex != largest) {
            swap(currentIndex, largest);
            currentIndex = largest;
            // voy bajando
            maxHeapify(currentIndex);
        }
    }

    @Override
    public void buildMaxHeap() {
        for(int i = (this.list.size()) / 2; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    @Override
    public T heapMaximum() {
        return this.heap.get(0);
    }

    @Override
    public void heapIncreaseKey(int index) {
        while (index > 0 && this.heap.get(parent(index)).compareTo(this.heap.get(index)) < 0){
            swap(index, parent(index));
            index = parent(index);
        }
    }
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    public int size() {
        return this.heap.size();
    }

    public boolean delete(T value) {
        int index = this.heap.indexOf(value);
        if (index == -1) {
            return false;
        }
        this.heap.set(index, this.heap.remove(this.heap.size() - 1));
        maxHeapify(index);
        return true;
    }
}