package cz.cvut.fit.pjv.alsa.common.util;

import java.util.Random;

public class Sleeper {
    private final String name;
    private final Random random;

    public Sleeper(String name) {
        this.name = name;
        this.random = new Random();
    }

    public void randomSleep() {
        int time = (int) Math.round(random.nextGaussian() * 500 + 1200);
        if (time < 0)
            time = 0;

        System.out.printf("[%s] Waiting for %d...\n", name, time);
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
        }
    }

}
