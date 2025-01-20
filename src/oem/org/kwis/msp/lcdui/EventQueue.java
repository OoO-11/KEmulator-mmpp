package org.kwis.msp.lcdui;

public class EventQueue {
    public static final int EVENT_SIZE = 4;
    public static final int KEY_EVENT = 1;
    public static final int POINTER_EVENT = 2;
    public static final int TIMER_EVENT = 3;

    private int[] eventQueue;
    private int queueSize;
    private int queueHead;
    private int queueTail;

    public EventQueue() {
        eventQueue = new int[EVENT_SIZE * 10]; // 큐의 크기를 10으로 설정
        queueSize = 0;
        queueHead = 0;
        queueTail = 0;
    }

    public synchronized void postEvent(int[] event) {
        if (queueSize < eventQueue.length / EVENT_SIZE) {
            System.arraycopy(event, 0, eventQueue, queueTail * EVENT_SIZE, EVENT_SIZE);
            queueTail = (queueTail + 1) % (eventQueue.length / EVENT_SIZE);
            queueSize++;
            notifyAll();
        }
    }

    public synchronized void getNextEvent(int[] event) {
        while (queueSize == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // 인터럽트 처리
            }
        }
        System.arraycopy(eventQueue, queueHead * EVENT_SIZE, event, 0, EVENT_SIZE);
        queueHead = (queueHead + 1) % (eventQueue.length / EVENT_SIZE);
        queueSize--;
    }

    public void dispatchEvent(int[] event) {
        // 이벤트를 처리하는 로직을 구현합니다.
    }
}
