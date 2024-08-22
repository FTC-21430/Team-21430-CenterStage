package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestRobot extends Robot{
    public HardwareMap hardwareMap = null;

    public TestRobot(HardwareMap hardwareMapInput, Telemetry telemetry1){
        hardwareMap = hardwareMapInput;
        telemetry = telemetry1;
    }

   public MecanumDriveTrain mecanumDriveTrain = new MecanumDriveTrain(hardwareMap);

}
