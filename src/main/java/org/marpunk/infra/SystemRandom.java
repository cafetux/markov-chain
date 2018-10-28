package org.marpunk.infra;

import org.marpunk.core.RandomGenerator;

import java.util.Random;

/**
 *
 */
public class SystemRandom implements RandomGenerator{
    private Random random = new Random();

    @Override
    public int getIntBetween(int start, int end) {
        return random.nextInt(end-start+1)+start;
    }

}
