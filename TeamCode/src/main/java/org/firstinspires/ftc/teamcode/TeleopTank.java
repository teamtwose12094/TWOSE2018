/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorAdafruitRGB;

/**
 * This OpMode uses the common HardwareK9bot class to define the devices on the robot.
 * All device access is managed through the HardwareK9bot class. (See this class for device names)
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a basic Tank Drive Teleop for the K9 bot
 * It raises and lowers the arm using the Gampad Y and A buttons respectively.
 * It also opens and closes the claw slowly using the X and B buttons.
 *
 * Note: the configuration of the servos is such that
 * as the arm servo approaches 0, the arm position moves up (away from the floor).
 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Teleop Tank", group="K9bot")
public class TeleopTank extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareK9bot robot = new HardwareK9bot();              // Use a K9's hardware
    double colourStickPosition = robot.COLOURSTICK_HOME;
    //double          armPosition     = robot.ARM_HOME;                   // Servo safe position
    //double          clawPosition    = robot.CLAW_HOME;                  // Servo safe position
    final double COLOURSTICK_UP = 1;
    final double COLOURSTICK_DOWN = 0;
    final double OPEN_LEFT = 0;
    final double CLOSE_LEFT = 0.32;
    final double RELIC_CLOSE_LEFT = 0.51;
    final double OPEN_RIGHT = 1;
    final double CLOSE_RIGHT = 0.49;
    final double RELIC_CLOSE_RIGHT = 0.3;
    public boolean relicMode = false;
    //final double    CLAW_SPEED      = 0.01 ;                            // sets rate to move servo
    //final double    ARM_SPEED       = 0.01 ;                            // sets rate to move servo

    @Override
    public void runOpMode() {
        double left;
        double right;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Hello human,", "it is I, the FRENCHIEST FRY");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;
            robot.leftMotor.setPower(left);
            robot.rightMotor.setPower(right);

            // Use gamepad Y & A raise and lower the arm
            if ((gamepad1.a)|| (gamepad2.a)) {
                robot.armMotor.setPower(-0.3);

                //armPosition += ARM_SPEED;
            } else if ((gamepad1.y)|| (gamepad2.y)) {
                robot.armMotor.setPower(0.3);
                //armPosition -= ARM_SPEED;

                // Use gamepad X & B to open and close the claw
            } else {
                robot.armMotor.setPower(0);
            }
            if ((gamepad1.dpad_up) || (gamepad2.dpad_up))  {
                robot.slideMotor.setPower(0.5);
            } else if ((gamepad1.dpad_down) || (gamepad2.dpad_down))  {
                robot.slideMotor.setPower(-0.5);
            } else {
                robot.slideMotor.setPower(0);
            }
            if ((gamepad1.b) || (gamepad2.b)) {
                    robot.clawLeft.setPosition(OPEN_LEFT);
                    robot.clawRight.setPosition(OPEN_RIGHT);

            } else if ((gamepad1.x) || (gamepad2.x)) {
                if (relicMode == false){
                    robot.clawLeft.setPosition(CLOSE_LEFT);
                    robot.clawRight.setPosition(CLOSE_RIGHT);}
                else{
                    robot.clawLeft.setPosition(RELIC_CLOSE_LEFT);
                    robot.clawRight.setPosition(RELIC_CLOSE_RIGHT);}
            }
            if ((gamepad1.left_bumper) || (gamepad2.left_bumper)) {
                if (relicMode == false){
                    relicMode = true;}
                else{
                    relicMode = false;}
            }
            if ((gamepad1.dpad_left) || (gamepad2.dpad_left)) {
                robot.colourStick.setPosition(COLOURSTICK_UP);
                //colourStickPosition = COLOURSTICK_DOWN;
                //clawPosition += CLAW_SPEED;
            }

            if ((gamepad1.dpad_right) || (gamepad2.dpad_right)) {
                robot.colourStick.setPosition(COLOURSTICK_DOWN);
            }


        }
    }

    }


