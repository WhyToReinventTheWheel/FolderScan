
public class Main {
    private static String PATH = "C:\\Code\\FolderScan\\src\\test.sql";

    public static void main(String[] args) {
        UpdateSearch updateSearch = new UpdateSearch();
        for (String data : updateSearch.getData(PATH)) {
            System.out.println(data);
        }
    }
}
