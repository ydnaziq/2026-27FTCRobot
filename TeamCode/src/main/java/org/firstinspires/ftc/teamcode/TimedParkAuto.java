package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/** Early, low-speed timed-drive prototype for a simple leave-and-park autonomous. */
@Autonomous(name = "Timed Park (Prototype)", group = "Autonomous")
public class TimedParkAuto extends LinearOpMode {
    // Keep these synchronized with the active Robot Controller configuration.
    private static final String LEFT_MOTOR_NAME = "left_drive";
    private static final String RIGHT_MOTOR_NAME = "right_drive";

    // Conservative initial values. Calibrate on the assembled robot and practice field.
    private static final double DRIVE_POWER = 0.20;
    private static final long DRIVE_TIME_MS = 750;

    // Match the current ArcadeDrive assumptions; verify with the robot safely raised.
    private static final DcMotor.Direction LEFT_DIRECTION = DcMotor.Direction.REVERSE;
    private static final DcMotor.Direction RIGHT_DIRECTION = DcMotor.Direction.FORWARD;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor leftDrive = hardwareMap.get(DcMotor.class, LEFT_MOTOR_NAME);
        DcMotor rightDrive = hardwareMap.get(DcMotor.class, RIGHT_MOTOR_NAME);

        try {
            leftDrive.setPower(0.0);
            rightDrive.setPower(0.0);
            leftDrive.setDirection(LEFT_DIRECTION);
            rightDrive.setDirection(RIGHT_DIRECTION);
            leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            telemetry.addLine("Timed Park autonomous prototype");
            telemetry.addLine("After START: drive straight briefly, then stop");
            telemetry.addData("Drive power", "%.0f%%", DRIVE_POWER * 100.0);
            telemetry.addData("Drive time", "%d ms", DRIVE_TIME_MS);
            telemetry.addLine("Use only in a clear test area; tune after chassis is built.");
            telemetry.update();

            waitForStart();
            if (opModeIsActive()) {
                leftDrive.setPower(DRIVE_POWER);
                rightDrive.setPower(DRIVE_POWER);
                sleep(DRIVE_TIME_MS);
            }
        } finally {
            // Also runs if STOP is pressed or execution is interrupted.
            leftDrive.setPower(0.0);
            rightDrive.setPower(0.0);
        }

        telemetry.addLine("Drive command complete; motors stopped.");
        telemetry.update();
    }
}
