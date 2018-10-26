package org.firstinspires.ftc.teamcode;

/**
 * Created by student on 10/26/2017.
 */

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "THE MOST SAVAGE DANCE MOVES IN THE WHOLE WORLD", group = "Autonomous")
public class DriveTest extends Autonomous {
    @Override
    public void runPath() {

        move(2,0.7);
        pivot(90,0.7);
        move(2,-0.7);
        pivot(90,-0.7);
        move(2,0.7);
        pivot(90,0.7);
        move(2,-0.7);
        pivot(90,-0.7);
        move(2,0.7);
        pivot(90,0.7);
        move(2,-0.7);
        pivot(90,-0.7);
    }
}