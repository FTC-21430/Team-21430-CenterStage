package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class OldCodeForPowerPlay extends GeneralCode {

    public  DcMotor liftMotor = null;
    public Servo servoL;
    public Servo servoR;
    public boolean rightPressed = false;
    public boolean upStack_old = false;
    public DigitalChannel digitalTouch;
    public int MediumJunction = 1302;

    public void GroundLift(){
        liftMotor.setTargetPosition(100);
    }
    public void LowLift(){
        liftMotor.setTargetPosition(774);
    }
    public void MidLift(){
        liftMotor.setTargetPosition(1302);
    }
    public void HighLift(){
        liftMotor.setTargetPosition(1820);
    }
    public void IntakeOpen(){
        servoL.setPosition(0.85);
        servoR.setPosition(0.285);
    }
    public void IntakeClose(){
        servoL.setPosition(0.72);
        servoR.setPosition(0.39);
    }
    public void PowerplayInit(){
        digitalTouch = hardwareMap.get(DigitalChannel.class, "Limit_Switch");
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        liftMotor.setTargetPosition(0);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        servoL = hardwareMap.get(Servo .class, "left_hand");
        servoR = hardwareMap.get(Servo.class, "right_hand");
        servoL.setPosition(0.72);
        servoR.setPosition(0.33);

    }
    public void LiftControl(){
        if (LiftAdd <= -0.2){
            LiftManual = LiftManual + 5;
            if (LiftManual >= 1851) LiftManual = 1850;
            if (LiftManual <= 1) LiftManual = 2;
            liftMotor.setTargetPosition(LiftManual);
            //go up
        }
        if (LiftAdd >= 0.2) {
            LiftManual = LiftManual - 5;
            if (LiftManual >= 1851) LiftManual = 1850;
            if (LiftManual <= 1) LiftManual = 2;
            liftMotor.setTargetPosition(LiftManual);


            // go down
        }
        if (upStack && !upStack_old ){
            // go up
            LiftManual += 60;
            if (LiftManual >= 1851) LiftManual = 1850;
            if (LiftManual <= 1) LiftManual = 2;
            liftMotor.setTargetPosition(LiftManual);

        }
        upStack_old = upStack;
        if (calabrate_Lift == true && liftMotor.getCurrentPosition() <= 100){

            if (digitalTouch.getState() == false){
                liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                liftMotor.setPower(-0.2);

            }
            if (digitalTouch.getState() == true || runtime.seconds() >= 3){
                liftMotor.setPower(0);
                liftMotor.setTargetPosition(0);
                liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftMotor.setPower(Math.abs(1));
                calabrate_Lift = false;
            }
        }else{
            liftMotor.setPower(Math.abs(1));
        }
        if (groundJunction) {
            GroundLift();
            LiftManual = 50;
            runtime.reset();
            calabrate_Lift = true;
        }
        if (lowJunction) {
            liftMotor.setTargetPosition(774);
            //liftMotor.setTargetPosition(2600);
            LiftManual = 774;
        }

        if (mediumJunction) {
            liftMotor.setTargetPosition(1302);
            // liftMotor.setTargetPosition(4000);
            rightPressed = true;
            LiftManual = 1302;
        }
        if (highJunction) {
            liftMotor.setTargetPosition(1820);
            LiftManual = 1820;
            //liftMotor.setTargetPosition(5700);
        }
    }



}
