package org.kwis.msp.lcdui;

public abstract class Jlet {

    public static final int ACTIVE = 1;
    public static final int PAUSED = 2;
    public static final int DESTROYED = 3;

    private final EventQueue eventQueue;
    private static Jlet activeJlet;

    // Jlet 생성자
    protected Jlet() {
        eventQueue = new EventQueue();
    }

    // 지정된 Jlet을 활성화하는 메서드
    public static void setActiveJlet(Jlet ql) {
        activeJlet = ql;
    }
    // 현재 활성화된 Jlet을 반환하는 메서드
    public static Jlet getActiveJlet() {
        return activeJlet;
    }

    // 주어진 ID에 해당하는 Jlet을 반환하는 메서드
    public static Jlet getJletFromPID(int id) {
        return activeJlet;
    }

    // 현재 수행 중인 Jlet을 반환하는 메서드
    public static Jlet getCurrentJlet() {
        // 현재 수행 중인 Jlet 반환 작업 수행
        return activeJlet;
    }

    // 프로그램 ID를 반환하는 메서드
    public int getCurrentProgramID() {
        // 프로그램 ID 반환 작업 수행
        return 0;
    }

    // 프로그램이 시작될 때 호출되는 메서드
    protected abstract void startApp(String[] args);

    // 프로그램이 일시 정지될 때 호출되는 메서드
    protected abstract void pauseApp();

    // 프로그램이 다시 시작될 때 호출되는 메서드
    protected abstract void resumeApp();

    // 프로그램이 종료될 때 호출되는 메서드
    protected abstract void destroyApp(boolean unconditional) throws JletStateChangeException;;

    // 프로그램이 종료되었음을 알리는 메서드
    public final void notifyDestroyed() throws JletStateChangeException {
        return;
    }

    // 응용 프로그램의 프라퍼티를 반환하는 메서드
    public final String getAppProperty(String key) {
        // 프라퍼티 반환 작업 수행
        return null;
    }

    // 이벤트 큐를 반환하는 메서드
    public final EventQueue getEventQueue() {
        // 이벤트 큐 반환 작업 수행
        return eventQueue;
    }

    // 모든 자원을 제거하는 메서드
    public static void removeAllResource(int id) {
        // 모든 자원 제거 작업 수행
    }

}
