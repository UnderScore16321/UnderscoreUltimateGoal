package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.HardwareMap.UnderscoreHardwareMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TeleOp(name = "Interpreted Auto")
public class InterpretedAuto extends LinearOpMode {

    private UnderscoreHardwareMap hm;

    @Override
    public void runOpMode() {
        hm = new UnderscoreHardwareMap(hardwareMap);
        runText(
                "drive:  2.5\n" +
                        "turn:   90\n" +
                        "drive: -1\n"
        );

    }

    private void runText(String commands) {
        String[] lines = commands.split("\n");
        for (String line : lines) {
            parseLine(line).run(hm);
        }
    }

    private Command parseLine(String com) {
        String[] nameSplit = com.split(":");

        List<String> argStrings = Arrays.asList(nameSplit[1].split(","));
        for (int i = argStrings.size() - 1; i >= 0; i--) {
            argStrings.set(i, argStrings.get(i).trim());
            if (argStrings.get(i).equals("")) argStrings.remove(i);
        }
        double[] values = new double[argStrings.size()];
        for (int i = 0; i < argStrings.size(); i++) {
            values[i] = Integer.parseInt(argStrings.get(i));
        }

        String commandName = nameSplit[0];
        CommandType commandType = CommandType.commandNames.get(commandName);

        return new Command(commandType, values);
    }

    static class Command {
        public CommandType type;
        public double[] args;

        public Command(CommandType type, double[] args) {
            this.type = type;
            this.args = args;
        }

        public void run(UnderscoreHardwareMap hm) {
            switch (type) {
                case DRIVE:
                    hm.driveFeet(Math.abs(args[0]), args[0] > 0 ? 1.0 : -1.0);
                    break;
                case TURN:
                    hm.turnRight(Math.abs(args[0]), args[0] > 0 ? 1.0 : -1.0);
                    break;
            }
        }
    }

    enum CommandType {
        DRIVE,
        TURN;

        public static final Map<String, CommandType> commandNames = new HashMap<>();

        static {
            commandNames.put("drive", CommandType.DRIVE);
            commandNames.put("turn", CommandType.TURN);
        }
    }
}
