package org.firstinspires.ftc.teamcode;

/**
 * Created by student on 2/3/2017.
 */

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "MissionCornerRed", group = "Autonomous")
public class MissionCornerRed extends Autonomous {
    @Override
    public void runPath() {
        robot.leftMotor.setPower(-0.4);
        robot.rightMotor.setPower(-0.4);
        for (int i=0; i <= 3000; i++){
            sleep(1);
        }
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
        //move(8, -0.4);
        //sleep(1000000);
    }
}
