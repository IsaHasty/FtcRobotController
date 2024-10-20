package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ArmPractice extends LinearOpMode {
    private DcMotor elevator;
    private Servo pincher;
    public void runOpMode(){
        pincher = hardwareMap.get(Servo.class, "pincher");
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevator.setTargetPosition(0);
        elevator.setMode((DcMotor.RunMode.RUN_TO_POSITION));
        pincher.setPosition(0);

        waitForStart();
        if(gamepad1.y){
            raiseElevator();
        }
        if(gamepad1.a){
            setElevatorGrabbingPosition();
        }
        if(gamepad1.x){
            openPincher();
        }
        if(gamepad1.b){
            closePincher();
        }
    }
    public void setElevatorGrabbingPosition(){
        elevator.setTargetPosition(30);
        elevator.setPower(.5);
    }
    public void raiseElevator(){
        elevator.setTargetPosition(60);
        elevator.setPower(.5);
    }
    public void openPincher(){
        pincher.setPosition(0.5);
    }
    public void closePincher(){
        pincher.setPosition(0.0);
    }
}
