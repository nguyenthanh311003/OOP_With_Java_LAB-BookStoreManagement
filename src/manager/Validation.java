package manager;

import java.util.List;
import java.util.Scanner;
import object.Book;
import object.Publisher;

public class Validation {

    private static Scanner sc = new Scanner(System.in);
    public static String pID;
    public static String bID;
    public static String bIDAndName;
    //Publisher Part begin//

    public static String getPublisherID(String msg, List<Publisher> pList, int mode) throws Exception {
        //mode 1 = input
        //mode 2 = delete
        //mode 3 = check
        //mode 4 = update
        boolean cont = true;
        do {
            try {
                String pattern = "(P)[0-9][0-9][0-9][0-9][0-9]";
                System.out.print(msg);
                pID = sc.nextLine();

                if (idExist(pID, pList) && pID.matches(pattern) && mode == 1) {
                    throw new Exception();
                }
                if (!idExist(pID, pList) && pID.matches(pattern) && mode == 1) {
                    return pID;
                }

                if (!idExist(pID, pList) && pID.matches(pattern) && (mode == 2 || mode == 3)) {
                    throw new NullPointerException();
                }

                if (idExist(pID, pList) && (mode == 3 || mode == 4 || mode == 2)) {
                    return pID;
                } else if (pID.equals("") && mode == 4) {
                    return pID;
                } else {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException exception) {
                System.err.println("Please enter an ID as form (Pxxxxx) with x is a digit from 0 to 9");

            } catch (NullPointerException exception) {
                System.err.println("ID not exist!");

            } catch (Exception exception) {
                System.err.println("ID already exist!");

            }
        } while (cont);

        return pID;
    }

    public static String getPublisherName(String msg) throws Exception {
        String s = "";
        boolean cont = true;
        do {
            try {
                String pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*${5,30}";
                System.out.print(msg);
                s = sc.nextLine();
                if (!s.matches(pattern)) {
                    throw new NumberFormatException();
                }
                if (s.length() < 5 || s.length() > 30) {
                    throw new Exception();
                }
                cont = false;
            } catch (NumberFormatException exception) {
                System.err.println("Please enter publisher name in range a to z!");
            } catch (Exception exception) {
                System.err.println("Please enter a publisher name in length 5 to 30 characters!");
                cont = true;
            }
        } while (cont);
        return s;
    }

    public static String getPublisherPhoneNumber(String msg) throws Exception {
        String s = "";
        boolean cont = true;
        do {
            try {
                String pattern = "[0-9]{10,12}";
                System.out.print(msg);
                s = sc.nextLine();
                if (s.length() < 10 || s.length() > 12) {
                    throw new Exception();
                }
                cont = false;
            } catch (Exception exception) {
                System.err.println("Please enter a publisher phone number in length 10 to 12!");
                cont = true;
            }
        } while (cont);
        return s;
    }

    public static String getpublisherDeleteCode(String msg, String err, List<Publisher> pList) {
        String s = "";
        while (true) {
            System.out.print(msg);
            s = sc.nextLine();
            if (!s.isEmpty()) {
                if (idExist(s, pList)) {
                    break;
                }
            }
            System.err.println(err);
        }
        return s;
    }

    public static boolean idExist(String publisherId, List<Publisher> pList) {
        for (Publisher o : pList) {
            if (o.getPublisherId().equals(publisherId)) {
                return true;
            }
        }
        return false;
    }

    //Publisher Part end//
    //Book Part begin//
    public static String getBookID(String msg, List<Book> bList, int mode) {
        //mode 1 = input
        //mode 3 = update
        //mode 3 = delete
        boolean cont = true;
        do {
            try {
                String pattern = "(B)[0-9][0-9][0-9][0-9][0-9]";
                System.out.print(msg);
                bID = sc.nextLine();
                if (!bID.matches(pattern)) {
                    throw new NumberFormatException();
                }

                if (bookIdExist(bID, bList) && mode == 1) {
                    throw new Exception();
                }

                if (!bookIdExist(bID, bList) && mode == 3) {
                    throw new NullPointerException();
                }

                cont = false;
            } catch (NumberFormatException exception) {
                System.err.println("Please enter an ID as form (Bxxxxx) with x is a digit from 0 to 9!");
                cont = true;
            } catch (NullPointerException exception) {
                System.err.println("Book ID not exist!");
            } catch (Exception exception) {
                System.err.println("Book ID already exist!");
            }
        } while (cont);
        return bID;
    }
//    public static String getPublisherNameForBook(String msg, List<Publisher> pList, List<Book> bList) {
//        String name;
//        for (Publisher p : pList) {
//            if(p.getPublisherId().equals(msg)) {
//                name = p.getPublisherName();
//                return name;
//            }
//        }
//        return null;
//    }

//    public static String getPublisherNameForBook(String msg, List<Publisher> pList, List<Book> bList) {
//        boolean cont = true;
//        String name;
//        do {
//            try {
//                String pattern = "(P)[0-9][0-9][0-9][0-9][0-9]";
//                System.out.print(msg);
//                pID = sc.nextLine();
//                if (!pID.matches(pattern)) {
//                    throw new NumberFormatException();
//                }
//                if (!idExist(pID, pList)) {
//                    throw new NullPointerException();
//                }
//
////                if (idExist(pID, pList)) {
////                    for (Publisher p : pList) {
////                        name = p.getPublisherName();
////                        return name;
////                    }
////                    return null;
////                }
//                for (Publisher p : pList) {
//                    if (p.getPublisherId().equals(pID)) {
//                        name = p.getPublisherName();
//                        return name;
//                    }
//                }
//
//                cont = false;
//
//            } catch (NumberFormatException exception) {
//                System.err.println("Please enter an ID as form (Pxxxxx) with x is a digit from 0 to 9");
//                cont = true;
//            } catch (NullPointerException exception) {
//                System.err.println("ID not exist!");
//                cont = true;
//            }
//        } while (cont);
//
//        return null;
//    }
    public static String getBookName(String msg, int mode) {
        //mode 1 = input
        //mode 2 = update
        String s = "";
        boolean cont = true;
        do {
            try {
                String pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*${5,30}";
                System.out.print(msg);
                s = sc.nextLine();

                if (s.matches(pattern) && (mode == 1 || mode == 2)) {
                    return s;
                } else if (s.equals("") && mode == 2) {

                    return s;

                } else {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException exception) {
                System.err.println("Please enter Book name in range a to z and in length 5 to 30 characters!");
                cont = true;
            }
        } while (cont);
        return s;
    }

    public static double getBookPrice(String msg) {
        double money = 0;
        boolean cont = true;
        do {
            try {
                System.out.print(msg);
                money = Double.parseDouble(sc.nextLine());
                if (money < 0) {
                    throw new Exception();
                }
                cont = false;
            } catch (NumberFormatException exception) {
                System.err.println("Book price must be a real number!");
                cont = true;
            } catch (Exception exception) {
                System.err.println("Book price must be greater than 0!");
                cont = true;
            }

        } while (cont);

        return money;
    }

    public static int getBookQuantity(String msg) {
        int quantity = 0;
        boolean cont = true;
        do {
            try {
                System.out.print(msg);
                quantity = Integer.parseInt(sc.nextLine());
                if (quantity < 0) {
                    throw new Exception();
                }
                cont = false;
            } catch (NumberFormatException exception) {
                System.err.println("A quantity must be an integer number!");
            } catch (Exception exception) {
                System.err.println("Please enter a quantity greater than 0!");
            }
        } while (cont);
        return quantity;
    }

    public static String getBookStatus(String msg, int mode) {
        String s = "";
        boolean cont = true;
        do {
            //msg: Status of this book: Available or Not Available (press a for Available || press n for Not Available): 
            try {
                String pattern = "[a-z]";
                System.out.print(msg);
                s = sc.nextLine();
                if (s.equals("n") && (mode == 1 || mode == 2)) {
                    s = "Not Available";
                    break;
                } else if (s.equals("a") && (mode == 1 || mode == 2)) {
                    s = "Available";
                } else if (s.equals("") && mode == 2) {
                    return s;
                } else {
                    throw new Exception();
                }

//                if(!s.matches(pattern)) {
//                    throw new Exception();
//                }
                cont = false;
            } catch (Exception exception) {
                System.err.println("Please enter a or n (a: Available, n: Not Available) to set a status!");
                cont = true;
            }
        } while (cont);
        return s;
    }

    public static boolean bookIdExist(String bookID, List<Book> bList) {
        for (Book o : bList) {
            if (o.getBookId().equals(bookID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean bookIdExist2(String bookIDandName, List<Book> bList) {
        for (Book o : bList) {
            if (o.getBookName().equals(bookIDandName) || o.getBookOfPublisherId().equals(bookIDandName)) {
                return true;
            }
        }
        return false;
    }

    public static String pName(String id, List<Publisher> pList, List<Book> bList) {
        String name;
        for (Publisher p : pList) {
            if (p.getPublisherId().equals(id)) {
                name = p.getPublisherName();
                return name;
            }
        }
        return null;
    }

//    public static String getBookSearch(String msg, List<Book> bList, List<Publisher> pList) {
//        boolean cont = true;
//        do {
//            try {
//                System.out.print(msg);
//                bIDAndName = sc.nextLine();
//                if (!bookIdExist2(bIDAndName, bList)) {
//                    throw new NullPointerException();
//                }
//
//                cont = false;
//            } catch (NullPointerException exception) {
//                System.err.println("Book not exist!");
//                cont = true;
//            }
//        } while (cont);
//        return bIDAndName;
//    }

}
