package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DriveMain extends LinearOpMode {

    //declares motors
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    //private Servo pincher;

    public void runOpMode() {
        //defines motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        //pincher = hardwareMap.get(Servo.class, "pincher");
        //pincher.setPosition(0.0);
        waitForStart();

        double tgtPowerX = 0.0;
        double tgtPowerY = 0.0;
        double tgtPowerTurn = 0.0;
        double tgtPowerSide = 0.0;
        boolean isReversed = false;

        while (opModeIsActive()){
            if(gamepad1.a){
                isReversed = !isReversed;
                sleep(300); //makes sure reverse isn't toggled multiple times
            }
            //driving
            tgtPowerX = gamepad1.left_stick_x;
            tgtPowerY = gamepad1.left_stick_y;
            driveStraight(tgtPowerY, isReversed);


            //turning
            tgtPowerTurn = gamepad1.right_stick_x;
            turn(tgtPowerTurn);

            //sideways
            tgtPowerSide = gamepad1.left_stick_x;
            driveSideways(tgtPowerSide, isReversed);

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
}
//things i could do: make an array of motors