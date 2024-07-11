package org.firstinspires.ftc.teamcode.General;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class TestRobot extends LinearOpMode {

    private float x;
    private float y;
    private float angle;

    public TestRobot (float x, float y, float angle){
        this.x=x;
        this.y=y;
        this.angle=angle;
        //this allows you access class attributes
        //even when you have local variables with the same name
    }
}
