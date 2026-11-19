# Autonomous Development

## Current prototype

`src/main/java/org/firstinspires/ftc/teamcode/TimedParkAuto.java` adds the
**Timed Park (Prototype)** autonomous. It commands both drive motors forward at
20% power for 750 ms, then stops them. This is a first hardware bring-up routine,
not a calibrated path or a complete BIOBUZZ scoring strategy.

It assumes the configured motor names are `left_drive` and `right_drive`, with
the same initial directions as `ArcadeDrive`. The robot must be assembled and
the direction checked safely before running it. Start in a clear area, keep the
Driver Station STOP control available, and adjust the duration only after
observing the robot at low speed. Carpet drag, battery level, wheel selection,
gear ratio, and robot mass all affect the distance traveled.

## Next measurements

Before turning this into a repeatable park routine, record:

- Exact starting tile and orientation, plus the desired parking location.
- Which wheels and wheel diameter are installed.
- Final motor and gearbox configuration on each side.
- Chassis track width and wheelbase.
- Whether both drive motors report usable encoder counts in the active config.
- Measured distance traveled at a few low power/time settings on the practice surface.
- Confirmation that forward and turn directions match the robot's physical orientation.

The current prototype intentionally uses timed open-loop drive because the robot
and encoder setup are not yet confirmed. Once encoder operation and wheel/gear
measurements are known, replace the timed command with a measured-distance move;
then add heading correction and route steps as the field plan is finalized.
