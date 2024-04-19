package org.firstinspires.ftc.teamcode.General;

public abstract class OctopusAutoFunctions extends OdometryCode {

    public void PurplePixelRedLeft() {
        if (Zone == 1) {
            intakeServo.setPosition(PixelPickerBottom);
            setTurn(0);
            RunToPoint(-53, -46, 1, 0.9);

            intakeMotor.setPower(0.5);
            RunToPoint(RobotX,RobotY,-1,.4);
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-38, -52, 3, 5);
            RunToPoint(-38, -12, 1.5, 5);
            setTurn(90);
            RunToPoint(RobotX,RobotY,-1,1.2);

        } else if (Zone == 2) {

            intakeServo.setPosition(PixelPickerBottom);
            RunToPoint(-49,-54,2,6);
            setTurn(-90);
            RunToPoint(-59,-26.3,3,1.2);
            RunToPoint(-54,-23.3,1.4,1.6);
            intakeMotor.setPower(0.5);
            RunToPoint(RobotX,RobotY,-1,.4);
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-59,-14,3,1.8);
            RunToPoint(-54,-12,5,5);
            setTurn(-180);
            RunToPoint(RobotX,RobotY,-1,.3);
            setTurn(90);
            RunToPoint(RobotX,RobotY,-1,1.7);
            RunToPoint(-48,-10,2,2);

        } else {
            if (Zone == 3) {

                setTurn(0);
                RunToPoint(-50,-53,1,1);

                setTurn(-90);
                RunToPoint(-45,-34,1,1.3);
                intakeServo.setPosition(PixelPickerBottom);
                RunToPoint(RobotX,RobotY,-1,.3);
                RunToPoint(-39,-34,1,1.3);
                intakeMotor.setPower(0.3);
                RunToPoint(RobotX,RobotY,-1,.4);
                intakeMotor.setPower(0);
                intakeServo.setPosition(PixelPickerTop);
                RunToPoint(-45,-34,1,2);
                RunToPoint(-45,-10,5,2);
                setTurn(-180);
                RunToPoint(RobotX,RobotY,-1,.3);
                setTurn(90);
                RunToPoint(RobotX,RobotY,-1,1.4);
                RunToPoint(-35,-9,2,0.7);
            }
        }
    }

    public void PurplePixelBlueRightTruss() {
        if (Zone == 3) {

            intakeServo.setPosition(PixelPickerBottom+0.02);
            setTurn(-180);
            RunToPoint(-49, 42, 2, 1.2);
            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.5 && opModeIsActive()) {

            }
            intakeMotor.setPower(0);

            intakeServo.setPosition(PixelPickerTop);

            RunToPoint(-43, 51, 4, 1);

            setTurn(90);
            RunToPoint(-37, 62, 4, 1);
            setTurn(90);
            RunToPoint(11, 62, 4, 2);
        } else if (Zone == 2) {

            intakeServo.setPosition(PixelPickerBottom + 0.02);
            RunToPoint(-47, 50, 3, 1);
            setTurn(-180);
            RunToPoint(-41, 35, 1.6, 2);
            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.6 && opModeIsActive()) {

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-43, 40, 4, 2);
            setTurn(90);
            RunToPoint(-43, 51, 4, 2);
            RunToPoint(-45, 62, 1, 1);
            RunToPoint(11, 62, 3, 2);

        } else {
            if (Zone == 1) {
                RunToPoint(-50,53,1,1);

                setTurn(-90);
                RunToPoint(-45,34,1,1.3);
                intakeServo.setPosition(PixelPickerBottom+0.02);
                RunToPoint(RobotX,RobotY,-1,.3);
                RunToPoint(-38,34,1,1.3);
                intakeMotor.setPower(0.45);
                RunToPoint(RobotX,RobotY,-1,.4);
                intakeMotor.setPower(0);
                intakeServo.setPosition(PixelPickerTop);
                RunToPoint(-45, 58, 1, 1);
                setTurn(90);
                RunToPoint(-37, 62, 4, 2);
                setTurn(90);
                RunToPoint(11, 62, 4, 1.4);
            }
        }
    }

    public void PurplePixelRedLeftTruss() {
        if (Zone == 1) {
            intakeServo.setPosition(PixelPickerBottom+0.02);
            setTurn(0);

            RunToPoint(-53.5, -37, 3, 1.4);
            RunToPoint(-53.5, -43, 1.5, 1);

            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.5 && opModeIsActive()) {

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);

            RunToPoint(-43, -51, 1, 1);

            setTurn(90);
            RunToPoint(-37, -60, 3, 1);
            setTurn(90);
            RunToPoint(11, -60, 2, 1);
        } else if (Zone == 2) {

            intakeServo.setPosition(PixelPickerBottom);
            RunToPoint(-47, -50, 1, 0.7);
            setTurn(0);

            RunToPoint(-45.4, -34.4, 1, 1);
            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            RunToPoint(RobotX,RobotY, -1,0.7);
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-43, -40, 4, 1);

            setTurn(90);
            RunToPoint(-43, -51, 4, 1);
            RunToPoint(-45, -59.5, 1, 1.8);
            RunToPoint(11, -59.5, 3, 1);

        } else {
            if (Zone == 3) {

                setTurn(0);
                RunToPoint(-50,-53,1,1);

                setTurn(-90);
                RunToPoint(-45,-34,1,1.3);
                intakeServo.setPosition(PixelPickerBottom+0.02);
                RunToPoint(RobotX,RobotY,-1,.3);
                RunToPoint(-38,-34,1,1.3);
                intakeMotor.setPower(0.45);
                RunToPoint(RobotX,RobotY,-1,.4);
                intakeMotor.setPower(0);
                intakeServo.setPosition(PixelPickerTop);
                RunToPoint(-45, -58, 1, 1);
                setTurn(90);
                RunToPoint(-37, -60, 4, 5);
                setTurn(90);
                RunToPoint(11, -60.5, 4, 2);


            }
        }
    }

    public void PurplePixelBlueRight() {
        if (Zone == 3) {
            intakeServo.setPosition(PixelPickerBottom);
            setTurn(-180);
            RunToPoint(-47.5, 45, 1, 0.9);

            intakeMotor.setPower(0.6);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.5 && opModeIsActive()) {

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-32, 52, 3, 5);
            RunToPoint(-32, 13, 1.5, 5);
            setTurn(90);
            RunToPoint(RobotX,RobotY,-1,1.2);

        } else if (Zone == 2) {
            intakeServo.setPosition(PixelPickerBottom);
            RunToPoint(-49,54,2,6);
            setTurn(-90);
            RunToPoint(-59,26.3,3,1);
            RunToPoint(-52,23.3,1.5,1.5);
            intakeMotor.setPower(0.5);
            RunToPoint(RobotX,RobotY,-1,.4);
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-59,14,3,1.8);
            RunToPoint(-54,12,5,5);
            setTurn(180);
            RunToPoint(RobotX,RobotY,-1,.3);
            setTurn(90);
            RunToPoint(RobotX,RobotY,-1,1.7);
            RunToPoint(-48,12,2,2);

        } else {
            if (Zone == 1) {
                setTurn(-180);
                RunToPoint(-50,53,1,1);

                setTurn(-90);
                RunToPoint(-45,34,1,1.3);
                intakeServo.setPosition(PixelPickerBottom);
                RunToPoint(RobotX,RobotY,-1,.3);
                RunToPoint(-36.5,34,1,1.3);
                intakeMotor.setPower(0.3);
                RunToPoint(RobotX,RobotY,-1,.4);
                intakeMotor.setPower(0);
                intakeServo.setPosition(PixelPickerTop);
                RunToPoint(-45,34,1,2);
                RunToPoint(-45,10,5,2);
                setTurn(-180);
                RunToPoint(RobotX,RobotY,-1,.3);
                setTurn(90);
                RunToPoint(RobotX,RobotY,-1,1.4);
                RunToPoint(-35,9,2,0.7);
            }
        }
    }

    public void YellowPixelRed(boolean stageSide) {
        if (!stageSide){
        stateMachineTimer = getRuntime();
        pixelLiftMotor.setTargetPosition(safeLiftHeight+90);
        while (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight && opModeIsActive()) {
            keepAtPoint(RobotX, RobotY);
            ProportionalFeedbackControlAuto();
        }

        fourBarServo.setPosition(0.015);
        stateMachineTimer = getRuntime();
        pixelLiftMotor.setTargetPosition(safeLiftHeight+90);
        stateMachineTimer =getRuntime();
        while (stateMachineTimer >= getRuntime()-0.6){
            keepAtPoint(RobotX, RobotY);
            ProportionalFeedbackControlAuto();
        }
        Speed = 0.6;
        if (Zone == 3) {
            RunToPoint(53.5, -46.6, 1, 1.6);

        } else if (Zone == 2) {
            RunToPoint(53, -39, 1, 1.2);

        } else {
//            pixelLiftMotor.setTargetPosition(460);
            RunToPoint(52.5, -31, 1, 1);
        }
        Speed = 1;
        backDepositorServo.setPosition(0);
        frontDepositorServo.setPosition(0);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer > getRuntime() - 1 && opModeIsActive()) {
            keepAtPoint(RobotX, RobotY);
        }
        backDepositorServo.setPosition(0.5);
        frontDepositorServo.setPosition(0.5);
        RunToPoint(35, -36, 3, 1.2);
        fourBarServo.setPosition(0.92);
        stateMachineTimer = getRuntime();
        RunToPoint(RobotX,RobotY,-1,1);
        pixelLiftMotor.setTargetPosition(1);}
        else{


            //for stage side route

            stateMachineTimer = getRuntime();
            pixelLiftMotor.setTargetPosition(safeLiftHeight+5);
            while (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight && opModeIsActive()) {
                keepAtPoint(RobotX, RobotY);
                ProportionalFeedbackControlAuto();
            }

            fourBarServo.setPosition(0.015);
            stateMachineTimer = getRuntime();
            pixelLiftMotor.setTargetPosition(safeLiftHeight+5);
            stateMachineTimer =getRuntime();
            while (stateMachineTimer >= getRuntime()-0.6){
                keepAtPoint(RobotX, RobotY);
                ProportionalFeedbackControlAuto();
            }
            Speed = 0.6;
            if (Zone == 3) {
                RunToPoint(53.5, -46.6, 1, 1.6);

            } else if (Zone == 2) {
                RunToPoint(53, -38, 1, 1.2);

            } else {
//            pixelLiftMotor.setTargetPosition(460);
                RunToPoint(52.5, -28, 1, 1);
            }
            Speed = 1;
            backDepositorServo.setPosition(0);
            frontDepositorServo.setPosition(0);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer > getRuntime() - 1 && opModeIsActive()) {
                keepAtPoint(RobotX, RobotY);
            }
            backDepositorServo.setPosition(0.5);
            frontDepositorServo.setPosition(0.5);
            RunToPoint(35, -36, 3, 1.2);
            fourBarServo.setPosition(0.92);
            stateMachineTimer = getRuntime();
            RunToPoint(RobotX,RobotY,-1,1);
            pixelLiftMotor.setTargetPosition(1);
        }
    }


    public void PlacePurplePixelWithPurplePixelPlacerOnThePixelPickerThatPlacesPerfectly() {
        intakeServo.setPosition(PixelPickerBottom);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 0.4 && opModeIsActive()) {

        }
        intakeMotor.setPower(0.3);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 0.3 && opModeIsActive()) {

        }
        intakeMotor.setPower(0);
        intakeServo.setPosition(PixelPickerTop);
        while (stateMachineTimer >= getRuntime() - 1 && opModeIsActive()) {

        }
    }

    public void YellowPixelBlue(boolean stageSide) {
        if (!stageSide){
            stateMachineTimer = getRuntime();
            pixelLiftMotor.setTargetPosition(safeLiftHeight+50);
            while (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight && opModeIsActive()) {
                keepAtPoint(RobotX, RobotY);
                ProportionalFeedbackControlAuto();
            }

            fourBarServo.setPosition(0.015);
            stateMachineTimer = getRuntime();
            pixelLiftMotor.setTargetPosition(safeLiftHeight+50);
            stateMachineTimer =getRuntime();
            while (stateMachineTimer >= getRuntime()-0.6){
                keepAtPoint(RobotX, RobotY);
                ProportionalFeedbackControlAuto();
            }
            Speed = 1;
            if (Zone == 3) {
                RunToPoint(53.5, 25.5, 1, 1.6);
            } else if (Zone == 2) {
                RunToPoint(53.5, 34.5, 1, 1.2);
            } else {
                RunToPoint(54.2, 41, 1, 1);
            }
            Speed = 1;
            backDepositorServo.setPosition(0);
            frontDepositorServo.setPosition(0);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer > getRuntime() - 1 && opModeIsActive()) {
                keepAtPoint(RobotX, RobotY);
            }
            backDepositorServo.setPosition(0.5);
            frontDepositorServo.setPosition(0.5);
            RunToPoint(35, 36, 3, 1.2);
            fourBarServo.setPosition(0.92);
            stateMachineTimer = getRuntime();
            RunToPoint(RobotX,RobotY,-1,1);
            pixelLiftMotor.setTargetPosition(1);



        }
            else{
            stateMachineTimer = getRuntime();
            pixelLiftMotor.setTargetPosition(safeLiftHeight);
            while (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight && opModeIsActive()) {
                keepAtPoint(RobotX, RobotY);
                ProportionalFeedbackControlAuto();
            }

            fourBarServo.setPosition(0.015);
            stateMachineTimer = getRuntime();
            pixelLiftMotor.setTargetPosition(safeLiftHeight);
            stateMachineTimer =getRuntime();
            while (stateMachineTimer >= getRuntime()-0.6){
                keepAtPoint(RobotX, RobotY);
                ProportionalFeedbackControlAuto();
            }
            Speed = 1;
            if (Zone == 3) {
                RunToPoint(51.5, 25.5, 1, 1.6);
            } else if (Zone == 2) {
                RunToPoint(52, 32.5, 1, 1.2);
            } else {
                RunToPoint(51.2, 39.2, 1, 1);


            }
            Speed = 1;
            backDepositorServo.setPosition(0);
            frontDepositorServo.setPosition(0);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer > getRuntime() - 1 && opModeIsActive()) {
                keepAtPoint(RobotX, RobotY);
            }
            backDepositorServo.setPosition(0.5);
            frontDepositorServo.setPosition(0.5);
            RunToPoint(35, 36, 3, 1.2);
            fourBarServo.setPosition(0.92);
            stateMachineTimer = getRuntime();
            RunToPoint(RobotX,RobotY,-1,1);
            pixelLiftMotor.setTargetPosition(1);

        }



    }
}