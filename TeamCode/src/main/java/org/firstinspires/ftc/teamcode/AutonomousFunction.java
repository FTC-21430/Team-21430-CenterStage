package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.operatorState.extendLift;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.liftOut;

public abstract class AutonomousFunction extends OdometryCode {

    public void PurplePixelRed() {
        if (Zone == 1) {

            RunToPoint(-46,-42 , 5);
            intakeMotor.setPower(0.4);
            resetRuntime();
            while (3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            //Remember to turn off
        } else if (Zone == 2) {
            RunToPoint(-37, -35, 5);

            intakeMotor.setPower(0.4);
            resetRuntime();
            while (3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            RunToPoint(-37,-40,5);


        }else{
        if (Zone == 3) {
            RunToPoint(-38,-34,5);
            RunToPoint(-24,-34,5);
            intakeMotor.setPower(0.4);
            resetRuntime();
            while (0.3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
//            Remember to turn off

        }
            }
        intakeMotor.setPower(0);}
    public void PurplePixelBlue() {
        if (Zone == 3) {

            RunToPoint(-46,42,5);
            intakeMotor.setPower(0.4);
            resetRuntime();
            while (3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            //Remember to turn off
        } else if (Zone == 2) {
            RunToPoint(-37, 35, 5);

            intakeMotor.setPower(0.4);
            resetRuntime();
            while (3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            RunToPoint(-37,40 ,5);


        }else{
            if (Zone == 1) {
                RunToPoint(-38,34,5);
                RunToPoint(-24,34,5);
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
                while   (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight && opModeIsActive()){
                    keepAtPoint(RobotX,RobotY);
                    ProportionalFeedbackControlAuto();
                }

                barHeightHigh = true;
                fourBarServo.setPosition(0.015);
                stateMachineTimer = getRuntime();
                while (stateMachineTimer >= getRuntime() - 1  && opModeIsActive()) {
                    keepAtPoint(RobotX,RobotY);
                    ProportionalFeedbackControlAuto();

                }
                //Zone = 1;
                pixelLiftMotor.setTargetPosition(380);

                if (Zone == 3) {
                    RunToPoint(36, -42, 2.4, 1);
                    RunToPoint(49.5, -42, 1, 1);


                } else if (Zone == 2) {

                    RunToPoint(49.5, -36, 1,1);

                }else {
                    if (Zone == 1) {
                        RunToPoint(36,-30, 2.4, 1);
                        RunToPoint(49.5, -30, 1);

                    }
                }
                backDepositorServo.setPosition(1);
                frontDepositorServo.setPosition(0);
                stateMachineTimer = getRuntime();
                while (stateMachineTimer > getRuntime()-2  && opModeIsActive()){
                 //   ProportionalFeedbackControl();
                    keepAtPoint(RobotX,RobotY);
                }
                backDepositorServo.setPosition(0.5);
                frontDepositorServo.setPosition(0.5);
                RunToPoint(44, -36, 2);
                fourBarServo.setPosition(0.92);
             //   RunToPoint();
                stateMachineTimer = getRuntime();
                while (stateMachineTimer > getRuntime()-0.5  && opModeIsActive()){
                    //   ProportionalFeedbackControl();
                    keepAtPoint(RobotX,RobotY);
                }
                pixelLiftMotor.setTargetPosition(1);
                while (stateMachineTimer > getRuntime()-0.7  && opModeIsActive()){
                    //   ProportionalFeedbackControl();
                    keepAtPoint(RobotX,RobotY);
                }
            }

            public void Park(){
                RunToPoint(49, -61,5);
                RunToPoint(61, -60,5);
            }
        }
