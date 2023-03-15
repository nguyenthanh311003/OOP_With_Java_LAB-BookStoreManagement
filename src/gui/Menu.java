package gui;

import java.util.Scanner;
import manager.Management;

public class Menu {

    private static Scanner sc = new Scanner(System.in);

    public void publisherMenu() throws Exception {
        Management m = new Management();
        int op;
        boolean cont = true;
        String s;
        m.loadBookFromFile();
        m.loadFromFile();
        do {
            try {
                System.out.println("<=============Book Store Management=============>");
                System.out.println("~~~~~~~~~~~~~~~~~Publisher Part~~~~~~~~~~~~~~~~~");
                System.out.println("| 1- Add a Publisher                              |");
                System.out.println("| 2- Delete the Publisher                         |");
                System.out.println("~~~~~~~~~~~~~~~~~~~~Book Part~~~~~~~~~~~~~~~~~~~");
                System.out.println("| 3- Add a Book                                   |");
                System.out.println("| 4- Search the Book                              |");
                System.out.println("| 5- Update a Book                                |");
                System.out.println("| 6- Delete the Book                              |");
                System.out.println("| 7- Quit                                         |");
                System.out.println("<===============================================>");
                System.out.print("Enter your option or press any key except from 1 to 6 to quit menu: ");
                op = Integer.parseInt(sc.nextLine());
                switch (op) {
                    case 1:
                        boolean a = true;
                        do {
                            m.addPublisher();
                            m.loadFromFile();
                            System.out.print("Would you like yo return to the menu ? (press y to return menu or press any key to stop): ");
                            s = sc.nextLine();
                            if (s.equals("y") || s.equals("Y")) {
                                a = false;
                                cont = true;
                            } else {
                                a = true;
                            }

                        } while (a);
                        break;

                    case 2:
                        boolean b = true;
                        do {
                            m.deletePublisher();
                            m.loadFromFile();
                            System.out.print("Would you like yo return to the menu ? (press y to return menu or press any key to stop): ");
                            s = sc.nextLine();
                            if (s.equals("y") || s.equals("Y")) {
                                b = false;
                                cont = true;
                            } else {
                                b = true;
                            }
                        } while (b);
                        break;

                    case 3:
                        boolean c = true;
                        do {
                        m.addBook();
                        m.loadBookFromFile();
                        System.out.print("Would you like yo return to the menu ? (press y to return menu or press any key to stop): ");
                        s = sc.nextLine();
                        if (s.equals("y") || s.equals("Y")) {
                            c = false;
                            cont = true;
                        } else {
                            c = true;
                        }
                        }while(c);
                        break;

                    case 4:
                        boolean d = true;
                        do {
                        m.searchBook();
                        System.out.print("Would you like yo return to the menu ? (press y to return menu or press any key to stop): ");
                        s = sc.nextLine();
                        if (s.equals("y") || s.equals("Y")) {
                            d = false;
                            cont = true;
                        } else {
                            d = true;
                        }
                        }while(d);
                        break;

                    case 5:
                        boolean e = true;
                        do {
                        m.update();
                        m.loadBookFromFile();
                        System.out.print("Would you like yo return to the menu ? (press y to return menu or press any key to stop): ");
                        s = sc.nextLine();
                        if (s.equals("y")) {
                            e = false;
                            cont = true;
                        } else {
                            e = true;
                        }
                        }while(e);
                        break;

                    case 6:
                        boolean f = true;
                        do {
                        m.deleteBook();
                        m.loadBookFromFile();
                        System.out.print("Would you like yo return to the menu ? (press y to return menu or press any key to stop): ");
                        s = sc.nextLine();
                        if (s.equals("y")) {
                            f = false;
                            cont = true;
                        } else {
                            f = true;
                        }
                        } while(f);
                        break;

                    default:
                        cont = false;
                        break;
                }
            } catch (NumberFormatException exception) {
                System.err.println("Please enter an integer in this situation!");
                cont = true;
            }
        } while (cont);
    }
}
