package org.firstinspires.ftc.teamcode.General;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
//TODO:fix this
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import java.util.function.ToDoubleBiFunction;

@Config
public abstract class Robot extends LinearOpMode {
    //TODO tune these values
    public static double ClimberBarDockedPosition = 0.55105;
    public static double ClimberBarOutPosition = 0.62;
    public static double ClimberBarMidPosition = 0.60;

    public IMU imu;
    public boolean resettingImu = false;
    // Declare OpMode members.
    public DigitalChannel PixelLiftLimitSwitch;
    public double TargetAngle = 0;
    public double error = 0;
    public double current = 0;
    public double RobotAngle = 0;
    public double drive;
    public double slide;
    public double turn;
    public boolean UseAprilTags;
    public double distanceX, distanceY, PowerX, PowerY, PowerF, PowerS, derivativeX, derivativeY;
    public double lastErrorX, lastErrorY, lastTime;
    public double RobotX, RobotY;
    public ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftFrontMotor = null;
    public DcMotor leftBackMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor rightBackMotor = null;
    public double lastErrorAngle;
    public double derivativeAngle;

    public float controlHubChange = 51;  
    public int liftPosition;
    public boolean TurnOLD = false;
    public boolean CurrentAlign = true;
    public boolean IsProgramAutonomous;
    public static double derivativeConstantAngle = 0.0015;
    public static double proportionalConstantAngle = 0.02;
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
    FtcDashboard dashboard;
    public double minPower = 0.01;
    public double endOfClipPower = 0.2;
    public double turnTimer;
    public double robotHeading;
    public double leftFrontPower;
    public double leftBackPower;
    public double rightFrontPower;
    public double rightBackPower;
    public boolean DriverOrientationDriveMode = true;
    public boolean Driver1Leftbumper;
    public double startingangle;
    public double AutoStartAngle = 0;
    public float gain = 5;
    public final float[] hsvValues = new float[3];
    public NormalizedColorSensor backColorSensor;
    public NormalizedColorSensor frontColorSensor;
    public double lastTimeAngle;

    View relativeLayout;

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
    public void straferAlgorithm() {

        if (DriverOrientationDriveMode == true) {

            double temp = drive * Math.cos(-robotHeading) + slide * Math.sin(-robotHeading);
            slide = -drive * Math.sin(-robotHeading) + slide * Math.cos(-robotHeading);
            if (!CurrentAlign) drive = temp;
        }

        leftFrontPower = Range.clip(drive + slide + turn, -1.0, 1.0);
        leftBackPower = Range.clip(drive - slide + turn, -1.0, 1.0);
        rightFrontPower = Range.clip(drive - slide - turn, -1.0, 1.0);
        rightBackPower = Range.clip(drive + slide - turn, -1.0, 1.0);

    }

    public void IMU_Update() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        if (orientation.getRoll(AngleUnit.DEGREES) == 0 && orientation.getPitch(AngleUnit.DEGREES) == 0
                && orientation.getYaw(AngleUnit.DEGREES) == 0) {
            if (!resettingImu) {
                telemetry.addData("IMU failed?", "Re-initializing!");
                resettingImu = true;
                RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
                RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
                RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
                imu.initialize(new IMU.Parameters(orientationOnRobot));
            }
        } else {
            resettingImu = false;
        }
        telemetry.addData("resettingIMU", resettingImu);
        AngularVelocity angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);


        robotHeading = orientation.getYaw(AngleUnit.RADIANS);
        RobotAngle = orientation.getYaw(AngleUnit.RADIANS);
        RobotAngle += AutoStartAngle;
        telemetry.addData("Yaw (Z)", "%.2f Rad. (Heading)", RobotAngle);
    }

    public void IMUReset() {
        telemetry.addData("Yaw", "Reset" + "ing\n");
        imu.resetYaw();
        TargetAngle = 0;
    }

    public void ProportionalFeedbackControl() {
        double currentTime = getRuntime();
        if (resettingImu)
            return;
        telemetry.addData("target", TargetAngle);
        error = Wrap((TargetAngle/180)*Math.PI - RobotAngle)*180/Math.PI;
        derivativeAngle = (error - lastErrorAngle)/(currentTime - lastTimeAngle);
        if (gamepad1.right_stick_x != 0 || turnTimer + 0.3 >= getRuntime()) {
            if (!IsProgramAutonomous) {
                TargetAngle = (RobotAngle * 180 / Math.PI);
            }
        }
        if (gamepad1.right_stick_x == 0 && !TurnOLD) {
            turnTimer = getRuntime();
        }

        if (gamepad1.right_stick_x != 0) TurnOLD = false;
        if (gamepad1.right_stick_x == 0) TurnOLD = true;
        telemetry.addData("ERROR", error);
        telemetry.addData("BEFORE", turn);
        turn -= error * proportionalConstantAngle + (derivativeConstantAngle * derivativeAngle);
        telemetry.addData("AFTER", turn);
        lastTimeAngle = currentTime;
        lastErrorAngle = error;
    }

    double Wrap(double angle) {
        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        return angle;
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
        pixelLiftMotor.setPower(0.8);


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