package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {
    private ArrayList<T> heap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    // Renvoie et supprime l'élément minimum du tas
    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T minElement = heap.get(0);
        // Remplacer la racine par le dernier élément
        T lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        return minElement;
    }

    // Renvoie l'élément minimum sans le supprimer
    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    // Ajoute un élément dans le tas
    public void push(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    // Retourne le nombre d'éléments dans le tas
    public int count() {
        return heap.size();
    }

    // Maintient la propriété de tas en remontant un élément à sa position correcte
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (comparator.compare(heap.get(index), heap.get(parentIndex)) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    // Maintient la propriété de tas en descendant un élément à sa position correcte
    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallest = index;

            if (leftChildIndex < size && comparator.compare(heap.get(leftChildIndex), heap.get(smallest)) < 0) {
                smallest = leftChildIndex;
            }
            if (rightChildIndex < size && comparator.compare(heap.get(rightChildIndex), heap.get(smallest)) < 0) {
                smallest = rightChildIndex;
            }
            if (smallest == index) {
                break;
            }
            swap(index, smallest);
            index = smallest;
        }
    }

    // Échange deux éléments dans le tas
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
