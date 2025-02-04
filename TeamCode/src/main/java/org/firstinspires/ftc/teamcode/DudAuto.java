package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class DudAuto extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor elevator;
    private Servo elevatorPincher;
    private DcMotor arm;
    private CRServo intakeSpinner;

    public void runOpMode(){
        //defines motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        elevatorPincher = hardwareMap.get(Servo.class, "elevatorPincher");
        intakeSpinner = hardwareMap.get(CRServo.class, "intakeSpinner");

        arm = hardwareMap.get(DcMotor.class, "arm");
        //arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
            driveStraight(0.5, false);
            sleep(250);
            stopMotors();

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
        elevator.setTargetPosition(1250);
        sleep(500);
        elevatorPincher.setPosition(0.8);
        sleep(500);
        setElevatorGrabbingPosition();
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

