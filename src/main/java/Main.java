public class Main {
    //Run methods here- keep it breif don't overdo it
    public static void main(String[] args){
        Parser parser = new Parser();

        System.out.println("List of reserved words: \n");
        parser.initializeReservedWords();
        System.out.println("List of Identifiers: \n");
        parser.getIdentifiers();

    }
}
