package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.HardwareMap.UnderscoreHardwareMap;

@Autonomous(name = "Auto mode 2 Test", group = "UnderScore")
public class Auto_test_2 extends LinearOpMode {

    private UnderscoreHardwareMap hm;

    @Override
    public void runOpMode() {
        hm = new UnderscoreHardwareMap(hardwareMap);
        hm.turnRight(360, 1);
    }

}
