public class RingBuffer
{
    private double[] data;          // items in the buffer
    private int      first;         // index for the next dequeue or peek
    private int      last;          // index for the next enqueue
    private int      size;          // number of items in the buffer

    /** create an empty buffer, with given max capacity */
    public RingBuffer(int capacity) {
    	data = new double[capacity];
    	first = 0;
    	last = 0;
    	size = 0;
    }

    /** return number of items currently in the buffer */
    public int size() {
        return size;
    }

    /** is the buffer empty (size equals zero)? */
    public boolean isEmpty() {
    	if (size == 0) {
    		return true;
    	}
        return false;
    }

    /** is the buffer full (size equals array capacity)? */
    public boolean isFull() {
    	if (size == data.length)  {
    		return true;
    	}
        return false;
    }

    /** add item x to the end */
    public void enqueue(double x) {
    	if (isFull() == true) {
    		throw new RuntimeException("Ring buffer is full");
    	}
    	data[last] = x;
    	last++;
    	size++;

    	if (last >= data.length) {
    		last = 0;
    	}
    }

    /** delete and return item from the front */
    public double dequeue() {
    	if (isEmpty() == true) {
    		throw new RuntimeException("Ring buffer is empty");
    	}
    	double tempValue = data[first];
    	first++;
    	if (first >= data.length) {
    		first = 0;
    	}
    	size--;
    	return tempValue;
    }

    /** return (but do not delete) item from the front */
    public double peek() {
        if (isEmpty() == true) {
          throw new RuntimeException("Ring buffer is empty");
        }
        return data[first];
    }

    /** a simple test of the constructor and methods in RingBuffer */
    public static void main(String[] args) {
        int N = 100;
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) {
            buffer.enqueue(i);
        }
        
        double t = buffer.dequeue();
        buffer.enqueue(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        
        System.out.println(buffer.peek());

        /*
         * Your program should produce the following output:
         *
         *  Size after wrap-around is 100
			*  5050.0
         */
    }
}
