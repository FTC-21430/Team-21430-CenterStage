package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.operatorState.depoTransition;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.dockedScoreFinished;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.extendBar;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.extendLift;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.fourBarDock;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.fourBarWait;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.highFourBarExtend;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.idle;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.intakeCancel;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.intakeDone;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.intakeManaul;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.intaking;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.liftDock;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.liftOut;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.score;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.scoreDocked;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.scoreFinished;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.scoreIdle;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.transferDocked;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public abstract class GeneralCode extends Robot {
    boolean ClimbAutoUp = false;
    int LiftManual = 0;
    boolean calabrate_Lift = false;
    boolean firstLoop = false;
    int LiftMAX = 1400;
    boolean leftTurnold = false;
    boolean rightTurnold = false;
    double intakeHeight = 0;
    boolean endGameMode = false;
    boolean pixelPickerUp = false;
    int pixelPickerCurrent = 6;
    boolean rightBumperOLD = false;
    boolean hasDroneLaunched = false;
    double stateMachineTimer;
    boolean oldEndGameMode = false;
    double LiftAdd;
    boolean slowMode;
    boolean Intake;
    boolean highJunction;
    boolean mediumJunction;
    boolean lowJunction;
    boolean groundJunction;
    float fastMode;
    boolean upStack;
    boolean TurnLeft;
    boolean TurnRight;
    boolean bumper_old;
    float IntakeInput;
    int safeLiftHeight = 450;
    boolean barHeightHigh = false;
    float communicationMode;
double liftTimeOut;

    double PixelPickerBottom = 0.278;
    double PixelPickerTop = 0.8;
public void pixelLiftRunToPosition(int encoderTick){

    pixelLiftMotor.setTargetPosition(encoderTick);
    if (liftTimeOut <= getRuntime()-5){
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
        highJunction = gamepad2.dpad_up;
        mediumJunction = gamepad2.dpad_right;
        lowJunction = gamepad2.dpad_left;
        groundJunction = gamepad2.dpad_down;
        //toggle Driver Orientation Mode
        communicationMode = gamepad2.right_trigger;
    }
    public void GridRunner() {
    // TODO: make it easy to not break the robot. :(
       // if (gamepad1.a) Target = scoringAngle;
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
        turn *=2;
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
        IntakeInput = gamepad2.left_stick_y/ 1;
    }

    public void Climber() {

//        if (hasDroneLaunched){
        telemetry.addData("encoderCount:", climberMotor.getCurrentPosition());
//
// && climberMotor.getCurrentPosition() >= -4630
//        ClimberLimitSwitchBottom.getState() == false && climberMotor.getCurrentPosition()<=100
        if (gamepad2.dpad_right && climberMotor.getCurrentPosition() > -18700) ClimbAutoUp = true;
        if (gamepad2.dpad_left || climberMotor.getCurrentPosition() <= -18690) ClimbAutoUp = false;
        if (ClimbAutoUp) {
                climberMotor.setPower(-1);
            }
        if (!ClimbAutoUp && gamepad2.dpad_right) {
            climberMotor.setPower(-0.1);
        }
        if (gamepad2.dpad_left && !ClimberLimitSwitchBottom.getState()){
            climberMotor.setPower(1);
        }
        if (!gamepad2.dpad_left && !gamepad2.dpad_right && !ClimbAutoUp) {
            climberMotor.setPower(0);
        }
            telemetry.addData("climber power", climberMotor.getPower());
        }


//    }
public void LaunchDrone(){
        hasDroneLaunched = true;
        droneTrigger.setPosition(0.67);
    // line 170, this is a temporary number. TUNE WHEN YOU CAN TEST;
}

    public void endGameThings(){
        if (gamepad2.back == true && oldEndGameMode == false){
            if (!endGameMode){
                endGameMode = true;
            } else{
                endGameMode = false;
            }
        }
        oldEndGameMode = gamepad2.back;
        if (gamepad1.left_bumper) {
            DroneLinkageServo.setPosition(0.75);
        }else{
            DroneLinkageServo.setPosition(0.9);
        }

        if (gamepad1.left_trigger >= 0.5 && gamepad1.left_bumper) LaunchDrone();
        if (endGameMode){
            Climber();
        }
    }

    public void updateCommunication(){
        if (currentState == scoreIdle || currentState == score || currentState == liftOut && ColorSensorCheck(backColorSensor) != "None"){
            if (ColorSensorCheck(backColorSensor) == "White"){
            pattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
            }
            if (ColorSensorCheck(backColorSensor) == "Yellow"){
                pattern = RevBlinkinLedDriver.BlinkinPattern.YELLOW;
            }
            if (ColorSensorCheck(backColorSensor) == "Green"){
            pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
            }
            if (ColorSensorCheck(backColorSensor) == "Purple"){
                pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET;
            }
        }
        if (endGameMode) pattern = RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE;

        if (communicationMode >= 0.2){
            if (gamepad2.a){

            pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
            }
            if (gamepad2.x){

                pattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
            }
            if (gamepad2.y){

                pattern = RevBlinkinLedDriver.BlinkinPattern.YELLOW;
            }
            if (gamepad2.b){
                
                pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET;
            }
        }}

    public void liftPowerControl(){
        if (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight){
            pixelLiftMotor.setPower(0.4);
        }else{
            pixelLiftMotor.setPower(0.95);
        }
    }

    public void stateControl() {
switch (currentState){
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
}}



    public void scoreDocked(){
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(0);

        if (!gamepad2.b) {
            frontDepositorServo.setPosition(0.5);
            backDepositorServo.setPosition(0.5);
            currentState = idle;
        }

    }

    public void dockedScoreFinished(){
        frontDepositorServo.setPosition(0.5);
        if (ColorSensorCheck(backColorSensor) != "None"){
            currentState = transferDocked;
        }else{

            currentState = idle;
            stateMachineTimer = getRuntime();
        }
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }
    }

    public void transferDocked(){
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(-1);
        if (ColorSensorCheck(frontColorSensor) != "None"){
            currentState = idle;
            frontDepositorServo.setPosition(0.5);
            backDepositorServo.setPosition(0.5);
        }
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }
    }


public void idleCode(){
    if (endGameMode) {
        intakeServo.setPosition(1);
    }
        if (gamepad2.b) currentState = scoreDocked;
        backDepositorServo.setPosition(0.5);
    pattern = RevBlinkinLedDriver.BlinkinPattern.CP1_2_COLOR_WAVES;
        if (gamepad2.left_bumper) {
            currentState = intaking;
            pixelPickerCurrent = 6;
            pixelPickerUp = true;

            backDepositorServo.setPosition(-1);
        }
      //  if (gamepad2.right_bumper) currentState = intakeManaul;
        if (gamepad2.dpad_up) {
            currentState = extendLift;
            stateMachineTimer = getRuntime();
        }
    if (gamepad2.x) {
        transferMotor.setPower(-1);
        intakeMotor.setPower(1);
    }
    }
    public void intake(){
    telemetry.addData("rightBumperOLD",rightBumperOLD);
    telemetry.addData("pickerUp",pixelPickerUp);
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
            if (pixelPickerCurrent == 6){
                intakeServo.setPosition(0.8);
            }else if (pixelPickerCurrent == 5){
                intakeServo.setPosition(0.371);
            }else if (pixelPickerCurrent == 4){
                intakeServo.setPosition(0.346);
            }else if (pixelPickerCurrent == 3){
                intakeServo.setPosition(0.327);
            }else if(pixelPickerCurrent == 2) {
                intakeServo.setPosition(0.30);
            }else if(pixelPickerCurrent == 1){
                intakeServo.setPosition(0.278);
            }

            telemetry.addData("current pixel picker",pixelPickerCurrent);
        if (pixelPickerCurrent == 6 ||  pixelPickerCurrent == 2 || pixelPickerCurrent ==3 ){
            intakeMotor.setPower(-0.7);
        }else if (pixelPickerCurrent == 5 || pixelPickerCurrent == 4){
            intakeMotor.setPower(-0.8);
        }else if(pixelPickerCurrent == 1){
            intakeMotor.setPower(-0.9);
        }
        rightBumperOLD = gamepad2.right_bumper;
        transferMotor.setPower(1);

        pattern = RevBlinkinLedDriver.BlinkinPattern.CP2_LARSON_SCANNER;
        if (!gamepad2.left_bumper) {
            currentState = intakeCancel;
            stateMachineTimer = getRuntime();
        }
        if (ColorSensorCheck(frontColorSensor) == "None"){
            frontDepositorServo.setPosition(1);
        }
        if (ColorSensorCheck(frontColorSensor) != "None"){
            frontDepositorServo.setPosition(.5);
        }
    if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None"){
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

    public void intakeCancel(){

    intakeServo.setPosition(1);
        frontDepositorServo.setPosition(0.5);
        backDepositorServo.setPosition(0.5);
        pattern = RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE;
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
    public void intakeManual(){
        pattern = RevBlinkinLedDriver.BlinkinPattern.CP2_LARSON_SCANNER;
        if (gamepad2.left_trigger >= 0.3){ intakeMotor.setPower(0.6);} else intakeMotor.setPower(1);
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

        if(gamepad2.a) currentState = idle;
        if (gamepad2.x) {
            transferMotor.setPower(-1);
            intakeMotor.setPower(1);
        }

        if (endGameMode){
            currentState = idle;
        }else{
            if (gamepad2.dpad_up || gamepad2.dpad_down || gamepad2.dpad_left || gamepad2.dpad_right){
                currentState = extendLift;
                stateMachineTimer = getRuntime();
            }
        }
    }
    public void extendLift(){

        pixelLiftMotor.setTargetPosition(safeLiftHeight);

        if (pixelLiftMotor.getCurrentPosition() >= safeLiftHeight  || stateMachineTimer <= getRuntime() - 3){
            // We made it an or statement just in case the robot doesn't reach the exact safe lift height then will be okay :)
                currentState = highFourBarExtend;
        }
    }
    public void extendFourBarHigh(){
        fourBarServo.setPosition(0.015);
        liftPosition = safeLiftHeight;
        currentState = liftOut;
    }
    public void liftOut(){
        frontDepositorServo.setPosition(0.5);
        backDepositorServo.setPosition(0.5);
        liftPosition += gamepad2.left_stick_y * -50;
        if (liftPosition >= LiftMAX) liftPosition = LiftMAX;
        if (liftPosition <= safeLiftHeight) liftPosition = safeLiftHeight;
        telemetry.addData("liftPosition", liftPosition);
        pixelLiftMotor.setTargetPosition(liftPosition);

        if(gamepad2.b){
            firstLoop = true;
            currentState = score;
        }
        if(gamepad2.dpad_down) {
            currentState = fourBarWait;
            fourBarServo.setPosition(0.96);
        pixelLiftMotor.setTargetPosition(safeLiftHeight+200);
            stateMachineTimer = getRuntime();
        }
    }
    public void score(){
        frontDepositorServo.setPosition(0);
        backDepositorServo.setPosition(1);

    if (!gamepad2.b) {
        frontDepositorServo.setPosition(0.5);
        backDepositorServo.setPosition(0.5);
        currentState = liftOut;
    }

    }
    public void scoreFinished(){
        frontDepositorServo.setPosition(0.5);
        backDepositorServo.setPosition(0.5);
        if (ColorSensorCheck(backColorSensor) != "None"){
            currentState = depoTransition;
        }
        if (gamepad2.dpad_down){
            fourBarServo.setPosition(0.92);
            currentState = fourBarWait;
            stateMachineTimer = getRuntime();
        }
    }
    public void depositorTransition(){
        if (firstLoop){
                frontDepositorServo.setPosition(0);
                stateMachineTimer = runtime.seconds();
            firstLoop = false;
        }else{

            if (stateMachineTimer <= runtime.seconds() - 0.5){
                currentState = liftOut;
            }
        }

    }
    public void fourBarWait(){
    // TODO Figure out best time for the Four Bar Wait
        if (stateMachineTimer <= getRuntime() - 0.5) currentState = fourBarDock;
    }
    public void fourBarDock(){
        fourBarServo.setPosition(0.92);
        if (gamepad2.dpad_down){
            currentState = liftDock;
            stateMachineTimer = getRuntime();
            liftTimeOut = getRuntime();
        }
    }
    public void liftDock(){
        pattern = RevBlinkinLedDriver.BlinkinPattern.CP1_2_COLOR_WAVES;
        pixelLiftRunToPosition(0);
        if (pixelLiftMotor.getCurrentPosition() <= 5) currentState = idle;
    }
}

