import java.awt.Color;

public enum Color_Map {

    //couleur pour le background :
    background_color(192, 192, 192),
    //couleur pour "building" : 
    building_color(153, 255, 153),
    // couleur pour "highway" :
    motorway_color(255, 102, 102), trunk_color(255, 153, 102), primary_color(255, 204, 102), secondary_color(255, 255, 102), tertiary_color(224, 224, 224);

    
    //here begins the serious shit
    private final Color tag_color;

    private Color_Map(int R, int V, int B) {
        this.tag_color = new Color(R, V, B);
    }

    public Color getColor() {
        return this.tag_color;
    }

}
