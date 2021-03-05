package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Auto mode 2 Test", group="UnderScore")
public class Auto_test_2 extends LinearOpMode {
    private final int MILLS_PER_FOOT = 273;
    private final int MILLS_PER_ROTATION = 1300;
    private DcMotor rightBack;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor leftFront;
    @Override
    public void runOpMode()
    {
        setup();
        turnRight(360, 1);
    }
    private void driveFeet(double feet, double power)
    {
        setPower(power);
        sleep((long) (MILLS_PER_FOOT*feet*Math.abs((1.0/power))));
        setPower(0);
    }
    private void turnLeft(double degrees, double power)
    {
        setPower(-power, power);
        sleep((long) (MILLS_PER_ROTATION*(degrees/360.0)));
        setPower(0);
    }
    private void turnRight(double degrees, double power)
    {
        setPower(power, -power);
        sleep((long) (MILLS_PER_ROTATION*(degrees/360.0)));
        setPower(0);
    }
    private void setPower(double power)
    {
        rightBack.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        leftFront.setPower(power);
    }
    private void setPower(double left, double right)
    {
        rightBack.setPower(right);
        leftBack.setPower(left);
        rightFront.setPower(right);
        leftFront.setPower(left);
    }
    private void setup()
    {
        rightBack = hardwareMap.dcMotor.get("BackRight");
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront = hardwareMap.dcMotor.get("FrontRight");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack = hardwareMap.dcMotor.get("BackLeft");
        leftFront = hardwareMap.dcMotor.get("FrontLeft");
    }
}
