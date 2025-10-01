package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Intake")
public class Intake extends LinearOpMode {  // Removed 'abstract'

    private CRServo servo1;

    @Override
    public void runOpMode() {
        servo1 = hardwareMap.get(CRServo.class, "servo1");


        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                setServo1Power(1.0);  // Run forward
            }
            if(gamepad1.b){
                setServo1Power(0.0);  // Stop when b is pressed
            }
        }
    }

    public void setServo1Power(double power) {
        servo1.setPower(power);  // Use setPower for CRServo
    }
}
