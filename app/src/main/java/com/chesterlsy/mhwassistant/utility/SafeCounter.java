package com.chesterlsy.mhwassistant.utility;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * App: MyAndroidTricks
 * Author: Siyi Liu
 * Created: 2019-01-30 11:37
 * Description:
 */
public class SafeCounter {

    private final AtomicInteger count;

    public SafeCounter() {
        this.count = new AtomicInteger(0);
    }

    /**
     * get() – gets the value from the memory, so that changes made by other threads are visible; equivalent to reading a volatile variable.
     * @return
     */
    public int getValue() {
        return count.get();
    }

    /**
     * A typical CAS operation works on three operands:
     *
     *      The memory location on which to operate (M)
     *      The existing expected value (A) of the variable
     *      The new value (B) which needs to be set
     *
     * The CAS operation updates atomically the value in M to B, but only if the existing value in M matches A, otherwise no action is taken.
     *
     * compareAndSet() – returns true when it succeeds, else false
     */
    public void increment() {
        while (true) {
            int existingValue = getValue();
            int newValue = existingValue + 1;
            if (count.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
}
