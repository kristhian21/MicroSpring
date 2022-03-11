package arep.microSpring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MicroSpring {

    private static MicroSpring instance;
    private File path;

    public MicroSpring() {
        String packgName = MicroSpring.class.getPackage().getName().replace(".", "/");
        this.path = new File("./src/main/java/"+packgName);
    }

    public static MicroSpring getInstance() {
        if(instance == null){
            instance = new MicroSpring();
        }
        return instance;
    }

}
