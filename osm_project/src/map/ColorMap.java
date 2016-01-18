package map;
import java.awt.Color;

public enum ColorMap {

    //couleur pour le background :
    background_color(192, 192, 192),
    //couleur pour "building" : 
    building_color(153, 255, 153),
    // couleur pour "highway" :
    motorway_color(255, 102, 102), // autoroute
    pedestrian_color(205, 145, 158), // passage pieton
    residential_color(130, 230, 230), // zone residentielle
    trunk_color(255, 153, 102),
    primary_color(255, 204, 102),
    secondary_color(255, 255, 102),
    tertiary_color(224, 224, 224),
	// couleur pour "amenity" :
	amenity_color(113, 113, 198);

    //here begins the serious shit
    private final Color tag_color;

    private ColorMap(int R, int V, int B) {
        this.tag_color = new Color(R, V, B);
    }

    public Color getColor() {
        return this.tag_color;
    }
}