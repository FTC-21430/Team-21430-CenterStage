package org.firstinspires.ftc.teamcode.General;

import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.depoTransition;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.extendLift;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.fourBarDock;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.fourBarWait;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.highFourBarExtend;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.idle;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.intakeCancel;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.intakeDone;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.intaking;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.liftCalibrate;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.liftDock;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.liftOut;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.score;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.scoreDocked;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.scoreIdle;
import static org.firstinspires.ftc.teamcode.General.Robot.operatorState.transferDocked;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
public abstract class GeneralCode extends Robot {

    public static double DroneLaunchPosition = 0.784;

    private boolean ClimberBarDepoyed = false;
    public boolean IntakeOLD = false;
    private boolean frontDepoReverse = false;
    public boolean ClimbAutoUp = false;
    public int LiftManual = 0;
    public boolean calabrate_Lift = false;
    boolean firstLoop = false;
    public int LiftMAX = 1400;
    public boolean leftTurnold = false;
    public boolean rightTurnold = false;
    public double intakeHeight = 0;
    public boolean endGameMode = false;
    public boolean pixelPickerUp = false;
    public int pixelPickerCurrent = 6;
    public boolean rightBumperOLD = false;
    public boolean hasDroneLaunched = false;
    public double stateMachineTimer;
    public boolean oldEndGameMode = false;
    public double LiftAdd;
    public boolean slowMode;
    public boolean Intake;
    public boolean highJunction;
    public boolean mediumJunction;
    public boolean lowJunction;
    public boolean groundJunction;
    public float fastMode;
    public boolean upStack;
    public boolean TurnLeft;
    public boolean TurnRight;
    public boolean bumper_old;
    public float IntakeInput;
    public int safeLiftHeight = 450;
    public boolean barHeightHigh = false;
    public float communicationMode;
    public double liftTimeOut;
    public static double PixelPickerServoPosition = 0.8;
    int pixelLiftHeightLevel = 1;
    int HeightOne = 450;
    int HeightTwo = 700;
    int HeightThree = 900;
    int HeightFour = 1100;
    int HeightFive = 1350;

    public double PixelPickerBottom = 0.53;
    public double PixelPickerTop = 0.8;

    public void pixelLiftRunToPosition(int encoderTick) {

        pixelLiftMotor.setTargetPosition(encoderTick);
        if (liftTimeOut <= getRuntime() - 5) {
            pixelLiftMotor.setTargetPosition(pixelLiftMotor.getCurrentPosition());
            currentState = idle;
        }
    }

    public void UpdateControls() {
        drive = -gamepad1.left_stick_y;
        slide = gamepad1.left_stick_x;
        turn = gamepad1.right_stick_x;
        slowMode = gamepad1.right_bumper;
        fastMode = gamepad1.right_trigger;

        Intake = gamepad2.b;
        communicationMode = gamepad2.right_trigger;
    }

    public void GridRunner() {
        // TODO: make it easy to not break the robot. :(
        if (gamepad1.dpad_up) {
            drive = 1;
            slide = 0;
        }
        if (gamepad1.dpad_left) {
            drive = 0;
            slide = -1;
        }
        if (gamepad1.dpad_right) {
            drive = 0;
            slide = 1;
        }
        if (gamepad1.dpad_down) {
            drive = -1;
            slide = 0;
        }
    }

    public void speedControl() {
        drive /= 2;
        slide /= 2;
        turn /= 2;
        if (fastMode == 1) {
            drive *= 2;
            slide *= 2;
            turn *= 2;
        }
        if (slowMode) {
            drive /= 2;
            slide /= 2;
            turn *= 0.8;

        }
    }

    public void setMotorPower() {
        // Send calculated power to wheels
        leftFrontMotor.setPower(leftFrontPower);
        leftBackMotor.setPower(leftBackPower);
        rightFrontMotor.setPower(rightFrontPower);
        rightBackMotor.setPower(rightBackPower);
        //Set the servo to the new position and pause;
    }

    public void CenterStageUpdateControls() {
        drive = -gamepad1.left_stick_y;
        slide = gamepad1.left_stick_x;
        turn = gamepad1.right_stick_x;
        slowMode = gamepad1.right_bumper;
        fastMode = gamepad1.right_trigger;
        IntakeInput = gamepad2.left_stick_y / 1;
    }

    public void Climber() {
        if (gamepad2.right_bumper && currentState != intaking) ClimberBarDepoyed = true;
      if (gamepad2.right_trigger >= 0.1 && currentState != intaking){
          ClimberBarServo.setPosition(ClimberBarOutPosition);
          ClimberBarDepoyed = true;
      }
      else if (ClimberBarDepoyed){
          ClimberBarServo.setPosition(ClimberBarMidPosition);
      }
      if (gamepad2.right_bumper) ClimberBarDepoyed = true;
      if (gamepad2.right_stick_y >= 0.3) {climberMotor.setPower(-1);}
      else
      if (gamepad2.right_stick_y <= -0.3){ climberMotor.setPower(1);}
      else{
          climberMotor.setPower(0);
      }

    }

    public void LaunchDrone() {
        hasDroneLaunched = true;
        droneTrigger.setPosition(0.67);
    }
    public void endGameThings() {
        if (endGameMode) Climber();
        if (gamepad2.back == true && oldEndGameMode == false) {
            if (!endGameMode) {
                endGameMode = true;
            } else {
                endGameMode = false;
            }
        }
        oldEndGameMode = gamepad2.back;
        if (gamepad1.left_bumper) {
            DroneLinkageServo.setPosition(DroneLaunchPosition);
        } else {
            DroneLinkageServo.setPosition(0.9);
        }

        if (gamepad1.left_trigger >= 0.5 && gamepad1.left_bumper) LaunchDrone();

    }

    public void updateCommunication() {
        if (currentState == scoreIdle || currentState == score || currentState == liftOut && ColorSensorCheck(backColorSensor) != "None") {
            if (ColorSensorCheck(backColorSensor) == "White") {
                pattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
            }
            if (ColorSensorCheck(backColorSensor) == "Yellow") {
                pattern = RevBlinkinLedDriver.BlinkinPattern.YELLOW;
            }
            if (ColorSensorCheck(backColorSensor) == "Green") {
                pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
            }
            if (ColorSensorCheck(backColorSensor) == "Purple") {
                pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET;
            }
        }
        if (endGameMode) pattern = RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE;

        if (communicationMode >= 0.2) {
            if (gamepad2.a) {

                pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
            }
            if (gamepad2.x) {

                pattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
            }
            if (gamepad2.y) {

                pattern = RevBlinkinLedDriver.BlinkinPattern.YELLOW;
            }
            if (gamepad2.b) {

                pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET;
            }
        }
    }

    public void liftPowerControl() {
        if (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight) {
            pixelLiftMotor.setPower(0.4);
        } else {
            pixelLiftMotor.setPower(0.95);
        }
    }

    public void stateControl() {
        switch (currentState) {
            case idle:
                idleCode();
                break;
            case intaking:
                intake();
                break;
            case intakeManaul:
                intakeManual();
                break;
            case intakeDone:
                intakeDone();
                break;
            case intakeCancel:
                intakeCancel();
                break;
            case scoreIdle:
                scoreIdle();
                break;
            case extendLift:
                extendLift();
                break;
            case liftOut:
                liftOut();
                break;
            case score:
                score();
                break;
            case scoreFinished:
                scoreFinished();
                break;
            case depoTransition:
                depositorTransition();
                break;
            case fourBarWait:
                fourBarWait();
                break;
            case fourBarDock:
                fourBarDock();
                break;
            case liftDock:
                liftDock();
                break;
            case liftCalibrate:
                liftCalibrate();
                break;
            case scoreDocked:
                scoreDocked();
                break;
            case transferDocked:
                transferDocked();
                break;
            case dockedScoreFinished:
                dockedScoreFinished();
                break;

            case highFourBarExtend:
                extendFourBarHigh();
                break;
        }
    }


    public void scoreDocked() {
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(1);

        if (!gamepad2.b) {
            frontDepositorServo.setPosition(0.5);
            backDepositorServo.setPosition(0.5);
            currentState = idle;
        }

    }

    public void dockedScoreFinished() {
        frontDepositorServo.setPosition(0.5);
        frontDepositorServo.setPosition(0.5);
        if (ColorSensorCheck(backColorSensor) != "None") {
            currentState = transferDocked;
        } else {

            currentState = idle;
            stateMachineTimer = getRuntime();
        }
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }
    }

    public void transferDocked() {
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(1);
        if (ColorSensorCheck(frontColorSensor) != "None") {
            currentState = idle;
            frontDepositorServo.setPosition(0.5);
            backDepositorServo.setPosition(0.5);
        }
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }
    }

    public void idleCode() {

        if (gamepad2.b) currentState = scoreDocked;
        backDepositorServo.setPosition(0.5);
        pattern = RevBlinkinLedDriver.BlinkinPattern.CP1_2_COLOR_WAVES;
        if (gamepad2.left_bumper) {
            currentState = intaking;
            pixelPickerCurrent = 6;
            pixelPickerUp = true;
            if (ColorSensorCheck(frontColorSensor) == "None") {
                frontDepositorServo.setPosition(0.8);
            }
            frontDepoReverse = false;
            backDepositorServo.setPosition(0.8);
        }
        if (gamepad2.dpad_up) {
            currentState = extendLift;
            stateMachineTimer = getRuntime();
        }
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }
    }

    public void intake() {
        if (gamepad2.right_trigger >= 0.5) pixelPickerCurrent = 6;
        if (gamepad2.left_trigger >= 0.5) pixelPickerCurrent = 1;
        if (gamepad2.right_bumper && !rightBumperOLD) {
            if (pixelPickerUp == true) {
                if (pixelPickerCurrent == 6) {
                    pixelPickerCurrent = 5;
                } else if (pixelPickerCurrent == 5) {
                    pixelPickerCurrent = 4;
                } else if (pixelPickerCurrent == 4) {
                    pixelPickerCurrent = 3;
                } else if (pixelPickerCurrent == 3) {
                    pixelPickerCurrent = 2;
                } else if (pixelPickerCurrent == 2) {
                    pixelPickerCurrent = 1;
                } else if (pixelPickerCurrent == 1) {
                    pixelPickerUp = false;
                    pixelPickerCurrent = 2;
                }

            } else {
                if (pixelPickerCurrent == 6) {
                    pixelPickerCurrent = 5;
                    pixelPickerUp = true;
                } else if (pixelPickerCurrent == 5) {
                    pixelPickerCurrent = 6;

                } else if (pixelPickerCurrent == 4) {
                    pixelPickerCurrent = 5;
                } else if (pixelPickerCurrent == 3) {
                    pixelPickerCurrent = 4;
                } else if (pixelPickerCurrent == 2) {
                    pixelPickerCurrent = 3;
                } else if (pixelPickerCurrent == 1) {
                    pixelPickerCurrent = 2;
                }
            }
        }

        if (pixelPickerCurrent == 6) {
            intakeServo.setPosition(0.8);
        } else if (pixelPickerCurrent == 5) {
            intakeServo.setPosition(0.604);
        } else if (pixelPickerCurrent == 4) {
            intakeServo.setPosition(0.583);
        } else if (pixelPickerCurrent == 3) {
            intakeServo.setPosition(0.565);
        } else if (pixelPickerCurrent == 2) {
            intakeServo.setPosition(0.54);
        } else if (pixelPickerCurrent == 1) {
            intakeServo.setPosition(0.53);
        }


        if (pixelPickerCurrent == 6 || pixelPickerCurrent == 2 || pixelPickerCurrent == 3) {
            intakeMotor.setPower(-0.7);
        } else if (pixelPickerCurrent == 5 || pixelPickerCurrent == 4) {
            intakeMotor.setPower(-0.8);
        } else if (pixelPickerCurrent == 1) {
            intakeMotor.setPower(-0.9);
        }
        rightBumperOLD = gamepad2.right_bumper;
        transferMotor.setPower(1);

        pattern = RevBlinkinLedDriver.BlinkinPattern.CP2_LARSON_SCANNER;
        if (!gamepad2.left_bumper) {
            currentState = intakeCancel;
            stateMachineTimer = getRuntime();
        }

        if (ColorSensorCheck(frontColorSensor) != "None") {
            frontDepositorServo.setPosition(.5);
            pattern = RevBlinkinLedDriver.BlinkinPattern.ORANGE;
        }


        if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None") {
            currentState = intakeDone;
            stateMachineTimer = getRuntime();
            frontDepositorServo.setPosition(.5);
            backDepositorServo.setPosition(.5);
        }
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }
    }

    public void intakeCancel() {

        intakeServo.setPosition(1);
        frontDepositorServo.setPosition(0.5);
        backDepositorServo.setPosition(0.5);

        intakeMotor.setPower(0.9);

        transferMotor.setPower(-1);
        if (stateMachineTimer <= getRuntime() - 0) {
            // We set it to 0 because it was taking to much time and if we delete it we encounter bugs
            currentState = idle;
            intakeMotor.setPower(0);
            transferMotor.setPower(0);
        }
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }
    }

    public void intakeManual() {
        pattern = RevBlinkinLedDriver.BlinkinPattern.CP2_LARSON_SCANNER;
        if (gamepad2.left_trigger >= 0.3) {
            intakeMotor.setPower(0.6);
        } else intakeMotor.setPower(1);
        transferMotor.setPower(1);

        if (gamepad2.a) {
            currentState = intakeCancel;
            stateMachineTimer = getRuntime();
        }
        if (gamepad2.x) currentState = intakeDone;
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }
    }

    public void intakeDone() {

        intakeServo.setPosition(1);
        pattern = RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE;
        intakeMotor.setPower(-0.8);

        transferMotor.setPower(-1);
        if (stateMachineTimer <= getRuntime() - 0) {
            //We set it to 0 because it was taking to much time and if we delete it we encounter bugs, edit: encountered bugs
            currentState = scoreIdle;
            intakeMotor.setPower(0);
            transferMotor.setPower(0);
        }
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }
    }

    public void scoreIdle() {
        if (gamepad2.b) currentState = scoreDocked;
        if (gamepad2.left_bumper && !IntakeOLD) {
            currentState = intaking;
            pixelPickerCurrent = 6;
            pixelPickerUp = true;

            backDepositorServo.setPosition(1);
        }
        if (gamepad2.a) currentState = idle;
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }

        if (endGameMode) {
            currentState = idle;
        } else {
            if (gamepad2.dpad_up || gamepad2.dpad_down || gamepad2.dpad_left || gamepad2.dpad_right) {
                currentState = extendLift;
                stateMachineTimer = getRuntime();
            }
        }
    }

    public void extendLift() {

        if (pixelLiftHeightLevel == 1){
            pixelLiftMotor.setTargetPosition(HeightOne);
            liftPosition = HeightOne;
        }else
        if (pixelLiftHeightLevel == 2){
            pixelLiftMotor.setTargetPosition(HeightTwo);
            liftPosition = HeightTwo;
        }else
        if (pixelLiftHeightLevel == 3){
            pixelLiftMotor.setTargetPosition(HeightThree);
            liftPosition = HeightThree;
        }else
        if (pixelLiftHeightLevel == 4){
            pixelLiftMotor.setTargetPosition(HeightFour);
            liftPosition = HeightFour;
        }else
        if (pixelLiftHeightLevel == 5) {
            pixelLiftMotor.setTargetPosition(HeightFive);
            liftPosition = HeightFive;
        }else{
            pixelLiftMotor.setTargetPosition(HeightOne);
            liftPosition = HeightOne;
        }

        if (pixelLiftMotor.getCurrentPosition() >= safeLiftHeight || stateMachineTimer <= getRuntime() - 3) {
            // We made it an or statement just in case the robot doesn't reach the exact safe lift height then will be okay :)
            currentState = highFourBarExtend;
        }
    }

    public void extendFourBarHigh() {
        fourBarServo.setPosition(0.015);
        liftPosition = safeLiftHeight;
        currentState = liftOut;
    }

    public void liftOut() {
        frontDepositorServo.setPosition(0.5);
        backDepositorServo.setPosition(0.5);
        int liftMovementSpeed = -80;
        liftPosition += gamepad2.left_stick_y * liftMovementSpeed;
        if (liftPosition >= LiftMAX) liftPosition = LiftMAX;
        if (liftPosition <= safeLiftHeight) liftPosition = safeLiftHeight;

        updateSavedLiftHeight(liftPosition);
        pixelLiftMotor.setTargetPosition(liftPosition);

        if (gamepad2.b) {
            firstLoop = true;
            currentState = score;
        }
        if (gamepad2.dpad_down) {
            currentState = fourBarWait;
            fourBarServo.setPosition(0.954);
            pixelLiftMotor.setTargetPosition(safeLiftHeight + 200);
            stateMachineTimer = getRuntime();
        }
    }

    public void score() {
        frontDepositorServo.setPosition(0);
        backDepositorServo.setPosition(0);

        if (!gamepad2.b) {
            frontDepositorServo.setPosition(0.5);
            backDepositorServo.setPosition(0.5);
            currentState = liftOut;
        }
    }

    public void scoreFinished() {
        frontDepositorServo.setPosition(0.5);
        backDepositorServo.setPosition(0.5);
        if (ColorSensorCheck(backColorSensor) != "None") {
            currentState = depoTransition;
        }
        if (gamepad2.dpad_down) {
            fourBarServo.setPosition(0.954);
            currentState = fourBarWait;
            stateMachineTimer = getRuntime();
        }
    }

    public void depositorTransition() {
        if (firstLoop) {
            frontDepositorServo.setPosition(0);
            stateMachineTimer = runtime.seconds();
            firstLoop = false;
        } else {

            if (stateMachineTimer <= runtime.seconds() - 0.5) {
                currentState = liftOut;
            }
        }
    }

    public void fourBarWait() {
        // TODO Figure out best time for the four bar wait
        if (stateMachineTimer <= getRuntime() - 0.5) currentState = fourBarDock;
    }

    public void fourBarDock() {
        fourBarServo.setPosition(0.954);
        if (gamepad2.dpad_down) {
            currentState = liftDock;
            stateMachineTimer = getRuntime();
            liftTimeOut = getRuntime();
        }
    }

    public void liftDock() {
        pattern = RevBlinkinLedDriver.BlinkinPattern.CP1_2_COLOR_WAVES;
        pixelLiftRunToPosition(30);


        if (pixelLiftMotor.getCurrentPosition() <= 35) {
            currentState = liftCalibrate;
            pixelLiftMotor.setPower(0.03);
            pixelLiftMotor.setTargetPosition(-50);
        }
    }
    public void liftCalibrate(){
        telemetry.addData("current pixel lift", pixelLiftMotor.getCurrentPosition());
        telemetry.addData("target pixel lift", pixelLiftMotor.getTargetPosition());
        telemetry.addData("limit switch", PixelLiftLimitSwitch.getState());
        if (PixelLiftLimitSwitch.getState() == true|| getRuntime() - liftTimeOut >= 2 || pixelLiftMotor.getCurrentPosition() <= -51) {
            currentState = idle;
            pixelLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            pixelLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            pixelLiftMotor.setTargetPosition(0);
            pixelLiftMotor.setPower(1);
        }
    }
    public void updateSavedLiftHeight(int liftHeight){
        if (liftHeight >= HeightOne && liftHeight < HeightTwo){
            pixelLiftHeightLevel = 1;
        }
        else if (liftHeight >= HeightTwo && liftHeight < HeightThree){
            pixelLiftHeightLevel = 2;
        }
        else if (liftHeight >= HeightThree && liftHeight < HeightFour){
            pixelLiftHeightLevel = 3;
        }
        else if (liftHeight >= HeightFour && liftHeight < HeightFive){
            pixelLiftHeightLevel = 4;
        }
        else if (liftHeight >= HeightFive){
            pixelLiftHeightLevel = 5;
        }
        else{
            pixelLiftHeightLevel = 1;
        }
    }
}