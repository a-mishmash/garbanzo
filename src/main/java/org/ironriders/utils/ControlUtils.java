package org.ironriders.utils;

import org.ironriders.constants.DriveConstants;

/**
 * Class for mapping input to cubic control curve + deadband.
 */
public class ControlUtils {
    
    /** 
     * Takes the input, sets it to zero if it's too close to zero (deadband),
     * and maps to a cubic equation to make control feel more natural
    */
    public static double controlCurve(double input) {
        double withDeadband = Math.abs(input) < DriveConstants.CONTROLLER_DEADBAND ? 0.0 : input;
        return Math.pow(withDeadband, 3);
    }
}