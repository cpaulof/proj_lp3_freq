package app;

public class Utils {
    public static double distanceBetween(Position p1, Position p2){
        //
        double R = 6371;
        double lat1 = Math.toRadians(p1.latitude);
        double lat2 = Math.toRadians(p2.latitude);
        double deltaLat = Math.toRadians(p2.latitude - p1.latitude);
        double deltaLon = Math.toRadians(p2.longitude - p1.longitude);

        double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) + 
        Math.cos(lat1)*Math.cos(lat2)*Math.sin(deltaLon/2)*Math.sin(deltaLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R*c;
        //System.out.println(p1);
        //System.out.println(p2);
        return d*1000.;
    }
}
