import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UpdateSearch {

    public List<String> getData(String path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> finalList = getUpdateData(lines);
        List<String> columnNames = getColumnNames(finalList);
        return columnNames;
    }


    private List<String> getUpdateData(List<String> lines) {
        List<String> finalList = new ArrayList<>();
        String str = "";
        for (String line : lines) {
            if (line.startsWith("**") || line.startsWith("-")) {
                continue;
            }
            if (line.toLowerCase().contains("update")) {
                //System.out.println(line);
                str = line;
                if (line.toLowerCase().contains("set")) {
                    finalList.add(str);
                    str = "";
                }

            } else if (line.toLowerCase().contains("set")) {
                str += " " + line;
                finalList.add(str);
                str = "";
            } else {
                str += " " + line;
            }
        }
        return finalList;
    }


    private List<String> getColumnNames(List<String> lines) {
        List<String> colList = new ArrayList<>();
        for (String data : lines) {
            int startIdx = 0;
            int endIdx = 0;
            if (data.lastIndexOf("update") >= 0) {
                startIdx = data.lastIndexOf("update");
            }

            if (data.lastIndexOf("UPDATE") >= 0) {
                startIdx = data.lastIndexOf("UPDATE");
            }

            if (data.lastIndexOf("set") >= 0) {
                endIdx = data.lastIndexOf("set");
            }

            if (data.lastIndexOf("SET") >= 0) {
                endIdx = data.lastIndexOf("SET");
            }
            String[] atts = data.substring(startIdx + 6, endIdx).trim().split(" ");
            String tableName = atts[0].trim();
            colList.add(tableName);
        }
        return colList;
    }
}
