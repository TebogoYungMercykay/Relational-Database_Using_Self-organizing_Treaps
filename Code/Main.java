// Selepe Sello => TebogoYungMercykay
public class Main {
    public static Database db;
    public static String[] columns;
    public static void main(String[] args) {
        task1();
        task2();
        System.out.println("---------- NOW TESTING THE DATABASE QUERIES -----------");
        System.out.println("-------------------------------------------------------");
        CountMethod();
        FindMethod();
        UpdateMethod();
        RemoveMethod();
        TestingThe_AllWhere_Methods();
    }

    public static void task1() {
        /*
         * You are not given a Main for this task, because we want you to figure out how
         * to do it for yourself.
         * 
         * You are provided with a validTreap() function which will print out valid or
         * invalid for a passed in Treap.
         * 
         * Use this function to make sure that your heaps follow the rules set by the
         * Assignment.
         * 
         * Tip : Create a Main that inserts / deletes a lot of elements and call
         * validTreap after every step
         */
    }

    public static <T extends Comparable<T>> void FindMethod() {
        Database db_back = db;
        // PrintDB(db.database);
        // System.out.println(db.indexes[0]);
        // System.out.println(db.indexes[1]);
        System.out.println("--------- Testing findFirstWhere(col, data) -----------");
        try{
            PrintDB(db.findFirstWhere("Descriptio", "Calculus 114"));
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            PrintDB(db.findFirstWhere("Description", "Calculus 114"));
            // System.out.println(db.indexes[1]);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            PrintDB(db.findFirstWhere("Module Code", "COS212"));
            // System.out.println(db.indexes[1]);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            PrintDB(db.findFirstWhere("Module Code", "COS212@"));
            // System.out.println(db.indexes[1]);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        // PrintDB(db.database);
        System.out.println("-------------------- D O N E --------------------------");
        db = db_back;
    }

    public static <T extends Comparable<T>> void UpdateMethod() {
        Database db_back = db;
        System.out.println("------------ Printing the Indexes Arrays --------------");
        System.out.println("-- Module Codes Treap<Cell>: --");
        System.out.println("-------------------------------------------------------");
        System.out.println(db.indexes[0]);
        System.out.println("-- Description Treap<Cell>: --");
        System.out.println("-------------------------------------------------------");
        System.out.println(db.indexes[1]);
        System.out.println("-------------------------------------------------------");
        System.out.println("--------------- Printing The Database -----------------");
        PrintDB(db.database);
        System.out.println("-------------------------------------------------------\n\n");
        System.out.println("--- Testing updateFirstWhere(col, updateCon, data) ----");
        try{
            PrintDB(db.updateFirstWhere("Descriptio", "Calculus 114", "Khalkhulas 114"));
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            PrintDB(db.updateFirstWhere("Description", "Calculus 114", "Differentiation"));
            PrintDB(db.updateFirstWhere("Description", "Artificial Intelligence (II) 711", "GPT-4 Open AI"));
            PrintDB(db.updateFirstWhere("Description", "Imperative programming 132", "Procedural Programming"));
            PrintDB(db.updateFirstWhere("Description", "Operating systems 122", "Windows For Example"));
            PrintDB(db.updateFirstWhere("Module Code", "COS212", "POS800"));
            // System.out.println(db.indexes[1]);
            // PrintDB(db.database);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            PrintDB(db.updateFirstWhere("Credits", "16", "50"));
            // System.out.println(db.indexes[0]);
            // PrintDB(db.database);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            PrintDB(db.updateFirstWhere("Module Code", "COS212-2", "Assignment is Not Allowed"));
            // System.out.println(db.indexes[0]);
            // PrintDB(db.database);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("\n\n------------ Printing the Indexes Arrays --------------");
        System.out.println("-- Module Codes Treap<Cell>: --");
        System.out.println("-------------------------------------------------------");
        System.out.println(db.indexes[0]);
        System.out.println("-- Description Treap<Cell>: --");
        System.out.println("-------------------------------------------------------");
        System.out.println(db.indexes[1]);
        System.out.println("-------------------------------------------------------");
        System.out.println("--------------- Printing The Database -----------------");
        PrintDB(db.database);
        System.out.println("-------------------------------------------------------");
        // System.out.println(db.indexes[1].contains(new Cell(2, "Calculus 114")));
        System.out.println("-------------------- D O N E --------------------------");
        db = db_back;
    }

    public static <T extends Comparable<T>> void CountMethod() {
        Database db_back = db;
        System.out.println("--------- Testing countOccurrences(col, data) ---------");
        try{
            System.out.println("Count 'Calculus 114': " + db.countOccurences("Dscription", "Calculus 114"));
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            System.out.println("Count 'Calculus 114': " + db.countOccurences("Description", "Calculus 114"));
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            System.out.println("Count 'Mathematics 124': " + db.countOccurences("Description", "Mathematics 124"));
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            System.out.println("Count 'COS212': " + db.countOccurences("Module Code", "COS212"));
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            System.out.println("Count 'COS132': " + db.countOccurences("Module Code", "COS132"));
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        PrintDB(db.database);
        System.out.println("-------------------- D O N E --------------------------");
        db = db_back;
    }

    public static <T extends Comparable<T>> void TestingThe_AllWhere_Methods() {
        System.out.println("------- Testing TestingThe_AllWhere_Methods(col, data) -----------");
        Database db_back = db;
        // System.out.println("--------- Testing removeAllWhere(col, data) ---------");
        // try{
        //     PrintDB(db.removeAllWhere("Credits", "16"));
        //     // System.out.println(db.indexes[1]);
        // }
        // catch(DatabaseException e){
        //     System.out.println(e);
        // }
        // db = db_back;
        System.out.println("--- Testing updateAllWhere(col, updateCon, data) ----");
        try{
            PrintDB(db.updateFirstWhere("Description", "Mathematics 124", "Khalkhulas 124"));
            System.out.println("--------------------- The Indexes Array ---------------------");
            System.out.println(db.indexes[1]);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        db = db_back;
        System.out.println("--- Testing findAllWhere(col, updateCon, data) ----");
        try{
            PrintDB(db.findAllWhere("Description", "Mathematics 124"));
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        // db = db_back;
        System.out.println("-------------------- D O N E --------------------------");
        db = db_back;
    }

    public static <T extends Comparable<T>> void RemoveMethod() {
        Database db_back = db;
        System.out.println("--------- Testing removeFirstWhere(col, data) ---------");
        try{
            PrintDB(db.removeFirstWhere("Descriptio", "Calculus 114"));
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            PrintDB(db.removeFirstWhere("Description", "Calculus 114"));
            // System.out.println(db.indexes[1]);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            PrintDB(db.removeFirstWhere("Module Code", "COS212"));
            // System.out.println(db.indexes[1]);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            PrintDB(db.removeFirstWhere("Credits", "16"));
            // System.out.println(db.indexes[1]);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try{
            PrintDB(db.removeFirstWhere("Module Code", "COS212@"));
            // System.out.println(db.indexes[1]);
        }
        catch(DatabaseException e){
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        PrintDB(db.database);
        System.out.println("-------------------- D O N E --------------------------");
        db = db_back;
    }

    public static void PrintDB(String[][] database){
        if(database.length == 0){
            System.out.println("Empty Database");
        }
        else{
            for (String[] row : database) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void PrintDB(String[] database){
        if(database.length == 0){
            System.out.println("Empty Database");
        }
        else{
            int c = 0;
            for (String s : database) {
                if (c++ == 1) {
                    System.out.print(String.format("%1$-75s", s));
                } else {
                    System.out.print(s + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void task2() {
        /*
         * Note that we also want you to create your own main for this task.
         * 
         * It takes a while to set the DB up, so an example is given below, feel free to
         * change it to test the rest of the functions
         */
        String[] columns = { "Module Code", "Description", "Credits", "Year", "Session" };
        System.out.println("----------- Testing The Database Constructor ----------");
        db = new Database(columns, 100);
        System.out.println("---------------------- D O N E ------------------------");
        String[][] modules = {
                { "LST110", "Language and study skills 110", "6", "1", "Sem 1" },
                { "WTW124", "Mathematics 124", "16", "1", "Sem 2" },
                { "UP0102", "Academic orientation 102", "0", "1", "Year" },
                { "WTW114", "Calculus 114", "16", "1", "Sem 1" },
                { "WTW123", "Numerical analysis 123", "8", "1", "Sem 2" },
                { "PHY114", "First course in physics 114", "16", "1", "Sem 1" },
                { "PHY124", "First course in physics 124", "16", "1", "Sem 2" },
                { "AIM102", "Academic information management 102", "6", "1", "Sem 2" },
                { "COS122", "Operating systems 122", "16", "1", "Sem 2" },
                { "COS132", "Imperative programming 132", "16", "1", "Sem 1" },
                { "COS110", "Program design: Introduction 110", "16", "1", "Sem 2" },
                { "COS151", "Introduction to computer science 151", "8", "1", "Sem 1" },
                { "COS212", "Data structures and algorithms 212", "16", "2", "Sem 1" },
                { "COS226", "Concurrent systems 226", "16", "2", "Sem 2" },
                { "COS284", "Computer organisation and architecture 284", "16", "2", "Sem 2" },
                { "COS210", "Theoretical computer science 210", "8", "2", "Sem 1" },
                { "WTW248", "Vector analysis 248", "12", "2", "Sem 2" },
                { "PHY255", "Waves, thermodynamics and modem physics 255", "24", "2", "Sem 1" },
                { "PHY263", "General physics 263", "24", "2", "Sem 2" },
                { "WTW211", "Linear algebra 211", "12", "2", "Sem 1" },
                { "WTW218", "Calculus 218", "12", "2", "Sem 1" },
                { "WTW220", "Analysis 220", "12", "2", "Sem 2" },
                { "COS314", "Artificial intelligence 314", "18", "3", "Sem 1" },
                { "COS330", "Computer security and ethics 330", "18", "3", "Sem 2" },
                { "COS333", "Programming languages 333", "18", "3", "Sem 2" },
                { "COS344", "Computer graphics 344", "18", "3", "Sem 1" },
                { "PHY310", "Particle and astroparticle physics 310", "18", "3", "Sem 2" },
                { "PHY356", "Electronics, electromagnetism and quantum mechanics 356", "36", "3", "Sem 1" },
                { "PHY364", "Statistical mechanics, solid state physics and modelling 364", "36", "3", "Sem 2" },
                { "COS711", "Artificial Intelligence (II) 711", "15", "4", "Sem 2" },
                { "FSK700", "Physics 700", "135", "4", "Year" }
        };

        try {
            System.out.println("------------ Testing The Insert Function --------------");
            for (String[] mod : modules) {
                db.insert(mod);
            }
            System.out.println("---------------------- D O N E ------------------------");
            // System.out.println("-- Testing generateIndexAll() & generateIndexOn(String col) --");
            db.generateIndexAll();
            // System.out.println("---------------------- D O N E ------------------------");
        } catch (DatabaseException e) {
            System.out.println("Error: " + e);
        }
        System.out.println("------------- Now Printing The Database ---------------");
        PrintDB(db.database);
        System.out.println("---------------------- D O N E ------------------------");
        System.out.println("------------ Printing the Indexes Arrays --------------");
        System.out.println("-- Module Codes Treap<Cell>: --");
        System.out.println("-------------------------------------------------------");
        System.out.println(db.indexes[0]);
        System.out.println("-- Description Treap<Cell>: --");
        System.out.println("-------------------------------------------------------");
        System.out.println(db.indexes[1]);
        System.out.println("---------------------- D O N E ------------------------");
        System.out.println("-------------------------------------------------------");
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static <T extends Comparable<T>> String validTreap(Treap<T> t) {
        return (validTreap(t.root) ? ANSI_GREEN + "Valid\n" + ANSI_RESET : ANSI_RED + "Invalid\n" + ANSI_RESET);
    }

    public static <T extends Comparable<T>> boolean validTreap(Node<T> n) {
        if (n == null) {
            return true;
        }

        if (n.left != null && (n.left.priority > n.priority || n.getData().compareTo(n.left.getData()) < 0)) {
            return false;
        }

        if (n.right != null && (n.right.priority > n.priority || n.getData().compareTo(n.right.getData()) > 0)) {
            return false;
        }

        if (!validTreap(n.left)) {
            return false;
        }

        if (!validTreap(n.right)) {
            return false;
        }

        return true;
    }
}