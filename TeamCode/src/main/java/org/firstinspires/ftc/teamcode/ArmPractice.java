package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ArmPractice extends LinearOpMode {
    private DcMotor elevator;
    private Servo pincher;
    public void runOpMode() {
        pincher = hardwareMap.get(Servo.class, "pincher");
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setTargetPosition(0);
        elevator.setDirection(DcMotor.Direction.REVERSE);
        elevator.setMode((DcMotor.RunMode.RUN_TO_POSITION));
        int numClicks = 0;
        pincher.setPosition(0.8);

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.y) {
                raiseElevator();
            }

            if (gamepad1.a) {
                setElevatorGrabbingPosition();
            }
            if (gamepad1.x) {
                numClicks++;
                sleep(200);
                openPincher(numClicks);
            }
            if (gamepad1.b) {
                closePincher();
            }
        }
    }
    public void setElevatorGrabbingPosition(){
        elevator.setPower(.5);
        elevator.setTargetPosition(149);
    }
    public void raiseElevator(){
        elevator.setPower(1);
        elevator.setTargetPosition(1400);
    }
    public void releaseElevator(){
        elevator.setPower(1);
        elevator.setTargetPosition(1000);
    }

    public void openPincher(int numClicks){
        if(numClicks%2 ==0){
            pincher.setPosition(.8);
        }
        else{
            pincher.setPosition(.3);
        }

    }
    public void closePincher(){
        pincher.setPosition(0.3);
    }
}
