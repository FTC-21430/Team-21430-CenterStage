package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.operatorState.extendLift;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.liftOut;

public abstract class AutonomousFunction extends OdometryCode {

    public void PurplePixelRed() {
        if (Zone == 1) {

            RunToPoint(-46,-42);
            intakeMotor.setPower(0.4);
            resetRuntime();
            while (3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            //Remember to turn off
        } else if (Zone == 2) {
            RunToPoint(-37, -35);

            intakeMotor.setPower(0.4);
            resetRuntime();
            while (3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            RunToPoint(-37,-40);


        }else{
        if (Zone == 3) {
            RunToPoint(-38,-34);
            RunToPoint(-24,-34);
            intakeMotor.setPower(0.4);
            resetRuntime();
            while (0.3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
//            Remember to turn off
        }}
    }
    public void PurplePixelBlue() {
        if (Zone == 3) {

            RunToPoint(-46 + 2,42);
            intakeMotor.setPower(0.4);
            resetRuntime();
            while (3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            //Remember to turn off
        } else if (Zone == 2) {
            RunToPoint(-37 -1, 35);

            intakeMotor.setPower(0.4);
            resetRuntime();
            while (3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            RunToPoint(-37 -1,40);


        }else{
            if (Zone == 1) {
                RunToPoint(-38+2,34);
                RunToPoint(-24+2,34);
                intakeMotor.setPower(0.4);
                resetRuntime();
                while (0.3 >= getRuntime() && opModeIsActive()){
                    ProportionalFeedbackControl();
                }
                //Remember to turn off
            }}
    }
            public void YellowPixel () {
                currentState = extendLift;
                stateMachineTimer = getRuntime();
                pixelLiftMotor.setTargetPosition(safeLiftHeight);
                while   (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight){
                    keepAtPoint(RobotX,RobotY);
                    ProportionalFeedbackControlAuto();
                }

                barHeightHigh = true;
                fourBarServo.setPosition(0.015);
                stateMachineTimer = getRuntime();
                while (stateMachineTimer >= getRuntime() - 1) {
                    keepAtPoint(RobotX,RobotY);
                    ProportionalFeedbackControlAuto();

                }
                liftPosition = safeLiftHeight;

                RunToPoint(48.5, -36);
                backDepositorServo.setPosition(1);
                frontDepositorServo.setPosition(0);
                stateMachineTimer = getRuntime();
                while (stateMachineTimer > getRuntime()-2){
                 //   ProportionalFeedbackControl();
                    keepAtPoint(RobotX,RobotY);
                }
                RunToPoint(44, -36);
             //   RunToPoint();
            }

            public void Park(){
                RunToPoint(49, -61);
                RunToPoint(61, -60);
            }
        }
