package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareMap.UnderscoreHardwareMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TeleOp(name = "Interpreted Auto lOCAL")
public class InterpretedAutoLocal extends LinearOpMode {

    private UnderscoreHardwareMap hm;

    boolean running = true;
    boolean executing = false;
    List<Command> commandBuffer = new ArrayList<>();

    @Override
    public void runOpMode() {
        waitForStart();

        hm = new UnderscoreHardwareMap(hardwareMap);
        try {
            Server s = new Server();
        } catch (Exception e) {
            telemetry.addLine(e.toString());
            telemetry.update();
            sleep(10000);
            running = false;
        }

        while(running) sleep(10);
    }

    class Command {
        public CommandType type;
        public double[] args;

        public Command(String type, String[] args) {
            this.type = CommandType.commandNames.get(type);
            this.args = new double[args.length];
            for(int i = 0; i < args.length; i++) {
                this.args[i] = Double.parseDouble(args[i].trim());
            }
        }

        public void run(UnderscoreHardwareMap hm) {
            switch (type) {
                case DRIVE:
                    telemetry.addLine("hm.driveFeet(" + Math.abs(args[0]) + ", " + (args[0] > 0 ? 1.0 : -1.0) + ")" );
                    telemetry.update();
                    hm.driveFeet(Math.abs(args[0]), args[0] > 0 ? 1.0 : -1.0);
                    break;
                case TURN:
                    telemetry.addLine("hm.turnRight(" + Math.abs(args[0]) + ", " + (args[0] > 0 ? 1.0 : -1.0) + ")" );
                    telemetry.update();
                    hm.turnRight(Math.abs(args[0]), args[0] > 0 ? -1.0 : 1.0);
                    break;
                case SLEEP:
                    telemetry.addLine("sleep(" + (long)args[0] + ")");
                    telemetry.update();
                    sleep((long) args[0]);
                    break;
            }
        }
    }

    enum CommandType {
        DRIVE,
        TURN,
        SLEEP;

        public static final Map<String, CommandType> commandNames = new HashMap<>();

        static {
            commandNames.put("DRIVE", CommandType.DRIVE);
            commandNames.put("TURN", CommandType.TURN);
            commandNames.put("SLEEP", CommandType.SLEEP);
        }
    }

    static final int port = 50904;
    static final String ip = "192.168.49.1";

    class Server {

        Server() throws IOException {
//            ServerSocket serverSocket = new ServerSocket(port, 0, InetAddress.getByName(ip));
//            Socket clientSocket = serverSocket.accept();
//            BufferedReader clientOutput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String[] lines = {
                    "DRIVE:-9.3",
            };

            int i = 0;
            while(i < lines.length) {
                String line = lines[i++];
                telemetry.addLine(line);
                telemetry.update();
                if(line.equals("exit")) {
                    break;
                } else if(line.equals("stop"))  {
                    haltMotors();
                    executing = false;
                    commandBuffer.clear();
                } else if(line.equals("run")) {
                    executing = true;
                } else if(executing) {
                    String[] colonSplit = line.split(":");
                    String[] args = colonSplit[1].split(",");
                    Command c = new Command(colonSplit[0], args);
                    c.run(hm);
                    commandBuffer.add(c);
                }
            }

            running = false;
        }
    }

    private void haltMotors() {
        hm.setPower(0.0);
    }


}
