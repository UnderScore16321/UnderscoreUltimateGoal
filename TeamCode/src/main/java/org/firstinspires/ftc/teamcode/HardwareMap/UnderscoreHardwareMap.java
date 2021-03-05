package org.firstinspires.ftc.teamcode.HardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static android.os.SystemClock.sleep;

public class UnderscoreHardwareMap {

    static final int MILLS_PER_FOOT = 273;
    static final int MILLS_PER_ROTATION = 1300;

    final DcMotor rightBack;
    final DcMotor leftBack;
    final DcMotor rightFront;
    final DcMotor leftFront;

    public UnderscoreHardwareMap(HardwareMap hwM) {
        rightBack = hwM.dcMotor.get("BackRight");
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront = hwM.dcMotor.get("FrontRight");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack = hwM.dcMotor.get("BackLeft");
        leftFront = hwM.dcMotor.get("FrontLeft");
    }

    public void driveFeet(double feet, double power) {
        setPower(power);
        sleep((long) (MILLS_PER_FOOT * feet * Math.abs((1.0 / power))));
        setPower(0);
    }

    public void turnLeft(double degrees, double power) {
        setPower(-power, power);
        sleep((long) (MILLS_PER_ROTATION * (degrees / 360.0)));
        setPower(0);
    }

    public void turnRight(double degrees, double power) {
        setPower(power, -power);
        sleep((long) (MILLS_PER_ROTATION * (degrees / 360.0)));
        setPower(0);
    }

    public void setPower(double power) {
        rightBack.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        leftFront.setPower(power);
    }

    public void setPower(double left, double right) {
        rightBack.setPower(right);
        leftBack.setPower(left);
        rightFront.setPower(right);
        leftFront.setPower(left);
    }

}
