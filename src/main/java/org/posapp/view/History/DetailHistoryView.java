package org.posapp.view.History;

import javafx.geometry.Insets;
import org.posapp.model.Barang;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.posapp.view.custom_components.FixedSizeTable;

public class DetailHistoryView extends VBox{
    private FixedSizeTable<Barang> table2;
    public DetailHistoryView(TranHis item) {
        Label labName =          new Label("Nama\t\t: " + item.getNama());
        String stringID = Integer.toString(item.getID());
        Label labID =            new Label("ID\t\t\t: " + stringID);
        Label labMembership =    new Label("Membership\t: " + item.getMembership());
        Label labNoTel =         new Label("No Telepon\t: " + item.getNoTel());

        setMargin(labName,new Insets(20,0,0,0));
        setMargin(labNoTel,new Insets(0,0,20,0));

        String[] headers = new String[] {"idBarang", "nama", "hargaJual", "stok", "hargaBeli"};
        table2 = new FixedSizeTable<Barang>(488, 570, headers, headers, item.getHisBarang().toArray(new Barang[0]), this::onRowSelect);

        getChildren().addAll(labName,labID, labMembership, labNoTel, table2);
    }

    private void onRowSelect(Barang selectedItem) {
    }
}
