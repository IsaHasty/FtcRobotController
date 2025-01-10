package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ElevatorDown extends LinearOpMode {
    private DcMotor elevator;
    public void runOpMode(){
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        if(gamepad1.dpad_down){
            elevator.setPower(-0.5);
        }
    }

}
