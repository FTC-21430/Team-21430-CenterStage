package org.firstinspires.ftc.teamcode;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
//TODO:fix this
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;
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
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public abstract class Robot extends LinearOpMode {
    IMU imu;
    // Declare OpMode members.
    double Target = 0;
    double error = 0;
    double current = 0;
    public double RobotAngle = 0;
    double drive;
    double slide;
    double turn;
    double distanceX, distanceY, PowerX, PowerY, PowerF, PowerS;
    public double RobotX, RobotY;
    public ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftFrontMotor = null;
    public DcMotor leftBackMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor rightBackMotor = null;
    public float controlHubChange = 51;
    int liftPosition;
    enum operatorState
    {
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
    public Servo fourBarServo = null;
    public Servo backDepositorServo = null;
    public Servo frontDepositorServo = null;
    public DcMotor transferMotor = null;
    public Servo droneTrigger = null;

    public double scoringAngle = 0;
    FtcDashboard dashboard;

    public double robotHeading;
    double leftFrontPower;
    double leftBackPower;
    double rightFrontPower;
    double rightBackPower;
    boolean DriverOrientationDriveMode = true;
    boolean Driver1Leftbumper;
    double startingangle;


    float gain = 5;
    final float[] hsvValues = new float[3];

    NormalizedColorSensor backColorSensor;
    NormalizedColorSensor frontColorSensor;

    View relativeLayout;


    public void colorSenseInit(){
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
    public void updateColorSensors(){

        frontColorSensor.setGain(gain);
        backColorSensor.setGain(gain);
        }

    RevBlinkinLedDriver blinkinLedDriver;
    RevBlinkinLedDriver.BlinkinPattern pattern;

    public DigitalChannel ClimberLimitSwitchBottom;


    public void LightsInit(){
        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver .class, "blinkin");
        pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
        blinkinLedDriver.setPattern(pattern);
    }
public void lightsUpdate(){
        blinkinLedDriver.setPattern(pattern);
}
    public void straferAlgorithm(){
        DriverOrientationDriveMode = false;
        if(DriverOrientationDriveMode == true){
//            slide = (slide * Math.cos(robotHeading)) - (drive * Math.sin(robotHeading));
//            drive = (slide * Math.sin(robotHeading)) + (drive * Math.cos(robotHeading));


            double temp = drive * Math.cos(-robotHeading) + slide * Math.sin(-robotHeading);
            slide = -drive * Math.sin(-robotHeading) + slide * Math.cos(-robotHeading);
            drive = temp;
        }

        if (gamepad1.a) {
            drive *= -1;
            slide *= -1;
        }

        leftFrontPower = Range.clip(drive + slide + turn, -1.0, 1.0);
        leftBackPower  =Range.clip(drive - slide + turn,-1.0, 1.0 );
        rightFrontPower=Range.clip(drive - slide - turn, -1.0, 1.0);
        rightBackPower =Range.clip(drive + slide - turn, -1.0, 1.0);

    }
    public void IMU_Update(){
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        if(orientation.getRoll(AngleUnit.DEGREES) == 0 && orientation.getPitch(AngleUnit.DEGREES) == 0 && orientation.getYaw(AngleUnit.DEGREES) == 0) {
            telemetry.addData("IMU failed?", "Re-initializing!");
            RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
            RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
            RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
            imu.initialize(new IMU.Parameters(orientationOnRobot));
        }
        AngularVelocity angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
        current = orientation.getYaw(AngleUnit.DEGREES);
        telemetry.addData("Yaw (Z)", "%.2f Deg. (Heading)", orientation.getYaw(AngleUnit.DEGREES));
        robotHeading = orientation.getYaw(AngleUnit.RADIANS) ;

    }
    public void IMUReset(){
        telemetry.addData("Yaw", "Reset" + "ing\n");
        imu.resetYaw();
        Target = 0;
    }

    public void ProportionalFeedbackControl(){
        telemetry.addData("angle", current);
        telemetry.addData("target", Target);
        error = Wrap((Target - current));
        if (gamepad1.right_stick_x != 0){
            Target = current;
        }

        turn -= error/20;
    }
    double Wrap(double angle){
        while(angle > 180){
            angle -= 360;
        }
        while(angle < -180){
            angle += 360;
        }
        return angle;
    }


    //        public void autoWait(int time){
//         while(time > -1){
//        wait(1);
//        time -= 1;
//        }
    public void Init() {
        dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
       //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//TODO:FIX THIS
        colorSenseInit();
        LightsInit();
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
        pixelLiftMotor.setTargetPosition(1);
        transferMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pixelLiftMotor.setTargetPosition(1);
        pixelLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        pixelLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        climberMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        pixelLiftMotor.setPower(0.8);
        fourBarServo.setPosition(0.92);


climberMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
climberMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        droneTrigger.setPosition(0.4);
        intakeServo.setPosition(0.8);

        ClimberLimitSwitchBottom = hardwareMap.get(DigitalChannel.class, "Climber_Limit_Switch_Bottom");
        ClimberLimitSwitchBottom.setMode(DigitalChannel.Mode.INPUT);

               leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);

      //  pixelLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

       imu.resetYaw();
       liftPosition = pixelLiftMotor.getCurrentPosition();
    }


    public String ColorSensorCheck(NormalizedColorSensor sensor) {



        NormalizedRGBA colors = sensor.getNormalizedColors();

        Color.colorToHSV(colors.toColor(), hsvValues);

        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(hsvValues));
            }
        });
//        telemetry.addData("Color Data:H", hsvValues[0]);
//        telemetry.addData("Color Data:S", hsvValues[1]);
//        telemetry.addData("Color Data:V", hsvValues[2]);
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

