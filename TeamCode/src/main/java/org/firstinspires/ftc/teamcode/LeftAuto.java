package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class LeftAuto extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor elevator;
    private Servo elevatorPincher;
    private DcMotor arm;
    private Servo intakeGrabber;

    public void runOpMode(){
        //defines motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        elevatorPincher = hardwareMap.get(Servo.class, "elevatorPincher");
        intakeGrabber = hardwareMap.get(Servo.class, "intakeGrabber");

        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(3000);
        arm.setDirection(DcMotor.Direction.REVERSE);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setTargetPosition(0);
        elevator.setDirection(DcMotor.Direction.REVERSE);
        elevator.setMode((DcMotor.RunMode.RUN_TO_POSITION));
        int numClicks = 0;
        elevatorPincher.setPosition(0.3);
        waitForStart();

        while(opModeIsActive()){
            driveStraight(0.3, false);
            sleep(400);
            stopMotors();

            driveSideways(0.5,true); // Adjust the power value if needed
            sleep(1400);
            stopMotors();

            driveStraight(0.25, false);
            sleep(800);
            stopMotors();

            raiseElevator();
            sleep(1500);

            driveStraight(0.25, false);
            sleep(900);
            stopMotors();
            releaseElevator();
            sleep(200);
            stopMotors();

            driveStraight(.3, true);
            lowerElevator();
            sleep(1000);
            stopMotors();

            turn(.5);
            sleep(800);

            driveStraight(.3, true);
            sleep(2100);

            driveSideways(.5, false);
            sleep(600);
            stopMotors();

            lowerArm();
            sleep(2000);
            elevatorPincher.setPosition(.3);
            sleep(1000);

            // Optionally, you can break the loop if only one sequence is needed
            break;

        }
    }

    public void driveStraight(double pwrX, boolean isReversed){
        if(isReversed){
            frontLeft.setPower(-pwrX);
            backLeft.setPower(-pwrX);
            frontRight.setPower(-pwrX);
            backRight.setPower(-pwrX);
        }
        else{
            frontLeft.setPower(pwrX);
            backLeft.setPower(pwrX);
            frontRight.setPower(pwrX);
            backRight.setPower(pwrX);
        }

    }

    public void turn(double direction){
        frontLeft.setPower(-direction);
        backLeft.setPower(-direction);
        frontRight.setPower(direction);
        backRight.setPower(direction);
    }

    public void driveSideways(double speed, boolean isReversed){
        if(isReversed){
            frontLeft.setPower(speed);
            backLeft.setPower(-speed);
            frontRight.setPower(-speed);
            backRight.setPower(speed);
        }
        else{
            frontLeft.setPower(-speed);
            backLeft.setPower(speed);
            frontRight.setPower(speed);
            backRight.setPower(-speed);
        }
    }

    public void driveDiagonally(double tgtX, double tgtY){
        frontLeft.setPower(tgtY-tgtX);
        frontRight.setPower(tgtY+tgtX);
        backLeft.setPower(tgtY+tgtX);
        backRight.setPower(tgtY-tgtX);
    }
    public void setElevatorGrabbingPosition(){
        elevator.setPower(.5);
        elevator.setTargetPosition(200);
    }
    public void raiseElevator(){
        elevator.setPower(1);
        elevator.setTargetPosition(1500);
    }
    public void lowerElevator(){
        elevator.setPower(1);
        elevator.setTargetPosition(0);
    }
    public void releaseElevator(){
        elevator.setPower(1);
        elevator.setTargetPosition(1150);
        sleep(500);
        elevatorPincher.setPosition(0.8);
        sleep(500);
    }

    public void changePincher(int numClicks){
        if(numClicks%2 ==0){
            elevatorPincher.setPosition(.8);
        }
        else{
            elevatorPincher.setPosition(.3);
        }
    }


    public void armUp(){
        arm.setPower(0.5);
        arm.setTargetPosition(2600);

    }
    public void lowerArm(){
        arm.setPower(0.5);
        //arm.setTargetPosition(20);
    }
    public void armDown(){
        arm.setPower(0.5);
        arm.setTargetPosition(250);
    }

    public void armOut(){
        arm.setPower(0.5);
        arm.setTargetPosition(500);
    }
    public void stopMotors(){
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
}


