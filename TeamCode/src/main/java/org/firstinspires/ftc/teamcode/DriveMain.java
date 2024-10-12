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

    public void runOpMode() {
        //defines motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        waitForStart();

        double tgtPower = 0.0;
        double tgtPowerTurn = 0.0;
        double tgtPowerSide = 0.0;
        boolean isReversed = false;

        while (opModeIsActive()){
            if(gamepad1.a){
                isReversed = !isReversed;
                sleep(300); //makes sure reverse isn't toggled multiple times
            }
            //driving
            tgtPower = gamepad1.left_stick_y;
            driveStraight(tgtPower, isReversed);

            //turning
            tgtPowerTurn = gamepad1.right_stick_x;
            turn(tgtPowerTurn);

            //sideways
            tgtPowerSide = gamepad1.left_stick_x;
            driveSideways(tgtPowerSide, isReversed);
        }


    }

    public void driveStraight(double speed, boolean isReversed){
        if(isReversed){
            frontLeft.setPower(-speed);
            backLeft.setPower(-speed);
            frontRight.setPower(speed);
            backRight.setPower(speed);
        }
        else{
            frontLeft.setPower(speed);
            backLeft.setPower(speed);
            frontRight.setPower(-speed);
            backRight.setPower(-speed);
        }

    }

    public void turn(double direction){
        frontLeft.setPower(-direction);
        backLeft.setPower(-direction);
        frontRight.setPower(-direction);
        backRight.setPower(-direction);
    }

    public void driveSideways(double speed, boolean isReversed){
        if(isReversed){
            frontLeft.setPower(speed);
            backLeft.setPower(-speed);
            frontRight.setPower(speed);
            backRight.setPower(-speed);
        }
        else{
            frontLeft.setPower(-speed);
            backLeft.setPower(speed);
            frontRight.setPower(-speed);
            backRight.setPower(speed);
        }
    }
}
//things i could do: make an array of motors