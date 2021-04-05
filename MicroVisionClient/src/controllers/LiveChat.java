package controllers;

import Sound.Mp3;
import driver.Driver;
import javazoom.jl.decoder.JavaLayerException;
import models.users.Customer;
import models.users.Employee;
import models.users._User;
import utilities.ServerRequest;
import utilities.ServerResponse;

import java.util.ArrayList;

public class LiveChat {

    public void logOnToLiveChat (_User user) {
        //Login to live chat
        ServerRequest<_User> request = new ServerRequest<_User>(ServerRequest.USER_LIVE_CHAT_COMMAND, user);
        Driver.messageConnection.sendAction(request);

        ServerResponse response;
        response = Driver.messageConnection.receiveResponse();

        //Array to save all users who log on
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        customerArrayList = (ArrayList<Customer>) response.getData();
        employeeArrayList = (ArrayList<Employee>)response.getData();

        if (response.getMessage().equals("Login Successful")) {

            if (user.getClass().getSimpleName().equals("Customer")) {
                //Send a message to all Customers the technicians that are online
                //play an mp3 sound - maybe a ping
                for (Employee employee: employeeArrayList) {
                    System.out.println("Technician " + employee.getfirstName() + " is online");
                    try {
                        Mp3.playMp3("1");
                    }catch (JavaLayerException ex) {
                        System.out.println("Error message to be logged");
                    }
                }
            }else if (user.getClass().getSimpleName().equals("Employee")) {
                    //Send a message to all Technicians the customers that are online
                    //play an mp3 sound - maybe a ping
                    for (Customer customer: customerArrayList) {
                        System.out.println("Customer " + customer.getfirstName() + " is online");
                        try {
                            Mp3.playMp3("1");
                        }catch (JavaLayerException ex) {
                            System.out.println("Error message to be logged");
                        }
                    }
                }
        }else if (response.getMessage().equals("Login Failed")) {
            System.out.println("Failed to Login");
        }
    }
}
