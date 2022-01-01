/* Dominic Martinez, 9/6/02
   The LunarLander class does all the physics needed for controlling
   the ship in LunarLanderMain.
*/

public class LunarLander {
    public static final int INITIAL_VELOCITY = 40;   // in meters/second
    public static final int INITIAL_ALTITUDE = 1000; // in meters
    public static final int INITIAL_FUEL     = 25;   // 1 fuel used per thrust
    public static final int GRAVITY          = 2;    // gravitational acceleration
                                                     // in meters/second/second
    public static final int THRUST           = 4;    // thrust acceleration in
                                                     // meters/second/second
    public static final int SAFE_LANDING     = 4;    // speed at which lander can
                                                     // safely land in meters/second
    private int Velocity = 0;                        // will be used for velocity

    private int Altitude = 0;                        // will be used for altitude

    private int Fuel = 0;                            // will be the fuel tank

	private int NumberofThrusts = 0; 				 // will be used for keeping track of user
	                                                 // invoked thrust

	private int myThrust = 0;                        // will be temp variable for thrust and fuel

    // sets the lunar lander to its initial configuration
    public void reset() {
		    Velocity = INITIAL_VELOCITY;             // resets velocity to the pre-set constant
		    Altitude = INITIAL_ALTITUDE;             // resets altitude to the pre-set constant
    		Fuel = INITIAL_FUEL;                     // resets fuel to the pre-set constant
    		myThrust = 0;                            // resets the temp variable
    		NumberofThrusts = 0;                     // resets the thrust-tracking variable
    }

    // returns to Main the current altitude of the lunar lander
    public int getAltitude() {
			return (Altitude);
    }

    // returns to Main the current velocity of the lunar lander
    public int getVelocity() {
		    return (Velocity);
    }

    // returns to Main the current fuel amount of the lunar lander
    public int getFuel() {
			return (Fuel);
    }

    // records a thrust request made by the player
    public void thrust() {
		NumberofThrusts++;                          // uses increment operator to keep track
													// of user invoked thrusts

	}

    // updates the velocity, altitude, and fuel and applies
    // the thrust requested during the last second
    public void tick() {
		myThrust = Math.min(NumberofThrusts, Fuel); // compares thrusts pressed by user to how
													// much fuel left in tank
		Fuel = Fuel - myThrust;                     // takes compared number and subtracts from tank
		NumberofThrusts = 0;                        // reset thrusts pressed by user
		Velocity = Velocity + GRAVITY - (myThrust * THRUST);  // calculates velocity by adding gravity
		                                                      // and subtracting for negative forces
		                                                      // in this scenario, the thrust
		Altitude = Altitude - Velocity;             // calculates altitude by subtracting velocity

    }
}