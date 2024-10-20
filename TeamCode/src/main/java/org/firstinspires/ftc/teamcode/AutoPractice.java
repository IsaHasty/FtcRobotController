package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class AutoPractice extends LinearOpMode{

    //declares motors
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private Servo pincher;

    public void runOpMode() {
        //defines motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        pincher = hardwareMap.get(Servo.class, "pincher");
        waitForStart();

        while (opModeIsActive()){
            sidewaysRight(0.5);
            sleep(500);
            stopMotors();
            driveForward(0.5);
            sleep(500);
            stopMotors();
        }


    }
    public void driveForward(double speed){
        frontLeft.setPower(speed);
        backLeft.setPower(speed);
        frontRight.setPower(-speed);
        backRight.setPower(-speed);
    }
    public void driveBackward(double speed){
        frontLeft.setPower(-speed);
        backLeft.setPower(-speed);
        frontRight.setPower(speed);
        backRight.setPower(speed);
    }
    public void turnRight(double speed){
        frontLeft.setPower(speed);
        backLeft.setPower(speed);
        frontRight.setPower(speed);
        backRight.setPower(speed);
    }
    public void turnLeft(double speed){
        frontLeft.setPower(-speed);
        backLeft.setPower(-speed);
        frontRight.setPower(-speed);
        backRight.setPower(-speed);
    }
    public void stopMotors(){
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
    public void sidewaysRight(double speed){
        frontLeft.setPower(-speed);
        backLeft.setPower(speed);
        frontRight.setPower(-speed);
        backRight.setPower(speed);
    }
    public void sidewaysLeft(double speed){
        frontLeft.setPower(speed);
        backLeft.setPower(-speed);
        frontRight.setPower(speed);
        backRight.setPower(-speed);
    }
    public void openPincher(){

    }


}

