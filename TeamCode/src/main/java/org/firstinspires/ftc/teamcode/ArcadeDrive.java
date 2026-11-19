package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/** Two-motor arcade drive using gamepad 1 on the FTC Driver Station. */
@TeleOp(name = "Arcade Drive", group = "Drive")
public class ArcadeDrive extends LinearOpMode {
    // THESE NAMES MUST MATCH YOUR ROBOT CONFIGURATION (including lowercase).
    private static final String LEFT_MOTOR_NAME = "left_drive";
    private static final String RIGHT_MOTOR_NAME = "right_drive";

    // Start at half power. Increase toward 1.0 only after testing the drivetrain.
    private static final double MAX_DRIVE_POWER = 0.50;
    private static final double STICK_DEADBAND = 0.05;

    // Starting directions for mirrored motors. Verify with the wheels off the floor.
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

            // REV HD Hex + stacked 5:1 and 4:1 UltraPlanetary cartridges:
            // nominal 20:1 (actual about 18.9:1). Power control needs no gear math.
            leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            telemetry.addLine("Ready: Arcade Drive");
            telemetry.addLine("Gamepad 1: left stick forward/back, right stick turn");
            telemetry.addData("Maximum power", "%.0f%%", MAX_DRIVE_POWER * 100.0);
            telemetry.update();

            waitForStart();

            while (opModeIsActive()) {
                // Gamepad Y is negative when pushed forward; negate it here.
                double forward = applyDeadband(-gamepad1.left_stick_y);
                double turn = applyDeadband(gamepad1.right_stick_x);

                double leftPower = forward + turn;
                double rightPower = forward - turn;

                // Scale both sides together to preserve steering at full stick.
                double denominator = Math.max(1.0,
                        Math.max(Math.abs(leftPower), Math.abs(rightPower)));
                leftPower = leftPower / denominator * MAX_DRIVE_POWER;
                rightPower = rightPower / denominator * MAX_DRIVE_POWER;

                leftDrive.setPower(leftPower);
                rightDrive.setPower(rightPower);

                telemetry.addData("Left power", "%.2f", leftPower);
                telemetry.addData("Right power", "%.2f", rightPower);
                telemetry.update();
                idle();
            }
        } finally {
            // Stop on Driver Station STOP, interruption, or an OpMode error.
            leftDrive.setPower(0.0);
            rightDrive.setPower(0.0);
        }
    }

    private static double applyDeadband(double value) {
        if (Math.abs(value) <= STICK_DEADBAND) {
            return 0.0;
        }
        return Math.copySign((Math.abs(value) - STICK_DEADBAND)
                / (1.0 - STICK_DEADBAND), value);
    }
}
