package org.firstinspires.ftc.teamcode;

public abstract class AutonomousFunction extends CameraVision {

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
                Target = 0;
                RunToPoint(49, -36);
                intakeMotor.setPower(0);
                RunToPoint(45, -36);
                if (Zone == 1) {
                    RunToPoint(45, -30);
                    liftOut();
                    extendFourBar();
                    score();
                } else if (Zone == 2) {
                    liftOut();
                    extendFourBar();
                    score();
                } else if (Zone == 3) {
                    RunToPoint(45, -42);
                    liftOut();
                    extendFourBar();
                    score();
                }
            }

            public void Park(){
                RunToPoint(49, -61);
                RunToPoint(61, -60);
            }
        }
