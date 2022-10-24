package engine.timer;

@SuppressWarnings("unused")
public final class Repeater {

    private int tpc;
    private final Proc[] proc;
    private boolean isRunning;

    public Repeater(int tpc, Proc... proc) {
        this.tpc = tpc;
        this.proc = proc;
    }

    public void setTpc(int tpc) {
        this.tpc = tpc;
        if (this.tpc < 0) this.tpc = 0;
    }

    public void start(boolean isNewThread) {
        if (isRunning) return;
        isRunning = true;
        if (isNewThread) new Thread(this::func).start();
        else func();
    }

    public final void start() {
        start(false);
    }

    public final void stop() {
        if (!isRunning) return;
        isRunning = false;
    }

    private void func() {
        long time = System.currentTimeMillis();
        long dif = 0, now;
        while (isRunning) {
            now = System.currentTimeMillis();
            dif += (now - time);
            time = now;
            if (dif * tpc >= 1000) {
                dif -= 1000 / tpc;
                for (Proc proc : proc) {
                    proc.apply();
                }
            }
        }
    }

}
