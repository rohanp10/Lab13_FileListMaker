import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
public class FileListMaker {

    static ArrayList<String> list = new ArrayList<>();

    static Scanner console = new Scanner(System.in);

    static final String menu = "A - Add  D - Delete  V - View  Q - Quit  O - Open  S - Save  C - Clear";
    static String cmd = "";
    static boolean done = false;
    static boolean needsToBeSaved = false;
    static String listFile = "";

    public static void main(String[] args) {

        do {

            cmd = displayInformation();

            switch(cmd)
            {
                case "A":
                    add();
                    break;
                case "D":
                    delete();
                    break;
                case "V":
                    view();
                    break;
                case "Q":
                    quit();
                    break;
                case "O":
                    open();
                    break;
                case "S":
                    save();
                    break;
                case "C":
                    clear();
                    break;
            }

        } while (!done);

        System.exit(0);

    }

    private static String displayInformation()
    {

        System.out.print("\n");
        for (int i = 0; i < 40; i++)
        {
            System.out.print("+");
        }
        System.out.print("\n");
        if (list.size() != 0)
        {
            for (String item: list)
            {
                System.out.printf("%3s", item);
                System.out.print("\n");
            }
        }
        else {
            System.out.print("+++");
            for (int i = 0; i < 10; i++) {
                System.out.print(" ");
            }
            System.out.print("List is empty");
            for (int i = 0; i < 10; i++) {
                System.out.print(" ");
            }
            System.out.print("+++");
            System.out.print("\n");
        }
        for (int i = 0; i < 40; i++)
        {
            System.out.print("+");
        }
        System.out.print("\n");


        String input = SafeInput.getRegExString(console, menu, "[AaDdVvQqOoSsCc]");
        input = input.toUpperCase();

        return input;

    }

    private static void add()
    {
        String item = SafeInput.getNonZeroLenString(console, "Enter your item");
        list.add(item);
        needsToBeSaved = true;
    }

    private static void delete()
    {
        displayNumberedList();

        if (list.size() >= 1)
        {
            int choice = SafeInput.getRangedInt(console, "What item number do you want to delete?", 1, list.size());
            choice -= 1;
            list.remove(choice);
            needsToBeSaved = true;
        }
        else {
            System.out.println("Error: The list is currently empty, there are no items to delete!\n");
        }

    }

    private static void view()
    {
        for (int i = 0; i < 40; i++)
        {
            System.out.print("+");
        }
        System.out.println("");
        if (list.size() != 0)
        {
            for (String item: list)
            {
                System.out.printf("%3s", item);
                System.out.print("\n");
            }
        }
        else {
            System.out.print("+++");
            for (int i = 0; i < 10; i++) {
                System.out.print(" ");
            }
            System.out.print("List is empty");
            for (int i = 0; i < 10; i++) {
                System.out.print(" ");
            }
            System.out.print("+++");
            System.out.print("\n");
        }
        for (int i = 0; i < 40; i++)
        {
            System.out.print("+");
        }
        System.out.println("\n");
    }

    private static void quit()
    {
        if (needsToBeSaved == true)
        {
            boolean confirm = SafeInput.getYNConfirm(console, "Do you want to save this list before quitting");
            if (confirm == true) {
                save();
            }
        }

        done = true;
    }

    private static void open()
    {
        boolean openYN = true;
        if (needsToBeSaved)
        {
            openYN = SafeInput.getYNConfirm(console, "Do you want to save this list before loading a new one");
            if (openYN == true) {
                save();
            }
        }
        if (!needsToBeSaved || openYN == false)
        {
            clear();
            JFileChooser chooser = new JFileChooser();
            File selectedFile;
            String rec = "";

            try
            {

                File workingDirectory = new File(System.getProperty("user.dir"));

                chooser.setCurrentDirectory(workingDirectory);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();

                    InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    while (reader.ready())
                    {
                        rec = reader.readLine();
                        list.add(rec);
                    }

                    reader.close();

                    listFile = selectedFile.toString();

                    needsToBeSaved = false;

                    System.out.println("\nThe list file has been opened!");

                }

                else {
                    System.out.println("Failed to choose a file to process");
                    System.out.println("Run the program again!");
                    System.exit(0);
                }
            }

            catch (FileNotFoundException e)
            {
                System.out.println("File not found!");
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }

    private static void save()
    {

        File workingDirectory = new File(System.getProperty("user.dir"));

        Path file;

        if (listFile.equals(""))
        {
            file = Paths.get(workingDirectory.getPath() + "//src//data.txt");
        }
        else {
            file = Paths.get(workingDirectory.getPath()).resolve(listFile);
        }

        try {

            Files.newBufferedWriter(file, TRUNCATE_EXISTING);
            Files.newInputStream(file, TRUNCATE_EXISTING);

            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String line: list)
            {
                writer.write(line, 0, line.length());

                writer.newLine();
            }
            writer.close();

            needsToBeSaved = false;

            System.out.println("\nThe list has been saved to the file " + file.getFileName() + "!");
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void clear()
    {
        list.clear();
        needsToBeSaved = true;
    }

    private static void displayNumberedList()
    {
        for (int i = 0; i < 40; i++)
        {
            System.out.print("+");
        }
        System.out.print("\n");
        if (list.size() != 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                System.out.printf("%3d%35s", i+1, list.get(i));
                System.out.print("\n");
            }
        }
        else {
            System.out.print("+++");
            for (int i = 0; i < 10; i++) {
                System.out.print(" ");
            }
            System.out.print("List is empty");
            for (int i = 0; i < 10; i++) {
                System.out.print(" ");
            }
            System.out.print("+++");
            System.out.print("\n");
        }
        for (int i = 0; i < 40; i++)
        {
            System.out.print("+");
        }
        System.out.print("\n");

    }

}