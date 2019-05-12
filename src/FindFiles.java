import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FindFiles {

    public Map<String, List<String>> get(String path){
        return getFiles(path);
    }

    private Map<String, List<String>> getFiles(String path){
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        Map<String,List<String>> map = new LinkedHashMap<>();
        List<String> files = new ArrayList<>();
        for(File file:listOfFiles){
            if (file.isDirectory()) {
                map.putAll(getFiles(file.getPath()));
            }
            else if(file.isFile()){
                String fileName = file.getName();
                if(fileName.endsWith(".sql")){
                    files.add(file.getPath());
                }
            }
        }
        map.put(path,files);
        System.out.println("FolderDetails="+ map);
        return map;
    }
}
