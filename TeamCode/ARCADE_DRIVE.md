# Arcade Drive

`src/main/java/org/firstinspires/ftc/teamcode/ArcadeDrive.java` is the TeleOp for
two REV HD Hex drive motors, each with stacked 5:1 and 4:1 UltraPlanetary stages.
This is nominally 20:1 reduction; REV lists the actual combined ratio as 18.9:1.
The program uses open-loop motor power, so it does not need encoder counts or a
gear-ratio setting in the code.

## Robot configuration

In the FTC Driver Station's robot configuration, configure the two physical hub
motor ports where your drive motors are connected. Select **REV Robotics
UltraPlanetary HD Hex Motor** and use these exact device names:

| Robot side (viewed facing forward) | Device name |
| --- | --- |
| Left | `left_drive` |
| Right | `right_drive` |

Save and activate that configuration. The port numbers depend on your wiring.
If you already use different device names, change `LEFT_MOTOR_NAME` and
`RIGHT_MOTOR_NAME` in the Java file to match. The names are case-sensitive.

## Run from the Driver Station

1. Open the `FtcRobotController` project in Android Studio and deploy the
   **TeamCode** app to the robot's Control Hub or Robot Controller phone.
2. Connect the gamepad to the **Driver Station** and assign it as **gamepad 1**.
   On a Logitech F310, press **START + A** and confirm the User 1 gamepad icon.
   Other controller models may use different button labels.
3. Select **Arcade Drive** in the TeleOp list, press **INIT**, then **START**.
4. Use the **left stick up/down** for forward/reverse and the **right stick
   left/right** to turn. You can drive and turn together, or turn in place.
5. Center the sticks to command zero power. Press Driver Station **STOP** to end
   the OpMode; the code explicitly zeros both motors.

Initial maximum power is **50%**, with a **5% stick deadband** and brake behavior
at zero power. To change the speed limit, edit `MAX_DRIVE_POWER` (0.0 to 1.0).

## First direction check

Support the robot so its wheels are off the floor before the first test.
Gently push forward: both sides should propel the robot forward. If one side is
backward, change that side's `LEFT_DIRECTION` or `RIGHT_DIRECTION` constant
between `FORWARD` and `REVERSE`. Check that right-stick right turns the robot
right. Software build checks cannot verify the physical wiring or directions.

## References

- [REV HD Hex motor and gearbox ratios](https://docs.revrobotics.com/duo-build/motion/motors/hd-hex-motor)
- [REV motor configuration](https://docs.revrobotics.com/duo-control/hello-robot-java/part-2)
- [FTC motor run modes](https://ftc-docs.firstinspires.org/en/latest/tech_tips/tech-tips/tech-tip-motor-modes/tech-tip-motor-modes.html)
- [FTC Android Studio: creating and running an OpMode](https://ftc-docs.firstinspires.org/en/latest/programming_resources/tutorial_specific/android_studio/creating_op_modes/Creating-and-Running-an-Op-Mode-%28Android-Studio%29.html)
