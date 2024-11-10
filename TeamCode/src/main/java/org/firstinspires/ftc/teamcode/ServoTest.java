package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class ServoTest extends LinearOpMode {
    private Servo servoO;
    public void runOpMode(){
        waitForStart();
        while(opModeIsActive()){
            servoO.setPosition(1);
        }
    }
}
