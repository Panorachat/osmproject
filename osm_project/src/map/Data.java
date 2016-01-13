package map;
import java.util.ArrayList;
import java.util.Collections;


public class Data {

	public Way data[] = new Way[Parser.getAllWay().size()];
	
	public Data() {
		int i = 0;
        for(int wi=0;wi<Parser.getAllWay().size();wi++){
        	if(Parser.getAllWay().get(wi).getAltitude() == 0) {
        		this.data[i] = Parser.getAllWay().get(wi);
        		i++;
        	}
        }
        for(int wi=0;wi<Parser.getAllWay().size();wi++){
        	if(Parser.getAllWay().get(wi).getAltitude() == 1) {
        		this.data[i] = Parser.getAllWay().get(wi);
        		i++;
        	}
        }
	}
}
