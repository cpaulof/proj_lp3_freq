package app;

public class Position {
    double latitude;
    double longitude;

    public Position(String position){
        position = position.replace(" ", "");
        String p[] = position.split(",");
        latitude = Double.parseDouble(p[0]);
        longitude = Double.parseDouble(p[1]);
    }
    public Position(Double lat, Double lon){
        latitude = lat;
        longitude = lon;
    }

    @Override
    public String toString(){
        return String.format("lat: %f, lon: %f", latitude, longitude);
    }
}
