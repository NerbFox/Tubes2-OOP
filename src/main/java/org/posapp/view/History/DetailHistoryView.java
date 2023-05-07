package org.posapp.view.History;

import javafx.geometry.Insets;
import org.posapp.model.Barang;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.posapp.view.custom_components.FixedSizeTable;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailHistoryView extends VBox{
    private FixedSizeTable<TransHisDetail> table2;
    public DetailHistoryView(TranHis item) {
        Label labName =          new Label("Nama\t\t\t:    " + item.getNama());
        String stringID = Integer.toString(item.getID());
        Label labID =            new Label("ID\t\t\t\t:    " + stringID);
        Label labMembership =    new Label("Membership\t\t:    " + item.getMembership());
        Label labNoTel =         new Label("No Telepon\t\t:    " + item.getNoTel());

        setMargin(labName,new Insets(20,0,0,0));
        setMargin(labNoTel,new Insets(0,0,20,0));

        String[] headers = new String[] {"idBarang", "nama", "hargaJual", "stok", "hargaBeli"};
        table2 = new FixedSizeTable<TransHisDetail>(488, 570, headers, headers, makeArrTransHisDetail(item.getMapBarang()), this::onRowSelect);

        getChildren().addAll(labName,labID, labMembership, labNoTel, table2);
    }

    public DetailHistoryView() {
        Label labName =          new Label("Nama\t\t\t:    ");
        Label labID =            new Label("ID\t\t\t\t:    ");
        Label labMembership =    new Label("Membership\t\t:    ");
        Label labNoTel =         new Label("No Telepon\t\t:    ");

        setMargin(labName,new Insets(20,0,0,0));
        setMargin(labNoTel,new Insets(0,0,20,0));

        String[] headers = new String[] {"idBarang", "nama", "hargaJual", "stok", "hargaBeli"};
        table2 = new FixedSizeTable<TransHisDetail>(488, 570, headers, headers, new TransHisDetail[0] , this::onRowSelect);

        getChildren().addAll(labName,labID, labMembership, labNoTel,table2);
    }

    private TransHisDetail[] makeArrTransHisDetail(HashMap<Barang, Integer> mapBarang) {
        ArrayList<TransHisDetail> data = new ArrayList<>();
        for (Barang key : mapBarang.keySet()) {
            data.add(new TransHisDetail(key, mapBarang.get(key)));
        }
        return data.toArray(new TransHisDetail[data.size()]);
    }

    private void onRowSelect(TransHisDetail selectedItem) {
        return;
    }

}
