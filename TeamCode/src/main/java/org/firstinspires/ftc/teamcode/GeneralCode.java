package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.operatorState.depoTransition;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.extendBar;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.extendLift;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.fourBarDock;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.fourBarWait;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.idle;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.intakeCancel;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.intakeDone;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.intakeManaul;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.intaking;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.liftDock;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.liftOut;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.score;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.scoreFinished;
import static org.firstinspires.ftc.teamcode.Robot.operatorState.scoreIdle;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class GeneralCode extends Robot {
    int LiftManual = 0;
    boolean calabrate_Lift = false;


    int LiftMAX = 1000;
    int LiftSafe = 30;

    boolean leftTurnold = false;
    boolean rightTurnold = false;

    double intakeHeight = 0;

    boolean endGameMode = false;

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
    int safeLiftHeight = 100;



float communicationMode;
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
        leftFrontPower = leftFrontPower / 2;
        leftBackPower = leftBackPower / 2;
        rightFrontPower = rightFrontPower / 2;
        rightBackPower = rightBackPower / 2;
        if (fastMode == 1) {
            leftFrontPower = leftFrontPower * 2;
            leftBackPower = leftBackPower * 2;
            rightFrontPower = rightFrontPower * 2;
            rightBackPower = rightBackPower * 2;
        }
        if (slowMode) {
            leftFrontPower = leftFrontPower / 2;
            leftBackPower = leftBackPower / 2;
            rightFrontPower = rightFrontPower / 2;
            rightBackPower = rightBackPower / 2;
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
//ClimberLimitSwitchBottom.getState() == false
// && climberMotor.getCurrentPosition()<=100
    public void Climber() {
        if (hasDroneLaunched){
            if (gamepad2.dpad_down) {
                climberMotor.setPower(1);
            } else if (gamepad2.dpad_up) {
                climberMotor.setPower(-0.9);
            } else {
                climberMotor.setPower(0);
            }
        }
        else {
            climberMotor.setPower(0);
        }

    }
public void LaunchDrone(){
        hasDroneLaunched = true;
        droneTrigger.setPosition(0.1);
    // line 131, this is a temporary number. TUNE WHEN YOU CAN TEST;
}

    public void endGameThings(){
        if (gamepad2.back) toggleEndgame();
        oldEndGameMode = endGameMode;
        if (endGameMode && gamepad2.right_trigger >= 0.5) LaunchDrone();
        if (endGameMode){
            Climber();
        }
    }
    public void toggleEndgame(){
        if (gamepad2.back == true && oldEndGameMode == false){
            if (endGameMode){
                endGameMode = false;
            }else{
                endGameMode = true;
            }
        }
    }

    public void updateCommunication(){
        if (currentState == scoreIdle || currentState == score || currentState == liftOut){
            if (ColorSensorCheck(frontColorSensor) == "White"){
            pattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
            }
            if (ColorSensorCheck(frontColorSensor) == "Yellow"){
                pattern = RevBlinkinLedDriver.BlinkinPattern.YELLOW;
            }
            if (ColorSensorCheck(frontColorSensor) == "Green"){
            pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
            }
            if (ColorSensorCheck(frontColorSensor) == "Purple"){
                pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET;
            }
        }

        if (communicationMode >= 0.2){
            if (gamepad2.a){

            pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
            }
            if (gamepad2.b){

                pattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
            }
            if (gamepad2.y){

                pattern = RevBlinkinLedDriver.BlinkinPattern.YELLOW;
            }
            if (gamepad2.x){
                
                pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET;
            }
        }}

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
    case extendBar:
        extendFourBar();
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
}}

public void idleCode(){
    pattern = RevBlinkinLedDriver.BlinkinPattern.CP1_2_COLOR_WAVES;
        if (gamepad2.left_bumper) {
            currentState = intaking;
            frontDepositorServo.setPosition(1);
            backDepositorServo.setPosition(1);
        }
        if (gamepad2.right_bumper) currentState = intakeManaul;
        if (gamepad2.dpad_up) currentState = extendLift;
    }
    public void intake(){
        if (gamepad2.left_trigger >= 0.5){
            intakeMotor.setPower(0.7);
            transferMotor.setPower(0.6);
        }else{
            intakeMotor.setPower(1);
            transferMotor.setPower(1);
        }
        frontDepositorServo.setPosition(1);
        pattern = RevBlinkinLedDriver.BlinkinPattern.CP2_LARSON_SCANNER;
        if (gamepad2.a) {
            currentState = intakeCancel;
            stateMachineTimer = getRuntime();
        }
        if (ColorSensorCheck(frontColorSensor) != "None"){
            frontDepositorServo.setPosition(0);
        }
        if (ColorSensorCheck(backColorSensor) != "None"){
            backDepositorServo.setPosition(0);
        }
    if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None"){
        currentState = intakeDone;
    }
    }
    public void intakeCancel(){
        pattern = RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE;
        intakeMotor.setPower(-0.7);
        transferMotor.setPower(-0.6);
        if (stateMachineTimer <= getRuntime() - 1) {
            currentState = idle;
            intakeMotor.setPower(0);
            transferMotor.setPower(0);
        }

    }
    public void intakeManual(){
        pattern = RevBlinkinLedDriver.BlinkinPattern.CP2_LARSON_SCANNER;
        if (gamepad2.left_trigger >= 0.3){ intakeMotor.setPower(0.6);} else intakeMotor.setPower(1);
        transferMotor.setPower(0.6);

        if (gamepad2.a) {
            currentState = intakeCancel;
            stateMachineTimer = getRuntime();
        }
        if (gamepad2.x) currentState = intakeDone;
        }

    public void intakeDone() {
        pattern = RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE;
        intakeMotor.setPower(-0.7);
        transferMotor.setPower(-0.6);
        if (stateMachineTimer <= getRuntime() - 1) {
            currentState = scoreIdle;
            intakeMotor.setPower(0);
            transferMotor.setPower(0);
        }
    }
    public void scoreIdle() {
        if (gamepad2.dpad_up || gamepad2.dpad_down || gamepad2.dpad_left || gamepad2.dpad_right)
            currentState = extendLift;
    }
    public void extendLift(){
        pixelLiftMotor.setTargetPosition(safeLiftHeight);
        if (pixelLiftMotor.getCurrentPosition() >= safeLiftHeight - 1) currentState = extendBar;
    }
    public void extendFourBar(){
        fourBarServo.setPosition(1);
        if (pixelLiftMotor.getCurrentPosition() >= safeLiftHeight - 1) currentState = liftOut;
    }
    public void liftOut(){
        int liftPosition = pixelLiftMotor.getCurrentPosition();
        liftPosition += gamepad2.left_stick_y * 3;
        if (liftPosition >= LiftMAX) liftPosition = LiftMAX;
        if (liftPosition <= LiftSafe) liftPosition = LiftSafe;
        pixelLiftMotor.setTargetPosition(liftPosition);
        if(gamepad2.b) currentState = score;
        if(gamepad2.a) currentState = fourBarDock;
    }
    public void score(){
        frontDepositorServo.setPosition(1);
        if (ColorSensorCheck(frontColorSensor) == "None"){
            currentState = scoreFinished;
        }
    }
    public void scoreFinished(){
        if (ColorSensorCheck(backColorSensor) != "None"){
            currentState = fourBarWait;
            stateMachineTimer = getRuntime();
        }else{
            currentState = depoTransition;
        }
    }
    public void depositorTransition(){
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(1);
        if (ColorSensorCheck(frontColorSensor) != "None"){
            currentState = liftOut;
            frontDepositorServo.setPosition(0);
            frontDepositorServo.setPosition(0);
        }

    }
    public void fourBarWait(){
        if (stateMachineTimer <= getRuntime() - 0.6) currentState = fourBarDock;
    }
    public void fourBarDock(){
        fourBarServo.setPosition(0);
        if (gamepad2.dpad_down) currentState = liftDock;
    }
    public void liftDock(){
        pixelLiftMotor.setTargetPosition(0);
        if (pixelLiftMotor.getCurrentPosition() <= 2) currentState = idle;
    }
}

