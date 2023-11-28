package org.firstinspires.ftc.teamcode;

public abstract class AutonomousFunction extends CameraVision {

    public void PurplePixel() {
        if (Zone == 1) {
            RunToPoint(-37,-37);
            setTurn(90);
            RunToPoint(-37,-34);
            RunToPoint(-35,-34);
            intakeServo.setPosition(0);
            RunToPoint(-32,-34);
            RunToPoint(-37,-60);
            intakeMotor.setPower(0);
            setTurn(0);
            //Remember to turn off
        } else if (Zone == 2) {
            RunToPoint(-37, -35);

            intakeMotor.setPower(0.6);
            resetRuntime();
            while (3 >= getRuntime()){
                ProportionalFeedbackControl();
            }
            RunToPoint(-37,-40);

        }else{
        if (Zone == 3) {
            RunToPoint(-37, -36);
            RunToPoint(-26, -41);
            intakeMotor.setPower(0.5);
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
