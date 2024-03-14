package org.firstinspires.ftc.teamcode.Genral;

import static org.firstinspires.ftc.teamcode.Genral.Robot.operatorState.extendLift;

public abstract class AutonomousFunction extends OdometryCode {
public boolean closeStartPos;
    public void PurplePixelBlueRight() {
        if (Zone == 3) {
            RunToPoint(-51.5,43,1,1.8);
            //RunToPoint(-56,-37, 0, 1.2);
            intakeMotor.setPower(0.4);
            resetRuntime();
            while (0.8 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            //Remember to turn off
        } else if (Zone == 2) {
            RunToPoint(-42, 37, 0, 1.6);

            intakeMotor.setPower(0.4);
            resetRuntime();
            while (0.8 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            RunToPoint(-37,40,0,1.6);


        }else{
            if (Zone == 1) {

                RunToPoint(-45,46,0,1.8);
                // RunToPoint(-38,-34,0,2);
                setTurn(-90);
                resetRuntime();
                while (0.5 >= getRuntime() && opModeIsActive()){
                    ProportionalFeedbackControl();
                }
                RunToPoint(-40,30,0,2);
                intakeMotor.setPower(0.4);
                resetRuntime();
                while (0.3 >= getRuntime() && opModeIsActive()){
                    ProportionalFeedbackControl();
                }
                RunToPoint(-48,35,0,1.5);
                setTurn(-180);
                resetRuntime();
                while (0.4 >= getRuntime() && opModeIsActive()) {
                    ProportionalFeedbackControl();
                }


            }
        }
        intakeMotor.setPower(0);}

//    public void PurplePixelBlueLeft() {
//        if (Zone == 3) {
//
//            RunToPoint(20,52,3,2);
//            setTurn(90);
//            resetRuntime();
//            while (0.5 >= getRuntime() && opModeIsActive()){
//                ProportionalFeedbackControl();
//            }
//            RunToPoint(30,33,0,1.4);
//            RunToPoint(12,33,0,1.4);
//            intakeMotor.setPower(0.4);
//            resetRuntime();
//            while (0.3 >= getRuntime() && opModeIsActive()){
//                ProportionalFeedbackControl();
//            }
//            RunToPoint(17,37,4,2);
//
//            setTurn(90);
//            resetRuntime();
//            while (0.4 >= getRuntime() && opModeIsActive()) {
//                ProportionalFeedbackControl();
//            }
//            //Remember to turn off
//        } else if (Zone == 2) {
//            RunToPoint(18, 50, 1, 2.5);
//            RunToPoint(18, 35, 0, 1.6);
//
//            intakeMotor.setPower(0.4);
//            resetRuntime();
//            while (0.8 >= getRuntime() && opModeIsActive()){
//                ProportionalFeedbackControl();
//            }
//
//
//
//        }else{
//            if (Zone == 1) {
//                RunToPoint(28.6,43,1,1.8);
//                //RunToPoint(-56,-37, 0, 1.2);
//                intakeMotor.setPower(0.4);
//                resetRuntime();
//                while (0.8 >= getRuntime() && opModeIsActive()){
//                    ProportionalFeedbackControl();
//                }
//
//
//
//            }
//        }
//        intakeMotor.setPower(0);}

    public void PurplePixelRedLeft() {
        if (Zone == 1) {
            RunToPoint(-51.5,-43,1,1.8);
            //RunToPoint(-56,-37, 0, 1.2);
            intakeMotor.setPower(0.6);
            resetRuntime();
            while (0.8 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            //Remember to turn off
        } else if (Zone == 2) {
            RunToPoint(-37, -35, 0, 1.6);

            intakeMotor.setPower(0.4);
            resetRuntime();
            while (0.8 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            RunToPoint(-37,-40,0,1.6);


        }else{
            if (Zone == 3) {

                RunToPoint(-45,-46,0,1.8);
                // RunToPoint(-38,-34,0,2);
                setTurn(-90);
                resetRuntime();
                while (0.5 >= getRuntime() && opModeIsActive()){
                    ProportionalFeedbackControl();
                }
                RunToPoint(-39.5,-31,0,2);
                intakeMotor.setPower(0.4);
                resetRuntime();
                while (0.3 >= getRuntime() && opModeIsActive()){
                    ProportionalFeedbackControl();
                }
                RunToPoint(-48,-35,0,1.5);
                setTurn(0);
                resetRuntime();
                while (0.4 >= getRuntime() && opModeIsActive()) {
                ProportionalFeedbackControl();
                }


            }
        }
        intakeMotor.setPower(0);}
    public void PurplePixelBlue() {
        if (Zone == 3) {

            RunToPoint(-46,42,5);
            intakeMotor.setPower(0.3);
            resetRuntime();
            while (3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            //Remember to turn off
        } else if (Zone == 2) {
            RunToPoint(-37, 35, 5);

            intakeMotor.setPower(0.3);
            resetRuntime();
            while (3 >= getRuntime() && opModeIsActive()){
                ProportionalFeedbackControl();
            }
            RunToPoint(-37,40 ,5);


        }else{
            if (Zone == 1) {
                RunToPoint(-38,34,5);
                RunToPoint(-24,34,5);
                intakeMotor.setPower(0.3);
                resetRuntime();
                while (0.3 >= getRuntime() && opModeIsActive()){
                    ProportionalFeedbackControl();
                }
                //Remember to turn off

            }}
    }
    public void YellowPixelRed() {
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
        pixelLiftMotor.setTargetPosition(410);

        if (Zone == 3) {
            RunToPoint(36, -46, 2.4, 1);
            RunToPoint(51, -46, 1, 1);


        } else if (Zone == 2) {

            RunToPoint(50.5, -39.5, 1,1);

        }else {
               RunToPoint(36,-30, 2.4, 1);
               RunToPoint(49.5, -30, 1);


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
        RunToPoint(44, -36, 3, 1.2);
        fourBarServo.setPosition(0.92);
        //   RunToPoint();
        stateMachineTimer = getRuntime();
        while (stateMachineTimer > getRuntime()-0.5  && opModeIsActive()){
               ProportionalFeedbackControl();
            keepAtPoint(RobotX,RobotY);
        }
        pixelLiftMotor.setTargetPosition(1);
    }
    public void PlacePurplePixelWithPurplePixelPlacerOnThePixelPickerThatPlacesPerfectly(){
        intakeServo.setPosition(PixelPickerBottom);
        stateMachineTimer = getRuntime();
         while (stateMachineTimer >= getRuntime() - 0.4  && opModeIsActive()){

         }
         intakeMotor.setPower(0.3);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 0.3  && opModeIsActive()){

        }
        intakeMotor.setPower(0);
        intakeServo.setPosition(PixelPickerTop);
        while (stateMachineTimer >= getRuntime() - 1  && opModeIsActive()){

        }
    }
    public void YellowPixelBlue() {
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
        pixelLiftMotor.setTargetPosition(400);

        if (Zone == 1) {
            RunToPoint(44, 49.5, 2.4, 1);
            RunToPoint(48, 49.5, 1, 1);


        } else if (Zone == 2) {

    RunToPoint(47.5, 40, 1,1.5);


        }else {
            //zone 3
            RunToPoint(36,28, 2.4, 1);
            RunToPoint(48, 29.8, 1);


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
        RunToPoint(44, 36, 2, 2);
        fourBarServo.setPosition(0.92);
        //   RunToPoint();
        stateMachineTimer = getRuntime();
        while (stateMachineTimer > getRuntime()-0.5  && opModeIsActive()){
            ProportionalFeedbackControl();
            keepAtPoint(RobotX,RobotY);
        }
        pixelLiftMotor.setTargetPosition(1);
    }
    public void ParkBlue(){

        RunToPoint(46,13,4,2);
        RunToPoint(50,13,1,2);

    }
    public void ParkRed(){

        RunToPoint(46,-10,4,2);
        RunToPoint(60,-10,2,2);

    }
}