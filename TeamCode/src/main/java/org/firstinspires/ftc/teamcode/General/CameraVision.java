/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.General;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagMetadata;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseRaw;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

/*
 * This OpMode illustrates the basics of TensorFlow Object Detection,
 * including Java Builder structures for specifying Vision parameters.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
 */
//@TeleOp(name = "CameraVision", group = "Concept")

public abstract class CameraVision extends GeneralCode {
    public boolean HasSeenTag = false;
    public float camBarrierONE = 200;
    public float camBarrierTwo = 400;
    public double x;
    public double y;
    public int Zone = 2;
    public double Delay = 0;
    private boolean dpadUpOLD;
    private boolean dpadDownOLD;
    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    // TFOD_MODEL_ASSET points to a model file stored in the project Asset location,
    // this is only used for Android Studio when using models in Assets.
    private static final String TFOD_MODEL_ASSET = "Team_Props.tflite";
    // TFOD_MODEL_FILE points to a model file stored onboard the Robot Controller's storage,
    // this is used when uploading models directly to the RC using the model upload interface.
    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/model_20231014_120906.tflite";
    // Define the labels recognized in the model for TFOD (must be in training order!)
    private static final String[] LABELS = {
            "blue-prop",
            "red-prop",
    };

    /**
     * The variable to store our instance of the TensorFlow Object Detection processor.
     */


    /**
     * The variable to store our instance of the vision portal.
     */

    private VisionPortal TensorFlowVisionPortal;
    private VisionPortal AprilTagVisionPortal;
    private AprilTagProcessor ATProcessor;
    private TfodProcessor tfod;
    public boolean aprilTagProcessorActive = false;

    public void AprilTagInit() {
        aprilTagProcessorActive = true;
        ATProcessor = AprilTagProcessor.easyCreateWithDefaults();
        AprilTagVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 2"), ATProcessor);
    }

    public float[] aprilTagFindRobotPosition() {
        HasSeenTag = false;
        float[] returnArray = {0, 0, 0};

        List<AprilTagDetection> detections = ATProcessor.getDetections();
        for (AprilTagDetection detection : detections) {
            HasSeenTag = true;
            AprilTagMetadata metaData = detection.metadata;
            AprilTagPoseRaw rawpose = detection.rawPose;
            if (!detections.isEmpty()) {
                float tagInFieldX = metaData.fieldPosition.get(0);
                float tagInFieldY = metaData.fieldPosition.get(1);
                float tagInFieldZ = metaData.fieldPosition.get(2);
                OpenGLMatrix tagInFieldR = new OpenGLMatrix(metaData.fieldOrientation.toMatrix());
                OpenGLMatrix tagInFieldFrame = OpenGLMatrix.identityMatrix()
                        .translated(tagInFieldX, tagInFieldY, tagInFieldZ)
                        .multiplied(tagInFieldR);
                float tagInCameraX = (float) rawpose.x;
                float tagInCameraY = (float) rawpose.y;
                float tagInCameraZ = (float) rawpose.z;
                OpenGLMatrix tagInCameraR = new OpenGLMatrix((rawpose.R));
                OpenGLMatrix cameraInTagFrame = OpenGLMatrix.identityMatrix()
                        .translated(tagInCameraX, tagInCameraY, tagInCameraZ)
                        .multiplied(tagInCameraR)
                        .inverted();
                //TODO:Make these values true to the robot THESE VALUES ARE ONLY TEMPORARY
                float cameraInRobotX = 1;
                float cameraInRobotY = -4.5f;
                float cameraInRobotZ = 1.75f;
                OpenGLMatrix cameraInRobotR = new Orientation(AxesReference.INTRINSIC, AxesOrder.XYZ,
                        AngleUnit.DEGREES, -90, 180, 0, 0)
                        .getRotationMatrix();
                OpenGLMatrix robotInCameraFrame = OpenGLMatrix.identityMatrix()
                        .translated(cameraInRobotX, cameraInRobotY, cameraInRobotZ)
                        .multiplied(cameraInRobotR)
                        .inverted();
                OpenGLMatrix robotInFieldFrame =
                        tagInFieldFrame
                                .multiplied(cameraInTagFrame)
                                .multiplied(robotInCameraFrame);
                VectorF robotInFieldTranslation = robotInFieldFrame.getTranslation();

                Orientation robotInFieldOrientation = Orientation.getOrientation(robotInFieldFrame,
                        AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);

                float robotInFieldX = robotInFieldTranslation.get(0);
                float robotInFieldY = robotInFieldTranslation.get(1);
                float robotInFieldZ = robotInFieldTranslation.get(2);
                float robotInFieldRoll = robotInFieldOrientation.firstAngle;
                float robotInFieldPitch = robotInFieldOrientation.secondAngle;
                float robotInFieldYaw = robotInFieldOrientation.thirdAngle;

                returnArray[0] = robotInFieldX;
                returnArray[1] = robotInFieldY;
                returnArray[2] = robotInFieldYaw * ((float) Math.PI / 180);
                telemetry.addData("robotX", robotInFieldX);
                telemetry.addData("robotY", robotInFieldY);
                telemetry.addData("robotYaw", robotInFieldYaw);
            }
            break;
        }
        return returnArray;
    }

    public void CamInit() {
        initTfod();

        // Wait for the DS start button to be touched.
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch Play to start OpMode");
        telemetry.update();
    }

    public void configDelay() {
        if (gamepad1.dpad_up && !dpadUpOLD) Delay += 1;
        if (gamepad1.dpad_down && !dpadDownOLD) Delay -= 1;
        dpadDownOLD = gamepad1.dpad_down;
        dpadUpOLD = gamepad1.dpad_up;
        telemetry.addData("Auto Delay = ", Delay);
    }

    public void ZoneTelemetryUntilStart() {
        while (!isStarted()) {
            configDelay();
            TfodZoneAndTelemetry();
            // Push telemetry to the Driver Station.
            telemetry.update();
        }
        Zone = 0;
        Zone = findZone();
    }

    public void CamEnd() {
        TensorFlowVisionPortal.close();
    }

    /**
     * Initialize the TensorFlow Object Detection processor.
     */
    private void initTfod() {

        tfod = new TfodProcessor.Builder()

                .setModelAssetName(TFOD_MODEL_ASSET)
                .setModelLabels(LABELS)
                .build();

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        //builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        //builder.setAutoStopLiveView(false);

        // Set and enable the processor.
        builder.addProcessor(tfod);

        // Build the Vision Portal, using the above settings.
        TensorFlowVisionPortal = builder.build();

        // Set confidence threshold for TFOD recognitions, at any time.
        //tfod.setMinResultConfidence(0.75f);

        // Disable or re-enable the TFOD processor at any time.
        //visionPortal.setProcessorEnabled(tfod, true);

    }   // end method initTfod()

    /**
     * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
     */
    public int findZone() {
        if (x <= camBarrierONE) {
            telemetry.addData("ZoneREAD", "1");
            return 1;
        } else if (x >= camBarrierONE && x <= camBarrierTwo) {
            telemetry.addData("ZoneREAD", "2");
            return 2;
        } else if (x >= camBarrierTwo) {
            telemetry.addData("ZoneREAD", "3");
            return 3;
        } else {
            telemetry.addData("ZoneREAD", "Did not work :'( ");
            return 2;
        }
    }

    private void TfodZoneAndTelemetry() {

        List<Recognition> currentRecognitions = tfod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());

        // Step through the list of recognitions and display info for each one.
        for (Recognition recognition : currentRecognitions) {
            x = (recognition.getLeft() + recognition.getRight()) / 2;
            y = (recognition.getTop() + recognition.getBottom()) / 2;
            Zone = findZone();
            telemetry.addData("zone!", Zone);
            telemetry.addData("", " ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
        }   // end for() loop
    }   // end method telemetryTfod()
}   // end class
