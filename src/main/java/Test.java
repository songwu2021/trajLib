import calculation.ListString;
import io.LoadTrajectories;
import model.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Test {
    public static void testModel1() throws ParseException, IOException {
        LoadTrajectories loadTrajectories = new LoadTrajectories();
        loadTrajectories.filePath("H:\\UpanSky\\DEDS_Java\\trajLib\\src\\main\\resources\\traj1.csv")
                .withHeader(true)
                .trajIndex(0)
                .xIndex(1)
                .yIndex(2)
                .datetimeIndex(3)
                .addAttr(AttributeType.DoubleAttr, "speed", 4)
                .addAttr(AttributeType.StrAttr, "city", 5)
                .sampleGap(3600*24);

        Point.setDateTimeFormatter("yyyy-MM-dd");
        List<Trajectory> trajectories = Trajectory.load(loadTrajectories);

        Trajectory COPY = trajectories.get(0).subTrajectory(0,1, "COPY");
        COPY.getPoint(0).setDatetime("2020-01-01");
        trajectories.add(COPY);

        for(Trajectory trajectory : trajectories){
            List<Double> speed = trajectory.getAttrsDouble("speed");
            List<String> city = trajectory.getAttrsString("city");
            for(int i = 0; i < trajectory.size(); i++){
                System.out.println(String.join(",", trajectory.getID(),
                        trajectory.getPoint(i).getX()+"",
                        trajectory.getPoint(i).getY()+"",
                        trajectory.getPoint(i).getDatetimeStr(),
                        speed.get(i)+"",
                        city.get(i)
                ));
            }
        }

    }

    public static void testModel2() throws ParseException, IOException {
        LoadTrajectories loadTrajectories = new LoadTrajectories();
        loadTrajectories.filePath("H:\\UpanSky\\DEDS_Java\\trajLib\\src\\main\\resources\\traj2.csv")
                .withHeader(true)
                .trajIndex(0)
                .xIndex(1)
                .yIndex(2)
                .datetimeIndex(3)
                .regularSampleGap("2000-10-01 00:00:00", "2000-10-11 00:00:00", 43200);

        List<Trajectory> trajectories = Trajectory.load(loadTrajectories);

        for(Trajectory trajectory : trajectories){
            for(int i = 0; i < trajectory.size(); i++){
                System.out.println(String.join(",", trajectory.getID(),
                        trajectory.getPoint(i).getX()+"",
                        trajectory.getPoint(i).getY()+"",
                        trajectory.getPoint(i).getDatetimeStr()
                ));
            }
        }
    }

    public static void testModel3() throws IOException {
        LoadTrajectories loadTrajectories = new LoadTrajectories();
        loadTrajectories.filePath("H:\\UpanSky\\DEDS_Java\\trajLib\\src\\main\\resources\\traj2.csv")
                .withHeader(true)
                .trajIndex(0)
                .xIndex(1)
                .yIndex(2)
                .datetimeIndex(3)
                .regularSampleGap("2000-10-01 00:00:00", "2000-10-11 00:00:00", 43200);

        try(TrajectoryIterator iterator = new TrajectoryIterator(loadTrajectories)){
            for(Trajectory trajectory : iterator){
                for(int i = 0; i < trajectory.size(); i++){
                    System.out.println(String.join(",", trajectory.getID(),
                            trajectory.getPoint(i).getX()+"",
                            trajectory.getPoint(i).getY()+"",
                            trajectory.getPoint(i).getDatetimeStr()
                    ));
                }
            }
        };
    }

    public static void main(String[] args) throws IOException, ParseException, CloneNotSupportedException {
//        Point p = new Point("2022-04-01 00:00:00", 0, 0);
//        Point p2 = new Point("2022-04-01 00:10:00", 10, 0);
//        Point p3 = new Point("2022-04-01 00:20:00", 20, 0);
//        Trajectory trajectory = new Trajectory("0");
//        trajectory.setPoints(Arrays.asList(p, p2, p3));
//        System.out.println(Arrays.toString(trajectory.getVector2("2022-04-01 00:20:00")));
//        System.out.println(trajectory.contains("2022-04-01 00:20:00"));

        testModel1();
    }
}
