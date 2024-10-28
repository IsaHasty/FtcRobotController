package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class DriveMain extends LinearOpMode {

    //declares motors
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor elevator;
    private Servo pincher;
    private DcMotor arm;
    private CRServo intake;

    public void runOpMode() {
        //defines motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        pincher = hardwareMap.get(Servo.class, "pincher");
        intake = hardwareMap.get(CRServo.class, "intake");

        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setDirection(DcMotor.Direction.REVERSE);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(0);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setTargetPosition(0);
        elevator.setDirection(DcMotor.Direction.REVERSE);
        elevator.setMode((DcMotor.RunMode.RUN_TO_POSITION));
        int numClicks = 0;
        pincher.setPosition(0.8);
        waitForStart();


        double tgtPowerY = 0.0;
        double tgtPowerTurn = 0.0;
        double tgtPowerSide = 0.0;
        boolean isReversed = false;
        boolean isIntakeTurning = false;

        while (opModeIsActive()){
            if(gamepad1.right_bumper){
                isReversed = !isReversed;
                sleep(300); //makes sure reverse isn't toggled multiple times
            }
            //driving
            tgtPowerY = gamepad1.left_stick_y;
            driveStraight(tgtPowerY, isReversed);


            //turning
            tgtPowerTurn = gamepad1.right_stick_x;
            turn(tgtPowerTurn);

            //sideways
            tgtPowerSide = gamepad1.left_stick_x;
            driveSideways(tgtPowerSide, isReversed);


            if(gamepad1.x){
                releaseElevator();
            }

            if (gamepad1.y) {
                raiseElevator();
            }

            if (gamepad1.a) {
                setElevatorGrabbingPosition();
            }
            if (gamepad1.b) {
                numClicks++;
                sleep(200);
                changePincher(numClicks);
            }

            if(gamepad2.b){
                isIntakeTurning = !isIntakeTurning;
                sleep(200);
                changeIntake(isIntakeTurning);
            }

            if(gamepad2.a){
                armDown();
            }

            if(gamepad2.x){
                armOut();
            }
            if(gamepad2.y){
                armUp();
            }
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
    public void releaseElevator(){
        elevator.setPower(1);
        elevator.setTargetPosition(1250);
        sleep(500);
        pincher.setPosition(0.8);
        sleep(500);
        setElevatorGrabbingPosition();
    }

    public void changePincher(int numClicks){
        if(numClicks%2 ==0){
            pincher.setPosition(.8);
        }
        else{
            pincher.setPosition(.3);
        }
    }

    public void changeIntake(boolean isIntakeTurning){
        if(isIntakeTurning){
            intake.setPower(-1);
        }
        else{
            intake.setPower(0);
        }
    }

    public void armUp(){
        arm.setPower(0.5);
        arm.setTargetPosition(2600);

    }

    public void armDown(){
        arm.setPower(0.5);
        arm.setTargetPosition(250);
    }

    public void armOut(){
        arm.setPower(0.5);
        arm.setTargetPosition(500);
    }
}
//things i could do: make an array of motors