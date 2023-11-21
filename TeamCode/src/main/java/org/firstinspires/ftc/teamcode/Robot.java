package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;
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
    public double robotHeading;
    double leftFrontPower;
    double leftBackPower;
    double rightFrontPower;
    double rightBackPower;
    boolean DriverOrientationDriveMode = true;
    boolean Driver1Leftbumper;
    float startingangle;

    float gain = 2;
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
        gain += 0.005;
        frontColorSensor.setGain(gain);
        backColorSensor.setGain(gain);
        }

    public void straferAlgorithm(){
        if(DriverOrientationDriveMode == true){
//            slide = (slide * Math.cos(robotHeading)) - (drive * Math.sin(robotHeading));
//            drive = (slide * Math.sin(robotHeading)) + (drive * Math.cos(robotHeading));


            double temp = drive * Math.cos(-robotHeading) + slide * Math.sin(-robotHeading);
            slide = -drive * Math.sin(-robotHeading) + slide * Math.cos(-robotHeading);
            drive = temp;
        }

        leftFrontPower = Range.clip(drive + slide + turn, -1.0, 1.0);
        leftBackPower  =Range.clip(drive - slide + turn,-1.0, 1.0 );
        rightFrontPower=Range.clip(drive - slide - turn, -1.0, 1.0);
        rightBackPower =Range.clip(drive + slide - turn, -1.0, 1.0);

    }
    public void IMUstuffs(){
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        AngularVelocity angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
        current = orientation.getYaw(AngleUnit.DEGREES) + startingangle;
        telemetry.addData("Yaw (Z)", "%.2f Deg. (Heading)", orientation.getYaw(AngleUnit.DEGREES));
    robotHeading = orientation.getYaw(AngleUnit.RADIANS);

    }
    public void IMUReset(){
        telemetry.addData("Yaw", "Reset" + "ing\n");
        imu.resetYaw();
        Target = 0;
    }

    public void ProportionalFeedbackControl(){
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

        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // get a reference to our digitalTouch object.

        imu.initialize(new IMU.Parameters(orientationOnRobot));
        // set the digital channel to input.

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFrontMotor = hardwareMap.get(DcMotor.class, "left_Front");
        leftBackMotor = hardwareMap.get(DcMotor.class, "left_Back");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "right_Front");
        rightBackMotor = hardwareMap.get(DcMotor.class, "right_Back");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);
        imu.resetYaw();
    }


    public String ColorSensorCheck(NormalizedColorSensor sensor) {



        NormalizedRGBA colors = sensor.getNormalizedColors();

        Color.colorToHSV(colors.toColor(), hsvValues);

        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(hsvValues));
            }
        });
        telemetry.addData("Color Data:H", hsvValues[0]);
        telemetry.addData("Color Data:S", hsvValues[1]);
        telemetry.addData("Color Data:V", hsvValues[2]);
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

