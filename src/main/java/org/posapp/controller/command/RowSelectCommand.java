package org.posapp.controller.command;

import org.posapp.model.Customer;
import org.posapp.model.Member;
import org.posapp.view.customer_list_info.CustomerInfoDetail;

public class RowSelectCommand implements Command {
    private final CustomerInfoDetail detail;
    private final Customer selectedItem;

    public RowSelectCommand(CustomerInfoDetail detail, Customer selectedItem) {
        this.detail = detail;
        this.selectedItem = selectedItem;
    }

    public void execute() {
        detail.setTextTitleID(selectedItem.getIdCust().toString());
        detail.setTextMembershipStatus(selectedItem.getMemberStatus());

        if (selectedItem instanceof Member) {
            detail.setTextName(((Member) selectedItem).getName());
            detail.setTextPhone(((Member) selectedItem).getPhone());
            detail.setTextPoints(((Member) selectedItem).getPoin().toString());
        } else {
            detail.clear();
        }
        detail.setComboBox(selectedItem);
    }
}