package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.operatorState.extendLift;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.liftOut;

public abstract class OctopusAutoFunctions extends OdometryCode {

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
            Speed = 0.8;

            intakeServo.setPosition(PixelPickerBottom);
            setTurn(0);
            RunToPoint(-53,-46,1,5);

            intakeMotor.setPower(0.3);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 1  && opModeIsActive()){

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-38,-52,1,5);
            setTurn(0);
            RunToPoint(-38,-12,1,5);
            setTurn(90);
            RunToPoint(-35,-12,1,5);
        } else if (Zone == 2) {
            Speed = 0.6;
            RunToPoint(-40,-34,3,5);
            setTurn(180);
            // intakeServo.setPosition(PixelPickerBottom);



            RunToPoint(-41,-14.3,1,5);
            intakeServo.setPosition(PixelPickerBottom);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.4  && opModeIsActive()){

            }
            intakeMotor.setPower(0.3);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 1.5  && opModeIsActive()){

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-35, -8, 3, 3);

            stateMachineTimer = getRuntime();
            Speed = 0.8;
            setTurn(90);
            while (stateMachineTimer >= getRuntime() - 1  && opModeIsActive()){

            }
        }else{
            if (Zone == 3) {
                Speed = 0.7;
                RunToPoint(-38,-62,3,3);
                RunToPoint(-52,-53,3,2);
                setTurn(-45);
                RunToPoint(-44,-44,3,3);
                setTurn(-90);
                intakeServo.setPosition(PixelPickerBottom);
                RunToPoint(-38,-28,1,2);


                intakeMotor.setPower(0.3);
                stateMachineTimer = getRuntime();
                while (stateMachineTimer >= getRuntime() - 1.5  && opModeIsActive()){

                }
                intakeMotor.setPower(0);
                intakeServo.setPosition(PixelPickerTop);
                setTurn(-135);
                RunToPoint(-50,-25,3,3);
                setTurn(-270);
                RunToPoint(-40,-10,3,3);



            }
        }
       }
//    public void PurplePixelBlue() {
//        if (Zone == 3) {
//
//            RunToPoint(-46,42,5);
//            intakeMotor.setPower(0.3);
//            resetRuntime();
//            while (3 >= getRuntime() && opModeIsActive()){
//                ProportionalFeedbackControl();
//            }
//            //Remember to turn off
//        } else if (Zone == 2) {
//            RunToPoint(-37, 35, 5);
//
//            intakeMotor.setPower(0.3);
//            resetRuntime();
//            while (3 >= getRuntime() && opModeIsActive()){
//                ProportionalFeedbackControl();
//            }
//            RunToPoint(-37,40 ,5);
//
//
//        }else{
//            if (Zone == 1) {
//                RunToPoint(-38,34,5);
//                RunToPoint(-24,34,5);
//                intakeMotor.setPower(0.3);
//                resetRuntime();
//                while (0.3 >= getRuntime() && opModeIsActive()){
//                    ProportionalFeedbackControl();
//                }
//                //Remember to turn off
//
//            }}
//    }

    public void PurplePixelBlueRight() {
        if (Zone == 1) {
                Speed = 0.7;
                RunToPoint(-38,62,3,3);
                RunToPoint(-52,53,3,2);
                setTurn(-45);
                RunToPoint(-44,44,3,3);
                setTurn(-90);
                intakeServo.setPosition(PixelPickerBottom);
                RunToPoint(-40,23,1,2);
                intakeMotor.setPower(0.3);
                stateMachineTimer = getRuntime();
                while (stateMachineTimer >= getRuntime() - 1.5  && opModeIsActive()){
                }
                intakeMotor.setPower(0);
                intakeServo.setPosition(PixelPickerTop);
                setTurn(-135);
                RunToPoint(-52,25,3,3);
                setTurn(-270);
                RunToPoint(-42,10,3,3);
                //STOP HERE
        } else if (Zone == 2) {
            Speed = 0.6;
            RunToPoint(-40,34,3,5);
            setTurn(0);
            // intakeServo.setPosition(PixelPickerBottom);
            RunToPoint(-41,14.3,1,5);
            intakeServo.setPosition(PixelPickerBottom);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.4  && opModeIsActive()){
            }
            intakeMotor.setPower(0.3);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 1.5  && opModeIsActive()){
            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-35, 8, 3, 3);
            stateMachineTimer = getRuntime();
            Speed = 0.8;
            setTurn(90);
            while (stateMachineTimer >= getRuntime() - 1  && opModeIsActive()){
            }
        }else{
            }
            Speed = 0.8;
            intakeServo.setPosition(PixelPickerBottom);
            setTurn(180);
            RunToPoint(-53,46,1,5);

            intakeMotor.setPower(0.3);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 1  && opModeIsActive()){

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-38,52,1,5);
            setTurn(180);
            RunToPoint(-38,12,1,5);
            setTurn(90);
            RunToPoint(-35,12,1,5);
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