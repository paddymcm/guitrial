/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.guiTrial;

/**
 *
 * @author jennypaddy
 */
import gnu.io.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
//import javax.comm.*;

/**
 *
 * @author jennypaddy
 */
public abstract class SerialWriter implements SerialPortEventListener
{
    
    GuiTrial window = null;     //passed from main GUI
    
    private Enumeration ports = null; //for containing the ports that will be found
    private HashMap portMap = new HashMap();   //map the port names to CommPortIdentifiers
    private CommPortIdentifier selectedPortIdentifier = null;  //this is the object that contains the opened port
    private SerialPort serialPort = null;

    private InputStream input = null;        //input and output streams for sending and receiving data
    private OutputStream output = null;

    //just a boolean flag that i use for enabling
    //and disabling buttons depending on whether the program
    //is connected to a serial port or not
    private boolean bConnected = false;

    //the timeout value for connecting with the port
    final static int TIMEOUT = 2000;

    //some ascii values for for certain things
    final static int SPACE_ASCII = 32;
    final static int DASH_ASCII = 45;
    final static int NEW_LINE_ASCII = 10;

    //a string for recording what goes on in the program
    //this string is written to the GUI
    String logText = "";
    
     //search for all the serial ports
    //pre style="font-size: 11px;": none
    //post: adds all the found ports to a combo box on the GUI
    public void searchForPorts()
    {
        ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements())
        {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)     //get only serial ports
            {
                GuiTrial.cboxPorts.addItem(curPort.getName());
                portMap.put(curPort.getName(), curPort);
            }
        }
    }
    
    
    
}


