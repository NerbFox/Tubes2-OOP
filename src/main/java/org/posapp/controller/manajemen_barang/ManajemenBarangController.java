package org.posapp.controller.manajemen_barang;

import javafx.collections.FXCollections;
import lombok.Getter;
import lombok.Setter;
import org.posapp.model.Barang;
import org.posapp.view.ManajemenBarang.DetailBarangView;
import org.posapp.view.ManajemenBarang.ManajemenBarangView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class ManajemenBarangController {
    private ManajemenBarangView view;
    private List<Barang> model = new ArrayList<Barang>(Arrays.asList(
            new Barang(1, "rokok", "obat", 10, 1,3, "url"),
            new Barang(2, "teh", "minuman", 11, 1,3, "url"),
            new Barang(3, "kopi", "minuman", 16, 1,3, "url"),
            new Barang(4, "tempe", "makanan", 0, 1,3, "url")
    ));
    static Integer greatestID;

    public ManajemenBarangController(ManajemenBarangView _view){
        view = _view;
    }

    public void addBarang(){
        view.setRightSideLayout(new DetailBarangView(new Barang(-1, "", "", 0, 0, 0, ""), view));
        view.getLayout().getChildren().set(1, view.getRightSideLayout());
        //        view.getLayout().getChildren().set(1, new DetailBarangView(new Barang(-1, "", "", 0, 0, 0, ""), view));
    }

    public void updateBarang(Barang item, String nama, String kategori, int stok, int hargaJual, int hargaBeli, String pathGambar){
        if (item.getIdBarang() != -1) {
            model.remove(item);
        }
        else {
            item.setIdBarang(model.size() + 1);
        }
        item.setNama(nama);
        item.setKategori(kategori);
        item.setStok(stok);
        item.setHargaJual(hargaJual);
        item.setHargaBeli(hargaBeli);
        item.setPathGambar(pathGambar);
        model.add(item);
        view.getTable().setItems(FXCollections.observableArrayList(model));
    }

    public void deleteBarang(Barang item){
        model.remove(item);
        view.getTable().setItems(FXCollections.observableArrayList(model));
    }
}
