package org.posapp.controller.manajemen_barang;

import javafx.collections.FXCollections;
import lombok.Getter;
import lombok.Setter;
import org.posapp.model.Barang;
import org.posapp.model.datastore.Datastore;
import org.posapp.view.ManajemenBarang.DetailBarangView;
import org.posapp.view.ManajemenBarang.ManajemenBarangView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class ManajemenBarangController {
    private ManajemenBarangView view;
    private Datastore model;
    static Integer greatestID;

    public ManajemenBarangController(ManajemenBarangView _view) {
        view = _view;
        model = Datastore.getInstance();
    }

    public void addBarang(){
        view.setRightSideLayout(new DetailBarangView(new Barang(-1, "", "", 0, 0, 0, ""), view));
        view.getLayout().getChildren().set(1, view.getRightSideLayout());
        //        view.getLayout().getChildren().set(1, new DetailBarangView(new Barang(-1, "", "", 0, 0, 0, ""), view));
    }

    public void updateBarang(Barang item, String nama, String kategori, String stok, String hargaJual, String hargaBeli, String pathGambar) throws InvalidInputException {
        if (item.getIdBarang() != -1) {
            model.getArrBarang().remove(item);

        }
        else {
            item.setIdBarang(model.getArrBarang().size() + 1);
        }
        item.setNama(nama);
        item.setKategori(kategori);
        try {
            item.setStok(Integer.parseInt(stok));
            item.setHargaJual(Float.parseFloat(hargaJual));
            item.setHargaBeli(Float.parseFloat(hargaBeli));
            item.setPathGambar(pathGambar);
            model.getArrBarang().add(item);
            view.getTable().setItems(FXCollections.observableArrayList(model.getArrBarang()));
        } catch (Exception err) {
            throw new InvalidInputException("Format Input Harga Jual atau Harga Beli Tidak Valid.");
        }
    }

    public void deleteBarang(Barang item){
        model.getArrBarang().remove(item);
        view.getTable().setItems(FXCollections.observableArrayList(model.getArrBarang()));
    }
}
