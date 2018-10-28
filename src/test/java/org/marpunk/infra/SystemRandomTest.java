package org.marpunk.infra;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class SystemRandomTest {


    private SystemRandom systemRandom = new SystemRandom();

    @Test
    public void should_include_min_and_max(){
        boolean includeMinValue = false;
        boolean includeMaxValue = false;
        int counter = 0;
        while(!includeMinValue || !includeMaxValue) {
            counter++;
            int g = systemRandom.getIntBetween(3, 8);
            includeMinValue |= (g == 3);
            includeMaxValue |= (g == 8);
            if(g<3){
                Assert.fail(g+" is lower than 3");
            }
            if(g>8){
                Assert.fail(g+" is greater than 8");
            }
            if(counter==1000){
                Assert.fail(message(includeMinValue, includeMaxValue));
            }
        }

    }

    private String message(boolean includeMinValue, boolean includeMaxValue) {
        String message = "Should include ";
        if(!includeMinValue){
            message+="min ";
        }
        if(!includeMinValue && !includeMaxValue){
            message+="and ";
        }
        if(!includeMaxValue){
            message+="max";
        }
        message+=" value";
        return message;
    }


}