package org.marpunk.infra;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

/**
 *
 */
public class SystemRandomTest {


    private SystemRandom systemRandom = new SystemRandom();

    @Test
    public void should_have_same_start_and_end(){
        int actual = systemRandom.getIntBetween(3,3);
        assertThat(actual).isEqualTo(3);
    }

    @Test
    public void should_not_have_bound_of_0(){
        try {
            systemRandom.getIntBetween(4,3);
            fail("should fail");
        } catch(Exception e) {
            assertThat(e).hasMessageContaining("4").hasMessageContaining("3");
        }
    }

    @Test
    public void should_not_accept_end_before_start(){
        try {
            systemRandom.getIntBetween(5,3);
            fail("should fail");
        } catch(Exception e) {
            assertThat(e).hasMessageContaining("3").hasMessageContaining("5");
        }
    }

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