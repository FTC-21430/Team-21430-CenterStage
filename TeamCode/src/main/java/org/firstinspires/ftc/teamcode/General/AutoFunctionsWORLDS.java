package org.firstinspires.ftc.teamcode.General;

public abstract class AutoFunctionsWORLDS extends OdometryCode {

    public void PurplePixelRedLeft() {
        if (Zone == 1) {
            Speed = 0.8;

            intakeServo.setPosition(PixelPickerBottom);
            setTurn(0);
            RunToPoint(-53, -46, 1, 5);

            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.5 && opModeIsActive()) {

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-38, -52, 1, 5);
            setTurn(0);
            RunToPoint(-38, -12, 1, 5);
            setTurn(90);
            RunToPoint(-35, -12, 1, 5);
        } else if (Zone == 2) {
            Speed = 0.6;
            RunToPoint(-40, -34, 3, 5);
            setTurn(180);

            RunToPoint(-41, -14.3, 1, 5);
            intakeServo.setPosition(PixelPickerBottom);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.4 && opModeIsActive()) {

            }
            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.6 && opModeIsActive()) {

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-35, -8, 3, 3);

            stateMachineTimer = getRuntime();
            Speed = 0.8;
            setTurn(90);

        } else {
            if (Zone == 3) {
                Speed = 0.7;
                RunToPoint(-38, -62, 3, 3);
                RunToPoint(-52, -53, 3, 2);
                setTurn(-45);
                RunToPoint(-44, -44, 3, 3);
                setTurn(-90);
                intakeServo.setPosition(PixelPickerBottom);
                RunToPoint(-38, -28, 1, 2);
                intakeMotor.setPower(0.7);
                stateMachineTimer = getRuntime();
                while (stateMachineTimer >= getRuntime() - 1.5 && opModeIsActive()) {

                }
                intakeMotor.setPower(0);
                intakeServo.setPosition(PixelPickerTop);
                setTurn(-135);
                RunToPoint(-50, -25, 3, 3);
                setTurn(-270);
                RunToPoint(-40, -10, 3, 3);
            }
        }
    }

    public void PurplePixelBlueRightTruss() {
        if (Zone == 3) {
            Speed = 0.8;
            intakeServo.setPosition(PixelPickerBottom);
            setTurn(-180);
            RunToPoint(-49, 42, 1, 5);
            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.5 && opModeIsActive()) {

            }
            intakeMotor.setPower(0);

            intakeServo.setPosition(PixelPickerTop);

            RunToPoint(-43, 51, 1, 5);

            setTurn(90);
            RunToPoint(-37, 61, 3, 5);
            setTurn(90);
            RunToPoint(11, 61, 3, 5);
        } else if (Zone == 2) {
            Speed = 0.6;
            intakeServo.setPosition(PixelPickerBottom);
            RunToPoint(-47, 50, 1, 5);
            setTurn(-180);
            RunToPoint(-41, 35, 1, 5);
            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.6 && opModeIsActive()) {

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-43, 40, 4, 5);
            setTurn(90);
            RunToPoint(-43, 51, 4, 5);
            RunToPoint(-45, 58.5, 1, 5);
            RunToPoint(11, 58.5, 3, 5);

        } else {
            if (Zone == 1) {
                Speed = 0.7;
                RunToPoint(-38, 62, 3, 3);
                RunToPoint(-52, 52, 3, 2);
                setTurn(-90);
                RunToPoint(-46, 35, 3, 3);
                setTurn(-90);
                intakeServo.setPosition(PixelPickerBottom);
                RunToPoint(-36, 29, 1, 2);
                intakeMotor.setPower(0.7);
                stateMachineTimer = getRuntime();
                while (stateMachineTimer >= getRuntime() - 1.5 && opModeIsActive()) {

                }
                intakeMotor.setPower(0);
                intakeServo.setPosition(PixelPickerTop);

                RunToPoint(-43, 51, 1, 5);

                setTurn(90);
                RunToPoint(-37, 58.2, 4, 5);
                setTurn(90);
                RunToPoint(11, 58.2, 4, 2);
            }
        }
    }

    public void PurplePixelRedLeftTruss() {
        if (Zone == 1) {
            Speed = 0.8;

            intakeServo.setPosition(PixelPickerBottom);
            setTurn(0);
            RunToPoint(-51, -46, 1, 5);

            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.5 && opModeIsActive()) {

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);

            RunToPoint(-43, -51, 1, 5);

            setTurn(90);
            RunToPoint(-37, -61, 3, 5);
            setTurn(90);
            RunToPoint(11, -61, 3, 5);
        } else if (Zone == 2) {
            Speed = 0.6;
            intakeServo.setPosition(PixelPickerBottom);
            RunToPoint(-47, -50, 1, 5);
            setTurn(0);
            Speed = 0.3;
            RunToPoint(-45.4, -33.4, 1, 3);
            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.6 && opModeIsActive()) {

            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-43, -40, 4, 5);
            Speed = 0.6;
            setTurn(90);
            RunToPoint(-43, -51, 4, 5);
            RunToPoint(-45, -58.5, 1, 5);
            RunToPoint(11, -58.5, 3, 5);

        } else {
            if (Zone == 3) {
                Speed = 0.7;
                RunToPoint(-38, -62, 3, 3);
                RunToPoint(-52, -52, 3, 2);
                setTurn(-90);
                RunToPoint(-46, -35, 3, 3);
                setTurn(-90);
                intakeServo.setPosition(PixelPickerBottom);
                RunToPoint(-39, -29, 1, 2);
                intakeMotor.setPower(0.7);
                stateMachineTimer = getRuntime();
                while (stateMachineTimer >= getRuntime() - 1.5 && opModeIsActive()) {

                }
                intakeMotor.setPower(0);
                intakeServo.setPosition(PixelPickerTop);
                RunToPoint(-43, -51, 1, 5);
                setTurn(90);
                RunToPoint(-37, -58.2, 4, 5);
                setTurn(90);
                RunToPoint(11, -58.2, 4, 2);


            }
        }
    }

    public void PurplePixelBlueRight() {
        if (Zone == 1) {
            Speed = 0.7;
            RunToPoint(-38, 62, 3, 3);
            RunToPoint(-52, 48, 3, 2);
            setTurn(-45);
            RunToPoint(-44, 39, 3, 3);
            setTurn(-90);
            intakeServo.setPosition(PixelPickerBottom);
            RunToPoint(-39, 34, 1, 2);
            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 1.5 && opModeIsActive()) {
            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            setTurn(-135);
            RunToPoint(-52, 25, 3, 3);
            setTurn(-270);
            RunToPoint(-42, 8, 3, 3);
        } else if (Zone == 2) {
            Speed = 0.6;
            RunToPoint(-40, 34, 3, 5);
            setTurn(0);
            RunToPoint(-41, 12.3, 1, 5);
            intakeServo.setPosition(PixelPickerBottom);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 0.4 && opModeIsActive()) {
            }
            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 1.5 && opModeIsActive()) {
            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-35, 8, 3, 3);
            stateMachineTimer = getRuntime();
            Speed = 0.8;
            setTurn(90);
            while (stateMachineTimer >= getRuntime() - 1 && opModeIsActive()) {
            }
        } else {
            Speed = 0.8;
            intakeServo.setPosition(PixelPickerBottom);
            setTurn(180);
            RunToPoint(-49.5, 44, 1, 5);
            intakeMotor.setPower(0.7);
            stateMachineTimer = getRuntime();
            while (stateMachineTimer >= getRuntime() - 1 && opModeIsActive()) {
            }
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
            RunToPoint(-39, 52, 1, 5);
            setTurn(180);
            RunToPoint(-39, 12, 1, 5);
            setTurn(90);
            RunToPoint(-35, 12, 1, 5);
        }
    }

    public void YellowPixelRed() {

        stateMachineTimer = getRuntime();
        pixelLiftMotor.setTargetPosition(safeLiftHeight);
        while (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight && opModeIsActive()) {
            keepAtPoint(RobotX, RobotY);
            ProportionalFeedbackControlAuto();
        }

        fourBarServo.setPosition(0.015);
        stateMachineTimer = getRuntime();
        pixelLiftMotor.setTargetPosition(410);

        if (Zone == 3) {
            RunToPoint(51, -45, 2, 2);

        } else if (Zone == 2) {
            RunToPoint(50.5, -39.5, 1, 1);

        } else {
            pixelLiftMotor.setTargetPosition(460);
            RunToPoint(50, -30, 2, 1.4);
        }
        backDepositorServo.setPosition(1);
        frontDepositorServo.setPosition(0);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer > getRuntime() - 1 && opModeIsActive()) {
            keepAtPoint(RobotX, RobotY);
        }
        backDepositorServo.setPosition(0.5);
        frontDepositorServo.setPosition(0.5);
        RunToPoint(44, -36, 3, 1.2);
        fourBarServo.setPosition(0.92);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer > getRuntime() - 0.5 && opModeIsActive()) {
            ProportionalFeedbackControl();
            keepAtPoint(RobotX, RobotY);
        }
        pixelLiftMotor.setTargetPosition(1);
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

    public void YellowPixelBlue() {

        stateMachineTimer = getRuntime();
        pixelLiftMotor.setTargetPosition(safeLiftHeight);
        while (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight && opModeIsActive()) {
            keepAtPoint(RobotX, RobotY);
            ProportionalFeedbackControlAuto();
        }

        fourBarServo.setPosition(0.015);
        stateMachineTimer = getRuntime();
        Speed = 0.5;
        pixelLiftMotor.setTargetPosition(520);

        if (Zone == 1) {
            RunToPoint(51, 41.5, 1, 1);
        } else if (Zone == 2) {

            RunToPoint(50, 32, 1, 1.5);
        } else {
            RunToPoint(50, 25.5, 2, 2);
        }
        backDepositorServo.setPosition(1);
        frontDepositorServo.setPosition(0);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer > getRuntime() - 1 && opModeIsActive()) {
            keepAtPoint(RobotX, RobotY);
        }
        backDepositorServo.setPosition(0.5);
        frontDepositorServo.setPosition(0.5);
        RunToPoint(44, 36, 2, 2);
        fourBarServo.setPosition(0.92);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer > getRuntime() - 0.5 && opModeIsActive()) {
            ProportionalFeedbackControl();
            keepAtPoint(RobotX, RobotY);
        }
        pixelLiftMotor.setTargetPosition(1);
    }

    public void ParkBlue() {

        RunToPoint(46, 13, 4, 2);
        RunToPoint(50, 13, 1, 2);
    }

    public void ParkRed() {

        RunToPoint(46, -10, 4, 2);
        RunToPoint(60, -10, 2, 2);
    }
}