package de.cristiano.marathon.lt;

class MovingAverage {

    private final int size;
    private final int[] array;
    private int arraySum;
    private int elements;

    public MovingAverage(int size) {
        this.array = new int[size];
        this.size = size;
        this.elements = 0;
        this.arraySum = 0;
    }

    public double next(int val) {
        var index = elements % size;
        elements++;

        arraySum -= array[index];
        array[index] = val;
        arraySum += array[index];

        return arraySum / (double) Math.min(size, elements);
    }
}
