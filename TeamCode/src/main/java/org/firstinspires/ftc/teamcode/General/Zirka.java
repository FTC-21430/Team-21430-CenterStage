package org.firstinspires.ftc.teamcode.General;

import android.app.Activity;
import android.graphics.Color;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Zirka extends Robot {

    public static double ClimberBarDockedPosition = 0.55105;
    public static double ClimberBarOutPosition = 0.62;
    public static double ClimberBarMidPosition = 0.60;
    public DigitalChannel PixelLiftLimitSwitch;
    public int liftPosition;
    public boolean TurnOLD = false;
    //Driver control to allow for drift while rotating
    public enum operatorState {
        idle,
        intaking,
        intakeManaul,
        intakeDone,
        intakeCancel,
        scoreIdle,
        extendLift,
        extendBar,
        liftOut,
        score,
        scoreFinished,
        depoTransition,
        fourBarWait,
        fourBarDock,
        liftDock,
        liftCalibrate,
        scoreDocked,
        transferDocked,
        dockedScoreFinished,
        highFourBarExtend,
    }
    public operatorState currentState = operatorState.idle;
    public DcMotor climberMotor = null;
    public DcMotor intakeMotor = null;
    public DcMotor pixelLiftMotor = null;
    public Servo intakeServo = null;
    public Servo ClimberBarServo = null;
    public Servo DroneLinkageServo = null;
    public Servo fourBarServo = null;
    public Servo backDepositorServo = null;
    public Servo frontDepositorServo = null;
    public DcMotor transferMotor = null;
    public Servo droneTrigger = null;
    public double scoringAngle = 0;
    public double turnTimer;
    //driver preference
    public final float[] hsvValues = new float[3];
    public NormalizedColorSensor backColorSensor;
    public NormalizedColorSensor frontColorSensor;

    public void colorSenseInit() {
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        try {
            runSample(); // actually execute the sample
        } finally {

            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.WHITE);
                }
            });
        }
    }

    protected void runSample() {
        frontColorSensor = hardwareMap.get(NormalizedColorSensor.class, "frontColorSensor");
        backColorSensor = hardwareMap.get(NormalizedColorSensor.class, "backColorSensor");
    }

    public void updateColorSensors() {
        frontColorSensor.setGain(gain);
        backColorSensor.setGain(gain);
    }

    public RevBlinkinLedDriver blinkinLedDriver;
    public RevBlinkinLedDriver.BlinkinPattern pattern;

    public DigitalChannel ClimberLimitSwitchBottom;

    public void LightsInit() {
        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
        blinkinLedDriver.setPattern(pattern);
    }
    public void lightsUpdate() {
        blinkinLedDriver.setPattern(pattern);
    }

    public void Init() {
        dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        colorSenseInit();
        LightsInit();

        PixelLiftLimitSwitch = hardwareMap.get(DigitalChannel.class, "LiftLimitSwitch");
        PixelLiftLimitSwitch.setMode(DigitalChannel.Mode.INPUT);

        DroneLinkageServo = hardwareMap.get(Servo.class, "DroneLinkage");
        DroneLinkageServo.setPosition(0.9);
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // get a reference to our digitalTouch object.

        imu.initialize(new IMU.Parameters(orientationOnRobot));
        // set the digital channel to input.

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        transferMotor = hardwareMap.get(DcMotor.class, "TransferMotor");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "left_Front");
        leftBackMotor = hardwareMap.get(DcMotor.class, "left_Back");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "right_Front");
        rightBackMotor = hardwareMap.get(DcMotor.class, "right_Back");
        climberMotor = hardwareMap.get(DcMotor.class, "climber");
        pixelLiftMotor = hardwareMap.get(DcMotor.class, "LiftMotor");
        intakeServo = hardwareMap.get(Servo.class, "IntakeServo");
        fourBarServo = hardwareMap.get(Servo.class, "fourBarServo");
        backDepositorServo = hardwareMap.get(Servo.class, "backDepo");
        frontDepositorServo = hardwareMap.get(Servo.class, "frontDepo");
        droneTrigger = hardwareMap.get(Servo.class, "DroneTrigger");
        ClimberBarServo = hardwareMap.get(Servo.class, "climberBarServo");
        ClimberBarServo.setPosition(ClimberBarDockedPosition);
        pixelLiftMotor.setTargetPosition(1);
        transferMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        transferMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pixelLiftMotor.setTargetPosition(1);
        pixelLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pixelLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        DriverOrientationDriveMode = true;
        climberMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pixelLiftMotor.setPower(1);


        fourBarServo.setPosition(0.954);
        climberMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        climberMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        droneTrigger.setPosition(0.4);
        intakeServo.setPosition(0.8);
        ClimberLimitSwitchBottom = hardwareMap.get(DigitalChannel.class, "Climber_Limit_Switch_Bottom");
        ClimberLimitSwitchBottom.setMode(DigitalChannel.Mode.INPUT);
        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);

        imu.resetYaw();
        liftPosition = pixelLiftMotor.getCurrentPosition();
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transferMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    public String ColorSensorCheck(NormalizedColorSensor sensor) {


        NormalizedRGBA colors = sensor.getNormalizedColors();

        Color.colorToHSV(colors.toColor(), hsvValues);

        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(hsvValues));
            }
        });

        if (((DistanceSensor) sensor).getDistance(DistanceUnit.CM) <= 3) {
            if (hsvValues[2] > .13) {
                //white pixel
                return "White";
            } else if (170 < hsvValues[0]) {
                //purple pixel
                return "Purple";
            } else if (120 > hsvValues[0]) {
                //yellow pixel
                return "Yellow";
            } else {
                //green pixel
                return "Green";
            }
        } else {
            return "None";
        }
    }


}
