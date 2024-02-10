package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.operatorState.intakeCancel;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.intakeDone;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name="TESTING", group="Linear Opmode")
public class TESTING extends AutonomousFunction{

  //  @Override
    double intakePower = 0;
    double AlignWait;
    public void runOpMode() {
        Init();


        waitForStart();

        runtime.reset();



}}


