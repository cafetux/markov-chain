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

        int bound = end - start + 1;
        if(bound <= 0){
            throw new IllegalArgumentException("start value "+start+" and end value "+end+" have invalid bound of "+bound);
        }
        return random.nextInt(bound)+start;
    }

}
