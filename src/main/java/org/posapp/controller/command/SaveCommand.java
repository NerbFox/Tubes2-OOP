package org.posapp.controller.command;

import javafx.collections.FXCollections;
import org.posapp.model.Customer;
import org.posapp.model.Datastore;
import org.posapp.model.Member;
import org.posapp.model.NonMember;
import org.posapp.view.custom_components.FixedSizeTable;

import java.util.ArrayList;
import java.util.Map;

public class SaveCommand implements Command {
    private final Map<String, String> savedValue;
    private final FixedSizeTable table;

    public SaveCommand(Map<String, String> savedValue, FixedSizeTable table) {
        this.savedValue = savedValue;
        this.table = table;
    }

    public void execute() {
        ArrayList<Customer> allCustomer = Datastore.getInstance().getArrCustomer();
        for (Customer cust : allCustomer) {
            if (cust.getIdCust().equals(Integer.parseInt(savedValue.get("id")))) {
                if (cust instanceof Member) {
                    ((Member) cust).setName(savedValue.get("name"));
                    ((Member) cust).setPhone(savedValue.get("phone"));
                    ((Member) cust).setVipStatus(savedValue.get("status").equals("VIP Member"));
                    ((Member) cust).setFrozen(savedValue.get("status").equals("Deactivated"));
                } else {
                    allCustomer.add(((NonMember) cust).makeMember(savedValue.get("name"), savedValue.get("phone")));
                    allCustomer.remove(cust);
                }
                Datastore.getInstance().setArrCustomer(allCustomer);
                System.out.println(cust.toString());

                Customer[] data = new Customer[Datastore.getInstance().getArrCustomer().size()];
                data = Datastore.getInstance().getArrCustomer().toArray(data);
                table.setItems(FXCollections.observableArrayList(data));
                table.refresh();
                break;
            }
        }
    }
}
