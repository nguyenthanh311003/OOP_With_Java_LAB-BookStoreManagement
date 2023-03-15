
package gui;

import object.Publisher; 
import manager.Management;

public class Main {
    public static void main(String[] args) throws Exception {
        Management pu = new Management();
        Menu me = new Menu();
          me.publisherMenu();
    }
}

