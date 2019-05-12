import java.util.List;
import java.util.Map;

public class Main {
    private static String PATH = "C:\\Code\\FolderScan\\src";
    public static void main(String[] args) {
        UpdateSearch updateSearch = new UpdateSearch();
        FindFiles findFiles = new FindFiles();
        Map<String, List<String>> files = findFiles.get(PATH);
        for (Map.Entry<String, List<String>> entry : files.entrySet()){
            System.out.println("Folder=" + entry.getKey());
            for(String file: entry.getValue()) {
                for (String data : updateSearch.getData(file)) {
                    System.out.println(data);
                }
            }
        }
    }
}
